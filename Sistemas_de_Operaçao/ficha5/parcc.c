#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <string.h>

int main(int argc, char* argv[]) {
    if(argc < 4 || strcmp(argv[1], "-o") != 0) {
        fprintf(stderr, "Usage: %s -o output source1.c source2.c ...\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char* output = argv[2];   // nome do binário final
    int n_sources = argc - 3; // número de ficheiros fonte
    char** sources = &argv[3];

    pid_t* pids = malloc(n_sources * sizeof(pid_t));
    if (!pids) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    // Compilar cada ficheiro fonte em paralelo
    for(int i = 0; i < n_sources; i++) {
        pid_t pid = fork();
        if(pid == -1) {
            perror("fork");
            exit(EXIT_FAILURE);
        }
        if(pid == 0) {
            // Filho: compilar o ficheiro
            printf("[pid:%d] compiling %s ...\n", getpid(), sources[i]);

            char obj_file[256];
            snprintf(obj_file, sizeof(obj_file), "%.*s.o", (int)(strlen(sources[i])-2), sources[i]); // remove .c e adiciona .o

            execlp("gcc", "gcc", "-Wall", "-c", sources[i], "-o", obj_file, NULL);

            // se execlp falhar
            perror("execlp");
            exit(EXIT_FAILURE);
        } else {
            // Pai guarda o PID
            pids[i] = pid;
        }
    }

    // Espera todos os filhos terminarem
    for(int i = 0; i < n_sources; i++) {
        waitpid(pids[i], NULL, 0);
    }

    free(pids);

    // Preparar comando de linking final
    char** link_args = malloc((n_sources + 4) * sizeof(char*));
    if(!link_args) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    link_args[0] = "gcc";
    int j = 1;
    for(int i = 0; i < n_sources; i++) {
        char* obj_file = malloc(256);
        snprintf(obj_file, 256, "%.*s.o", (int)(strlen(sources[i])-2), sources[i]);
        link_args[j++] = obj_file;
    }
    link_args[j++] = "-o";
    link_args[j++] = output;
    link_args[j] = NULL;

    // Fazer link final
    pid_t link_pid = fork();
    if(link_pid == 0) {
        execvp("gcc", link_args);
        perror("execvp");
        exit(EXIT_FAILURE);
    } else {
        waitpid(link_pid, NULL, 0);
    }

    // liberar memória alocada
    for(int i = 1; i <= n_sources; i++) free(link_args[i]);
    free(link_args);

    printf("Compilation finished: %s created.\n", output);

    return 0;
}
