/*							-*- coding: utf-8 -*-
 *
 * redir.c	-- Redirection fichiers
 *
 * Erick Gallesio (2012-03-09)
 * Stéphane Lavirotte (2017/03/23)
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int fp;

void usage(char *progname) {
    fprintf(stderr, "Usage: %s direction file cmd arg ...\n", progname);
    fprintf(stderr, "       direction = R or W\n");
    exit(1);
}

int main(int argc, char *argv[]) {

    if (argc < 4) {
        usage(*argv);
    }

    /* Vérifier que le premier argument est bien un R ou un W */
    switch (argv[1][0]) {
        case 'R':
        case 'r':
            fp = open(argv[2], O_RDONLY);
            dup2(fp, STDIN_FILENO);
            close(fp);
            execvp(argv[3], argv + 3);
            break;

        case 'W':
        case 'w':
            fp = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0666);
            dup2(fp, STDOUT_FILENO);
            close(fp);
            execvp(argv[3], argv + 3);
            break;
        default:
            usage(*argv);
    }
}