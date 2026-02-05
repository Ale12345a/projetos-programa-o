#include <errno.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

static void handler1() { printf("caught SIGUSR1\n"); }
static void handler2() { printf("caught SIGUSR2\n"); }
static void handler_int() { printf("caught SIGINT (CTRL-C)\n"); }
static void handler_tstp() { printf("caught SIGTSTP (CTRL-Z)\n"); }
static void handler_hup() { printf("caught SIGHUP (logout/terminal closed)\n"); }

int main(int argc, char* argv[]) {
    printf("my PID is %d\n", getpid());

    if (signal(SIGUSR1, handler1) == SIG_ERR) {
        fprintf(stderr, "Can't catch SIGUSR1: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    if (signal(SIGUSR2, handler2) == SIG_ERR) {
        fprintf(stderr, "Can't catch SIGUSR2: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    if (signal(SIGINT, handler_int) == SIG_ERR) {
        fprintf(stderr, "Can't catch SIGINT: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    if (signal(SIGTSTP, handler_tstp) == SIG_ERR) {
        fprintf(stderr, "Can't catch SIGTSTP: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    if (signal(SIGHUP, handler_hup) == SIG_ERR) {
        fprintf(stderr, "Can't catch SIGHUP: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
}
