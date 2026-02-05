#include <sys/stat.h>
#include <pwd.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s file1 [file2 ...]\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    long long total_size = 0;
    long long total_blocks = 0;

    for (int i = 1; i < argc; i++) {
        struct stat info;
        if (stat(argv[i], &info) == -1) {
            fprintf(stderr, "fsize: Can't stat %s\n", argv[i]);
            continue;
        }

        // Obter o nome do utilizador dono do ficheiro
        struct passwd* pw = getpwuid(info.st_uid);
        const char* username = pw ? pw->pw_name : "unknown";

        // Converter tempo de última modificação para formato legível
        char* mod_time = ctime(&info.st_mtime);

        // ctime adiciona uma nova linha, remover se necessário
        if (mod_time[strlen(mod_time)-1] == '\n') {
            mod_time[strlen(mod_time)-1] = '\0';
        }

        printf("%s:\n", argv[i]);
        printf("  Size: %lld bytes\n", (long long)info.st_size);
        printf("  Disk blocks: %lld\n", (long long)info.st_blocks);
        printf("  Last modified: %s\n", mod_time);
        printf("  Owner: %s\n\n", username);

        total_size += info.st_size;
        total_blocks += info.st_blocks;
    }

    printf("Total size: %lld bytes\n", total_size);
    printf("Total disk blocks: %lld\n", total_blocks);

    return EXIT_SUCCESS;
}
