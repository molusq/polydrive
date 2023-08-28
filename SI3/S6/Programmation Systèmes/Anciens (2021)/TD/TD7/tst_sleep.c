//
// Created by Florian Salord on 18/04/2018.
//

#include <sys/param.h>
#include <stdio.h>
#include <unistd.h>

//
// Created by Florian Salord on 18/04/2018.
//
//typedef void (*sighandler_t)(int);


void on_signal(int sig) {
    printf("*** signal %d\n", sig);
}


int main() {

    signal(SIGSEGV, SIG_IGN);
    signal(SIGINT, SIG_IGN);

    signal(SIGSEGV, on_signal);
    signal(SIGINT, on_signal);
    sleep(50);
    pause();
    return 0;
}