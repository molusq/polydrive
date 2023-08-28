/*
 * my-malloc.c 	-- Implementation de malloc, free, calloc, realloc
 *
 * Implémentation first-fit pour malloc
 *
 * Erick Gallesio (2015/06/16)
 * Stéphane Lavirotte (2018/04/26)
 */

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>
#include "malloc.h"

/* On suppose qu'on ajoute au moins 50 Headers au minimum */
#define MIN_ALLOCATION          50

/* Pour s'aligner sur des frontieres multiples
 * de la taille du type le plus contraignant
 */
#define MOST_RESTRICTING_TYPE double
typedef union header {                /* Header de bloc */
    struct {
        size_t size;                /* Taille du bloc */
        union header *ptr;            /* bloc libre suivant */
    } info;
    MOST_RESTRICTING_TYPE dummy;    /* Ne sert qu'a provoquer un alignement */
} Header;

#define NEXT(p)             ((p)->info.ptr)
#define SIZE(p)             ((p)->info.size)

/* L'unité de découpage de la mémoire est la taille de Header car ça permet de gérer facilement chaînage
   Le programme appelant malloc reçoit un pointeur et utilise la mémoire comme il veut */
#define BLOCKS_TO_ALLOCATE(size) (1 + ((size) + sizeof(Header)-1) / sizeof(Header))

static Header base = {{0, &base}}; /* Le pointeur de départ: aucune zone allouée */
static Header *freep = &base;          /* freep pointe sur la 1ère zone libre */

/* ====================================================================== */

static int nb_alloc = 0;        /* Nombre de fois où on alloué     */
static int nb_dealloc = 0;        /* Nombre de fois où on désalloué  */
static int nb_sbrk = 0;        /* nombre de fois où a appelé sbrk */

/* Utilisé si on n'a pas trouvé assez de place */
static void *allocate_core(size_t size) {
    // fait un appel à sbrk pour allouer la mémoire et
    // un appel à myfree pour chaîner la zone allouée avec la liste freep
    /*Header *pnew;
    if (size < MIN_ALLOCATION) {
        size = MIN_ALLOCATION;
    }
    pnew = sbrk((int) (size * sizeof(Header)));
    if (pnew == (void *) -1) {
        return NULL;
    }
    SIZE(pnew) = size;
    NEXT(pnew) = NULL;
    myfree(pnew + 1);
    return freep;*/
    Header *pnew;
    if (size < MIN_ALLOCATION) {
        size = MIN_ALLOCATION;
    }
    pnew = sbrk((int) (size * sizeof(Header)));
    SIZE(pnew) = size;
    NEXT(pnew) = NULL;
    //myfree(pnew + 1);
    return pnew;
}

void *mymalloc(size_t size) {
    // A COMPLETER
    // fait un appel à allocate_core si pas de place dans la liste freep
    /*Header *prev = freep;
    Header *current;
    size_t blocks = BLOCKS_TO_ALLOCATE(size);
    for (current = NEXT(freep);; prev = current, current = NEXT(current)) {
        if (SIZE(current) >= size) {
            if (SIZE(current) == size) {
                NEXT(prev) = NEXT(current);
            } else {
                SIZE(current) -= blocks;
                current += SIZE(current);
                SIZE(current) = blocks;
            }
            return current + 1;
        }
        if (current == freep) {
            if ((current = allocate_core(blocks)) == NULL) {
                return NULL;
            }
        }
    }*/
    Header *current;
    Header *prev = freep;

    if (NEXT(freep) == NULL) {
        Header *block = allocate_core(size);
        NEXT(block) = freep;
        SIZE(freep) = 0;
        NEXT(freep) = block;
    }
    size_t block_size = BLOCKS_TO_ALLOCATE(size);
    for (current = NEXT(freep); current != freep; prev = current, current = NEXT(current)) {
        if (SIZE(current) >= block_size) {
            if (block_size == SIZE(current)) {
                NEXT(prev) = NEXT(current);
                NEXT(current) = NULL;
            } else {
                Header *remain_block = current + block_size;
                SIZE(remain_block) = SIZE(current) - block_size;
                NEXT(remain_block) = NEXT(current);
                NEXT(prev) = remain_block;
                SIZE(current) = block_size;
            }
            return current + 1;
        }
    }
    current = allocate_core(block_size);
    return current + 1;
}

void myfree(void *ptr) {
    // A COMPLETER
    // Libère la zone pointée par ptr en l'insérant dans freep
    // Essaie de coller les blocs adjacents à cette zone
    // NOTA : la liste freep est triée par ordre croissant

    Header *ptr_remove = ((Header *) ptr) - 1;
    Header *current;
    Header *prev = freep;
    for (current = NEXT(freep); current != freep; prev = current, current = NEXT(current)) {
        if ((current + 1 + SIZE(current)) == ptr_remove) {
            SIZE(current) += SIZE(ptr_remove);
            return;
        } else if ((ptr_remove + 1 + SIZE(ptr_remove)) == current) {
            SIZE(ptr_remove) += SIZE(current) + 1;
            NEXT(ptr_remove) = NEXT(current);
            NEXT(prev) = ptr_remove;
            return;
        }
    }
    NEXT(prev) = ptr_remove;
    NEXT(ptr_remove) = freep;
}


void *mycalloc(size_t nmemb, size_t size) {
    // A COMPLETER
    // Utilise mymalloc
    return NULL;
}


void *myrealloc(void *ptr, size_t size) {
    // A COMPLETER
    // utilise myfree et mymalloc
    return ptr;
}

/* ====================================================================== */
#ifdef MALLOC_DBG
void mymalloc_infos(char *msg)
{
    if (msg) fprintf(stderr, "**********\n*** %s\n", msg);

    fprintf(stderr, "# allocs = %3d - # deallocs = %3d - # sbrk = %3d\n",
            nb_alloc, nb_dealloc, nb_sbrk);

    /* affichage de la zone libre */
    Header *p = freep;
    do {
        fprintf(stderr, "\tBlock @ %p (size=%4ld, next %p)\n", p, SIZE(p), NEXT(p));
        p = NEXT(p);
    } while (p != freep);

    if (msg) fprintf(stderr, "**********\n\n");
}
#endif
