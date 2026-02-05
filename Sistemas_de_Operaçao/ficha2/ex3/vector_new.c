#include "vector.h"
#include <math.h>

vector vector_new(double x, double y, double z) {
    vector v;
    v.x = x;
    v.y = y;
    v.z = z;
    return v;
}