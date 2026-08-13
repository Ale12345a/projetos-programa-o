#include "vector.h"
#include <math.h>

// produto vectorial (v1 × v2)
vector vector_vprod(vector v1, vector v2) {
    return vector_new(
        v1.y * v2.z - v1.z * v2.y,
        v1.z * v2.x - v1.x * v2.z,
        v1.x * v2.y - v1.y * v2.x
    );
}
