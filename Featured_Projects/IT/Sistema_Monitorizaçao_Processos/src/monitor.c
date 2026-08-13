#include "monitor.h"
#include <ncurses.h>
#include <stdlib.h>
#include <unistd.h>

WINDOW *mainwin;

void initUI() {
    mainwin = initscr();
    cbreak();
    noecho();
    nodelay(mainwin, TRUE);
    keypad(mainwin, TRUE);
    curs_set(0);
    start_color();
    init_pair(1, COLOR_GREEN, COLOR_BLACK);
}

void closeUI() {
    endwin();
}

void displayProcesses() {
    wclear(mainwin);

    attron(COLOR_PAIR(1));
    mvprintw(0, 0, "=== MONITOR DE PROCESSOS ===");
    attroff(COLOR_PAIR(1));

    mvprintw(2, 0, "PID     USER     CPU%%     MEM%%     CMD");

    // Simulação de processos (podes ligar a /proc depois)
    for (int i = 0; i < 5; i++) {
        mvprintw(3 + i, 0, "%5d   user%d    %2d%%      %2d%%      process%d", 
                 1000+i, i, 10+i*5, 5+i*3, i);
    }
    refresh();
}

void refreshUI() {
    displayProcesses();
}

bool shouldExit() {
    int ch = wgetch(mainwin);
    return (ch == 'q' || ch == 'Q');
}