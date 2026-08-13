#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <dirent.h>
#include <unistd.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <string.h>

void print_permissions(mode_t mode) {
    char perms[11] = "----------";

    if (S_ISDIR(mode)) perms[0] = 'd';
    if (mode & S_IRUSR) perms[1] = 'r';
    if (mode & S_IWUSR) perms[2] = 'w';
    if (mode & S_IXUSR) perms[3] = 'x';
    if (mode & S_IRGRP) perms[4] = 'r';
    if (mode & S_IWGRP) perms[5] = 'w';
    if (mode & S_IXGRP) perms[6] = 'x';
    if (mode & S_IROTH) perms[7] = 'r';
    if (mode & S_IWOTH) perms[8] = 'w';
    if (mode & S_IXOTH) perms[9] = 'x';

    printf("%s ", perms);
}

void print_file_info(const char *path, const char *name) {
    struct stat info;
    char fullpath[1024];
    snprintf(fullpath, sizeof(fullpath), "%s/%s", path, name);

    if (stat(fullpath, &info) != 0) {
        perror("stat");
        return;
    }

    print_permissions(info.st_mode);

    printf("%2ld ", (long)info.st_nlink);

    struct passwd *pw = getpwuid(info.st_uid);
    struct group  *gr = getgrgid(info.st_gid);
    printf("%s %s ", pw ? pw->pw_name : "?", gr ? gr->gr_name : "?");

    printf("%8ld ", (long)info.st_size);

    char mod_time[256];
    strftime(mod_time, sizeof(mod_time), "%b %d %H:%M", localtime(&info.st_mtime));
    printf("%s ", mod_time);

    printf("%s\n", name);
}

void list_directory(const char *path) {
    DIR *d = opendir(path);
    if (!d) {
        perror("opendir");
        return;
    }

    struct dirent *entry;
    while ((entry = readdir(d)) != NULL) {
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;
        print_file_info(path, entry->d_name);
    }

    closedir(d);
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s file_or_dir [...]\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    for (int i = 1; i < argc; i++) {
        struct stat info;
        if (stat(argv[i], &info) != 0) {
            perror(argv[i]);
            continue;
        }

        if (S_ISDIR(info.st_mode)) {
            printf("%s:\n", argv[i]);
            list_directory(argv[i]);
        } else {
            print_file_info(".", argv[i]);
        }
    }

    return 0;
}
