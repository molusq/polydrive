#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#include "load_library.h"
#include "sort.h"
#include "utils.h"

/* ------------------------------------------------------------------------------------
 * Valeurs par défaut des variables globales qui suivent 
 * ------------------------------------------------------------------------------------
 */

#define SIZE 10000
#define FALSE 0
#define TRUE 1

/* ------------------------------------------------------------------------------------
 * Variables globales 
 * ------------------------------------------------------------------------------------
*/

static int Size_Array = SIZE;
static int Verbose = FALSE;

/* ------------------------------------------------------------------------------------
 * Prototypes de fonctions définies plus tard 
 * ------------------------------------------------------------------------------------
*/
static void Scan_Args(int argc, char *argv[]);

static void Usage(const char *execname);

// call this function to start a nanosecond-resolution timer
struct timespec timer_start() {
    struct timespec start_time;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &start_time);
    return start_time;
}

// call this function to end a timer, returning nanoseconds elapsed as a long
long timer_end(struct timespec start_time) {
    struct timespec end_time;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &end_time);
    long diffInNanos = end_time.tv_nsec - start_time.tv_nsec;
    return diffInNanos;
}

int main(int argc, char *argv[]) {
    /* Décodage des arguments de la ligne de commande */
    Scan_Args(argc, argv);

    int list[Size_Array];

    // Initialize a ramdom list of numbers;
    srand(0);
    for (int i = 0; i < Size_Array; i++) {
        list[i] = rand() % 1000;
    }

    if (Verbose) {
        printf("Array to sort:");
        print_list(list, Size_Array);
    }

    struct timespec vartime = timer_start();
    sort(list, Size_Array);
    long time_elapsed_nanos = timer_end(vartime);

    if (Verbose)
        printf("Time taken for sorting (nanoseconds): ");
    printf("%ld\n", time_elapsed_nanos);

    if (Verbose) {
        printf("Sorted array:");
        print_list(list, Size_Array);
    }

    unload();
}

/* Analyse des arguments 
 * ----------------------
 */
static void Scan_Args(int argc, char *argv[]) {
    for (int i = 1; i < argc; i++) {
        if (argv[i][0] == '-') {
            switch (argv[i][1]) {
                case 'h':
                    Usage(argv[0]);
                    exit(1);
                case 's':
                    Size_Array = atoi(argv[++i]);
                    break;
                case 'v':
                    Verbose = TRUE;
                    break;
            }
        } else {
            load_library(argv[i]);
        }
    }
}

/* Information d'utilisation
 * -------------------------
 */
static void Usage(const char *execname) {
    printf("\nUsage: %s [options]\n", execname);
    printf("\nOptions générales\n"
                   "-----------------\n"
                   "-h\tce message\n"
                   "-s\ttaille du tableau à trier\n"
                   "-v\taffiche plus d'informations\n");
}
