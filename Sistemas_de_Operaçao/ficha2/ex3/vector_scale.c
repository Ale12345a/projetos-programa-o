#include "vector.h"
#include <math.h>

// multiplicação por escalar
vector vector_scale(double k, vector v) {
    return vector_new(k * v.x, k * v.y, k * v.z);
}
