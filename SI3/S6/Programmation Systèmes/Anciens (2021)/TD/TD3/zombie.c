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
            printf("Je suis le fils\n");
            exit(0);
        default:
            sleep(10);
            printf("Je suis le père\n");
            break;
    }
    return 0;
}