#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 10
#define NUM_TRAPS 5

typedef struct {
    int x, y;
} Drone;

typedef struct {
    int x, y;
} Trap;

void printGrid(Drone d1, Drone d2, Trap traps[], int numTraps) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            int printed = 0;
            if (i == d1.y && j == d1.x) { printf("1 "); printed=1; }
            else if (i == d2.y && j == d2.x) { printf("2 "); printed=1; }
            else {
                for(int t=0; t<numTraps; t++){
                    if(i==traps[t].y && j==traps[t].x){ printf("X "); printed=1; break; }
                }
            }
            if(!printed) printf(". ");
        }
        printf("\n");
    }
    printf("\n");
}

void moveDrone(Drone *d, char cmd) {
    switch(cmd) {
        case 'w': if(d->y > 0) d->y--; break;
        case 's': if(d->y < SIZE-1) d->y++; break;
        case 'a': if(d->x > 0) d->x--; break;
        case 'd': if(d->x < SIZE-1) d->x++; break;
        default: printf("Comando inválido! Use w/a/s/d\n"); break;
    }
}

int checkTrap(Drone d, Trap traps[], int numTraps) {
    for(int i=0; i<numTraps; i++){
        if(d.x == traps[i].x && d.y == traps[i].y) return 1;
    }
    return 0;
}

int main() {
    Drone d1 = {0, 0};
    Drone d2 = {SIZE-1, SIZE-1};
    char cmd1, cmd2;
    Trap traps[NUM_TRAPS];

    srand(time(NULL));
    // Gerar armadilhas aleatórias
    for(int i=0; i<NUM_TRAPS; i++){
        traps[i].x = rand() % SIZE;
        traps[i].y = rand() % SIZE;
        // Garantir que não aparece no início dos drones
        if((traps[i].x==d1.x && traps[i].y==d1.y) || (traps[i].x==d2.x && traps[i].y==d2.y)) i--;
    }

    printf("=== Simulação de Drones com Armadilhas ===\n");
    printf("Drone 1: w/a/s/d, Drone 2: i/j/k/l\n");
    printf("X = armadilha, . = espaço livre\n\n");

    while(1) {
        printGrid(d1, d2, traps, NUM_TRAPS);

        // Movimento do drone 1
        printf("Drone 1 move: ");
        scanf(" %c", &cmd1);
        moveDrone(&d1, cmd1);
        if(checkTrap(d1, traps, NUM_TRAPS)) {
            printGrid(d1, d2, traps, NUM_TRAPS);
            printf("💥 Drone 1 caiu numa armadilha! Fim do jogo.\n");
            break;
        }

        // Movimento do drone 2
        printf("Drone 2 move: ");
        scanf(" %c", &cmd2);
        if(cmd2=='i') cmd2='w';
        else if(cmd2=='k') cmd2='s';
        else if(cmd2=='j') cmd2='a';
        else if(cmd2=='l') cmd2='d';
        moveDrone(&d2, cmd2);
        if(checkTrap(d2, traps, NUM_TRAPS)) {
            printGrid(d1, d2, traps, NUM_TRAPS);
            printf("💥 Drone 2 caiu numa armadilha! Fim do jogo.\n");
            break;
        }

        // Checar colisão
        if(d1.x == d2.x && d1.y == d2.y) {
            printGrid(d1, d2, traps, NUM_TRAPS);
            printf("💥 Colisão! Fim do jogo.\n");
            break;
        }
    }

    return 0;
}