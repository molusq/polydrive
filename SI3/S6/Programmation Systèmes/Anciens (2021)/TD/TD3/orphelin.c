#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>

pid_t getpid(void);
pid_t getppid(void);

int main(){
    switch (fork()) {
        case -1 :
            exit(1);
        case 0 :
            sleep(0.1);
            printf("Je suis le fils, mon père est %d\n", getppid());
            exit(0);
        default:
            printf("Je suis le père avec pid %d\n", getpid());
            break;
    }
    return 0;
}