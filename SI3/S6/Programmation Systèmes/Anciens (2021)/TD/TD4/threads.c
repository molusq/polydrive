#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <unistd.h>

void *print_thread(void *arg) {
    sleep(*(int *) arg);
    for (int i = 0; i < 5; i++) {
        printf("%lu\n", pthread_self());
        sleep(0.1);
    }
    printf("Linux Thread ID: %ld\n", syscall(SYS_gettid));
    exit(0);
    //pthread_exit(NULL);
    return NULL;
}

int main(int argc, char *argv[]) {
    int a = 0;
    int b = 0;
    if (argc == 3) {
        a = atoi(argv[1]);
        b = atoi(argv[2]);
    }
    pthread_t t1, t2;

    pthread_create(&t1,
                   NULL,
                   print_thread,
                   &a);

    pthread_create(&t2,
                   NULL,
                   print_thread,
                   &b);
    //exit(0);
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    return 0;
}