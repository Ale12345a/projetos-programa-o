#include <sys/wait.h>
#include <sys/socket.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define BUF_SIZE 1024

int main(int argc, char* argv[]) {
    if (argc != 2) {
        fprintf(stderr, "usage: %s filename\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int sockets[2], retv;
    char buf[BUF_SIZE];

    // cria socket pair (bidirecional)
    if (socketpair(AF_UNIX, SOCK_STREAM, 0, sockets) == -1) {
        perror("socketpair");
        exit(EXIT_FAILURE);
    }

    retv = fork();
    if (retv == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (retv > 0) {  
        /* PAI */
        FILE *file = fopen(argv[1], "r");
        if (!file) {
            perror("fopen");
            exit(EXIT_FAILURE);
        }

        close(sockets[1]);  // pai usa apenas sockets[0]

        // lê o ficheiro e envia ao filho
        size_t nread;
        while ((nread = fread(buf, 1, BUF_SIZE, file)) > 0) {
            if (write(sockets[0], buf, nread) != nread) {
                perror("write to child");
                fclose(file);
                close(sockets[0]);
                exit(EXIT_FAILURE);
            }
        }
        fclose(file);

        // sinaliza EOF ao filho (fecha escrita)
        shutdown(sockets[0], SHUT_WR);

        // lê resposta em maiúsculas do filho e imprime
        ssize_t nbytes;
        while ((nbytes = read(sockets[0], buf, BUF_SIZE)) > 0) {
            if (write(STDOUT_FILENO, buf, nbytes) != nbytes) {
                perror("write stdout");
                close(sockets[0]);
                exit(EXIT_FAILURE);
            }
        }

        close(sockets[0]);
        wait(NULL);

    } else {  
        /* FILHO */
        close(sockets[0]);  // filho usa apenas sockets[1]

        ssize_t nbytes;
        while ((nbytes = read(sockets[1], buf, BUF_SIZE)) > 0) {
            for (ssize_t i = 0; i < nbytes; i++) {
                buf[i] = toupper((unsigned char) buf[i]);
            }
            if (write(sockets[1], buf, nbytes) != nbytes) {
                perror("write to parent");
                close(sockets[1]);
                exit(EXIT_FAILURE);
            }
        }

        close(sockets[1]);
        exit(EXIT_SUCCESS);
    }

    return 0;
}
