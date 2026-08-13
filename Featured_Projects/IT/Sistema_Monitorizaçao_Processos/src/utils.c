#include "utils.h"
#include <unistd.h>

void sleep_ms(int milliseconds) {
    usleep(milliseconds * 1000);
}