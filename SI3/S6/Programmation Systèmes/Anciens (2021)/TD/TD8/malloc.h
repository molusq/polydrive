/*
 * my-malloc.h 	-- Implementation de malloc, free, calloc, realloc
 *
 * Implémentation first-fit pour malloc
 *
 * Erick Gallesio (2015/05/11)
 * Stéphane Lavirotte (2017/04/11)
 */

#ifndef _MY_MALLOC_H_
#define _MY_MALLOC_H_

#include <stdlib.h>

void *mymalloc(size_t size);
void  myfree(void *ptr);
void *mycalloc(size_t nmemb, size_t size);
void *myrealloc(void *ptr, size_t size);

/* Instrumentation */
#ifdef MALLOC_DBG
   void mymalloc_infos(char *str);
#else
#  define mymalloc_infos(str)	/* nothing */
#endif
#endif /* _MY_MALLOC_H_ */
