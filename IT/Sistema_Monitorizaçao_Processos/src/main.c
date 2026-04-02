#include "monitor.h"
#include "utils.h"

int main() {
    initUI();

    while (!shouldExit()) {
        refreshUI();
        sleep_ms(1000); // Atualiza a cada 1 segundo
    }

    closeUI();
    return 0;
}