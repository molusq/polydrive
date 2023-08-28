#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
#include "load_library.h"

static void *plib = NULL;
static void (*sort_handler)(int [], int);

void load_library(const char *library_name) {
    plib = dlopen(library_name, RTLD_LAZY);
    sort_handler = (void (*)(int [], int))dlsym(plib, "sort");
}

void sort(int tab[], int size) {
    sort_handler(tab, size);
}

void unload(){
    dlclose(plib);
    exit(1);
}