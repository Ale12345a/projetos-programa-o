#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
int main(int argc, char* argv[]) {
  for (int i = 0; i < 4; i++)
    fork();

  printf("PID = %d, PPID = %d\n", getpid(), getppid());

  exit(EXIT_SUCCESS);
}