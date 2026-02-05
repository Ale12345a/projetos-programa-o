#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
int main(int argc, char* argv[]) {
fork();
fork();
fork();

printf("PID = %d, PPID = %d\n", getpid(), getppid());


exit(EXIT_SUCCESS);
}