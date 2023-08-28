/*
 * my-malloc.c  -- Implementation de malloc, free, calloc, realloc
 *
 * Implémentation first-fit pour malloc
 *
 * Erick Gallesio (2015/06/16)
 * Stéphane Lavirotte (2018/04/26)
 */

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include "malloc.h"

/* On suppose qu'on ajoute au moins 50 Headers au minimum */
#define MIN_ALLOCATION 50

/* Pour s'aligner sur des frontieres multiples
 * de la taille du type le plus contraignant
 */
#define MOST_RESTRICTING_TYPE double
typedef union header { /* Header de bloc */
    struct
    {
        size_t size;       /* Taille du bloc */
        union header *ptr; /* bloc libre suivant */
    } info;
    MOST_RESTRICTING_TYPE dummy; /* Ne sert qu'a provoquer un alignement */
} Header;

#define NEXT(p) ((p)->info.ptr)
#define SIZE(p) ((p)->info.size) 

/* L'unité de découpage de la mémoire est la taille de Header car ça permet de gérer facilement chaînage
   Le programme appelant malloc reçoit un pointeur et utilise la mémoire comme il veut */
#define BLOCKS_TO_ALLOCATE(size) (1 + (size + sizeof(Header) - 1) / sizeof(Header))

static Header base = {{0, &base}}; /* Le pointeur de départ: aucune zone allouée */
static Header *freep = &base;      /* freep pointe sur la 1ère zone libre */

/* ====================================================================== */

static int alloc_size_alloc = 0;   /* Nombre de fois où on alloué     */
static int alloc_size_dealloc = 0; /* Nombre de fois où on désalloué  */
static int alloc_size_sbrk = 0;    /* nombre de fois où a appelé sbrk */

/* Utilisé si on n'a pas trouvé assez de place */
static void *allocate_core(size_t size)
{
    Header *new;

    /* Allouer au moins MIN_ALLOCATION cellules */
    if (size < MIN_ALLOCATION)
        size = MIN_ALLOCATION;

    new = sbrk(size * sizeof(Header));
    if (new == ((void *)-1))
        return NULL; /* Plus de memoire */

    alloc_size_sbrk += 1;

#ifdef MALLOC_DBG
    fprintf(stderr, "\t--> sbrk(%ld) [%ld blocks]\n", size * sizeof(Header), size);
#endif
    /* Mettre a jour la taille du Header */
    SIZE(new) = size;

    /* Liberer ce bloc (ceci afin de le remettre dans la liste libre) */
    myfree(new + 1);
    alloc_size_dealloc -= 1; /* car le précédent free n'en est pas un (moche) */

    /* Retourner le pointeur sur le debut de la zone libre */
    return freep;
}

/* ====================================================================== */

void *mymalloc(size_t size)
{
    Header *p, *prevp;
    unsigned blocks;

    alloc_size_alloc += 1;

    /* Allouer un bloc dont la taille est un multiple de celle d'un header */
    blocks = BLOCKS_TO_ALLOCATE(size);

#ifdef MALLOC_DBG
    fprintf(stderr, "mymalloc(%ld) [%d blocks]\n", size, blocks);
#endif

    /* Chercher un bloc assez gros dans la liste des blocs libres */
    for (prevp = freep, p = NEXT(freep);; prevp = p, p = NEXT(p))
    {
        if (SIZE(p) >= blocks)
        {
            if (SIZE(p) == blocks)
            {
                /* Gagne: c'est juste la bonne taille */
                NEXT(prevp) = NEXT(p);
            }
            else
            {
                /* Decoupage d'un bloc libre */
                SIZE(p) -= blocks;
                p += SIZE(p);
                SIZE(p) = blocks;
            }
            return (void *)(p + 1);
        }

        /* Regarder si on a fait un tour complet dans la liste des blocs libres */
        if (p == freep)
        {
            /* Allouer de la memoire et la remettre dans la ronde */
            if ((p = allocate_core(blocks)) == NULL)
                return NULL;
        }
    }
}

/* ====================================================================== */

