#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>

pid_t getpid(void);
pid_t getppid(void);

int main(){
    printf("Je ne devrais pas apparaître");
    execlp("./exec_prop-aux.exe", "", NULL);
    return 0;
}