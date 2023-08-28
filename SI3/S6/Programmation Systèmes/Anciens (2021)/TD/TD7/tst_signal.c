#include <sys/param.h>
#include <stdio.h>
#include <unistd.h>


void on_signal(int sig) {
    printf("*** signal %d\n", sig);
    if (sig == 2) {
        signal(SIGINT, on_signal);
    }
}


int main() {

    signal(SIGSEGV, SIG_IGN);
    signal(SIGINT, SIG_IGN);

    signal(SIGSEGV, on_signal);
    signal(SIGINT, on_signal);
    while (1) {
        pause();
    }
}