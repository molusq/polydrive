#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <unistd.h>
#include "timer.h"

void *print_thread(void *arg) {
    return NULL;
}

long create_threads(int n) {

    pthread_t threads[n];
    struct timespec vartime = timer_start();

    for (int i = 0; i < n; i++) {
        pthread_create(&threads[i], NULL, print_thread, &n);
    }
    long time = timer_end(vartime);

    for (int i = 0; i < n; i++) {
        pthread_join(threads[i], NULL);
    }
    return time;
}

int main(int argc, char *argv[]) {
    int n = atoi(argv[1]);

    long time_processus = create_threads(n);

    printf("Time taken for creating %d threads (nanoseconds): %ld\n", n, time_processus);
    printf("Time taken for creating %d threads (milliseconds): %ld\n", n, time_processus / NANO_TO_MILLI);

    /* On flushe la sortie standard */
    fflush(stdout);

    /* Fin du père */
    sleep(20);
    exit(0);
}