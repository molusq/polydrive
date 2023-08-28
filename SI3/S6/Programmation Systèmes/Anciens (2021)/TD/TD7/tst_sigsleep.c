//
// Created by Florian Salord on 18/04/2018.
//

#include <signal.h>
#include <sys/param.h>
#include <stdio.h>
#include <unistd.h>


void on_signal(int sig) {
    printf("*** signal %d\n", sig);
}

int main() {
    struct sigaction sigact;

    sigset_t msk_int, msk_segv;

    sigemptyset(&msk_int);
    sigaddset(&msk_int, SIGINT);

    sigemptyset(&msk_segv);
    sigaddset(&msk_segv, SIGSEGV);

    sigact.sa_handler = on_signal;

    sigact.sa_mask = msk_segv;
    sigaction(SIGINT, &sigact, NULL);

    sigact.sa_mask = msk_int;
    sigaction(SIGSEGV, &sigact, NULL);

    sleep(50);
    pause();
}