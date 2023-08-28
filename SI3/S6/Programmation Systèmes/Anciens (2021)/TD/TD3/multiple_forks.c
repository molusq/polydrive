#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>

pid_t getpid(void);
pid_t getppid(void);

int main(){
    int i = 0;
    int pid = getpid();
    //int status;

    for(; i < 10; i++){
        switch (fork()) {
            case -1 :
                exit(1);
            case 0 :
                for(int j = 0; j < 10; j++){
                    printf("%d\n", getpid());
                    sleep(2);
                }
                exit(0);
            default:
                //wait(&status);
                break;
        }
    }
    //exit(0);
    return 0;
}