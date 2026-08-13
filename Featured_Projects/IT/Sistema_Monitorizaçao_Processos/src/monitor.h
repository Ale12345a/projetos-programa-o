#ifndef MONITOR_H
#define MONITOR_H

#include <stdbool.h>

void initUI();
void closeUI();
void displayProcesses();
void refreshUI();
bool shouldExit();

#endif