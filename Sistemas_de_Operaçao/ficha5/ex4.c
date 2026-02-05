#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
int main(int argc, char* argv[]) {
/* fork um processo filho */
pid_t pid = fork();
if (pid == -1) {
perror("fork");
exit(EXIT_FAILURE);
}
if (pid == 0) {
/* processo filho */
execlp(argv[1],argv[1],NULL);
/* as próximas duas linhas serão executadas somente se execlp() falhar */
perror("execlp");
exit(EXIT_FAILURE);
}
else {
/* processo pai */
int retv = waitpid(pid, NULL, 0);
if (retv == -1) {
perror("waitpid");
exit(EXIT_FAILURE);
}
}
exit(EXIT_SUCCESS);
}