//
// Created by Florian Salord on 04/04/2018.
//

#include <unistd.h>
#include <fcntl.h>
#include <printf.h>

#define MAX_BUFF 1024

int main() {
    int p = open("/tmp/my_named_pipe", O_RDONLY);
    char buff[MAX_BUFF];
    while (read(p, buff, MAX_BUFF)) {
        //write(STDOUT_FILENO, buff, sizeof(buff));
        printf("%s\n", buff);
    }
    close(p);

    return 0;
}