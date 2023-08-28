#include <unistd.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>

void lsrec(char *path) {
    DIR *dir = opendir(path);
    if (!dir) {
        perror("Directory unavailable\n");
        return;
    }

    struct dirent *dirent;
    char newdir[512];
    printf("Current directory: %s\n", path);
    while ((dirent = readdir(dir))) {
        if (strncmp(dirent->d_name, ".", 1)) {
            printf("%s\n", dirent->d_name);
        }
    }
    closedir(dir);

    dir = opendir(path);
    while ((dirent = readdir(dir))) {
        if (strncmp(dirent->d_name, ".", 1)) {
            if (dirent->d_type == 4) {
                sprintf(newdir, "%s/%s", path, dirent->d_name);
                lsrec(newdir);
            }
        }
    }
    closedir(dir);
}

int main(int argc, char *argv[]) {
    switch (argc) {
        case 1:;
            char buffer[1024];
            getcwd(buffer, sizeof(buffer));
            printf("%s", buffer);
            lsrec(buffer);
            break;
        case 2:
            lsrec(argv[1]);
            break;
        default:
            break;
    }
    return 0;
}