void myfree(void *ptr)
{
    // Libère la zone pointée par ptr en l'insérant dans freep
    // Essai de coller les blocs adjacents à cette zone
    // récupérére le 1er pointeur de la liste d'espace libre

    // récupére le header de la case mémoire (1 case mémoire avant)
    Header *released = (Header *)ptr - 1;
    // prevp correspond à la 1er zone mémoire libre
    Header *prevp = freep;
    // p correspond à l'adresse de la 2ème zone mémoire libre
    Header *p = NEXT(freep);

    // aucune zone mémoire de libre on libère directement le pointeur
    if (freep == NULL)
    {
        // prochaine adresse de release reboucle sur release (seul espace mémoire)
        NEXT(released) = released;
        freep = released;
        return;
    }

    // tant que l'adresse mémoire de p ne correspond pas à freep (le début)
    // et tant que p est inférieur à l'adresse mémoire de release on continue
    // si p est plus grand que l'adresse de release, ça veut dire que l'on a dépassé  
    // sa zone mémoire et qu'il faut s'arrêter
    while (NEXT(p) != freep && p < released)
    {
        // comme le for de mymalloc mais en while
        prevp = p;
        p = NEXT(p);
    }

    // free après la fin de la liste (dernier pointeur sur la liste libre atteint)
    // si à la fin de la boucle while, on a toujours p qui est inférieur à released,
    // cela veut dire que l'adresse se situe après la fin de la liste libre.
    if (p < released)
    {
        NEXT(released) = freep;
        NEXT(p) = released;
        //s'unir si possible
        coalesce(p, released);
        return;
    }

    // free avant le début de la liste
    // si released est inférieur à freep, ça veut dire que la zone en mémoire à libérer est
    // avant celle de freep
    if (released < freep)
    {
        NEXT(released) = freep;
        NEXT(p) = released;
        //s'unir si possible
        coalesce(released, freep);
        return;
    }

    //sinon le free à lieu quelque part au milieu de la zone mémoire
    NEXT(released) = p;
    NEXT(prevp) = released;
    //s'unir si possible
    coalesce(released, p);
    coalesce(prevp, released);
}

int coalesce(Header *thisP, Header *toThatP)
{
    // ex : 
    // &thisP = 20octets, next(&thisP) = 50octets et size(thisp) = 30octets
    // donc 50octets == 30octets + 20octets 
    if (NEXT(thisP) == SIZE(thisP)+ thisP)
    {
        NEXT(thisP) = NEXT(toThatP);
        SIZE(thisP) += SIZE(toThatP);
        return 1;
    }
    return 0;
}

/* ====================================================================== */

void *mycalloc(size_t nmemb, size_t size)
{
    //nmemb = nombre d'élément
    //size = taille de chaque élément
    //nombre d'élément à initialiser
    size_t alloc_size = nmemb * size;
    char *setp, *p;

    //init à 0 les éléments
    if (setp = p = mymalloc(alloc_size) != NULL)
        for (int i = 0; i < alloc_size; i++)
            *setp++ = 0;
    return p;
}

void *myrealloc(void *ptr, size_t size)
{
    //taille =0 ? on déalloc et retourne une erreur
    if (size == 0)
    {
        myfree(ptr);
        return NULL;
    }

    //pointeur null ? on alloc un nouvel espace mémoire
    if (ptr == 0)
        return mymalloc(size);

    //taille actuelle >= nouvelle taille ? on ne change rien
    unsigned blocks = BLOCKS_TO_ALLOCATE(size);
    Header *changed = (Header *)ptr - 1;
    if (SIZE(changed) >= blocks)
        return ptr;

    //sinon on realloc l'espace mémoire
    Header *newP = mymalloc(size);
    if (newP)
    {

        memcpy(newP, ptr, SIZE(changed));
        myfree(ptr);
    }
}

#ifdef MALLOC_DBG
void mymalloc_infos(char *msg)
{
    if (msg)
        fprintf(stderr, "**********\n*** %s\n", msg);

    fprintf(stderr, "# allocs = %3d - # deallocs = %3d - # sbrk = %3d\n",
            alloc_size_alloc, alloc_size_dealloc, alloc_size_sbrk);

    /* affichage de la zone libre */
    Header *p = freep;
    do
    {
        fprintf(stderr, "\tBlock @ %p (size=%4ld, next %p)\n", p, SIZE(p), NEXT(p));
        p = NEXT(p);
    } while (p != freep);

    if (msg)
        fprintf(stderr, "**********\n\n");
}
#endif
