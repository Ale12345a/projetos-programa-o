#include "complex.h"
complex complex_new(double x, double y) {
complex r;
r.x = x;
r.y = y;
return r;
}