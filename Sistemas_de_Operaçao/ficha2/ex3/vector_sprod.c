#include "vector.h"
#include <math.h>

// produto escalar (v1 ⋅ v2)
double vector_sprod(vector v1, vector v2) {
    return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
}