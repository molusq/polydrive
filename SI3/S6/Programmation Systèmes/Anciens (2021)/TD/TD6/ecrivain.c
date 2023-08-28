//
// Created by Florian Salord on 04/04/2018.
//

#include <unistd.h>
#include <fcntl.h>
#include <memory.h>

#define MAX_BUFF 1024

int main() {
    int p = open("/tmp/my_named_pipe", O_WRONLY);
    char *to_write = "Test123457890";
    write(p, to_write, strlen(to_write));
    close(p);

    return 0;
}