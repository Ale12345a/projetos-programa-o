
// Deep comparison function
function deepComp(a, b) {
    // Check if both values are strictly equal
    if (a === b) {
        return true;
    }

    // Check if either value is null or not an object
    if (a === null || b === null ||
        typeof a !== 'object' || typeof b !== 'object') {
        return false;
    }

    // Get enumerable properties
    let keysA = Object.keys(a);
    let keysB = Object.keys(b);

    // Check if both objects have the same number of properties
    if (keysA.length !== keysB.length) {
        return false;
    }

    // Compare each property recursively
    for (let key of keysA) {
        if (!Object.prototype.hasOwnProperty.call(b, key)) {
            return false;
        }

        if (!deepComp(a[key], b[key])) {
            return false;
        }
    }

    return true;
}

// Test objects
let a = {
    x: [1, 2, 3, 4, 5],
    y: 0,
    z: { m: 'test', n: false }
};

let b = {
    x: [1, 2, 3, 4, 5],
    y: 0,
    z: { m: 'test', n: false }
};

let c = {
    x: [1, 2, 3, 4, 5, 6],
    y: 0,
    z: { m: 'test', n: false }
};

let d = {
    x: [1, 2, 3, 4],
    y: 0,
    z: { m: 'test', n: false }
};

let e = {
    x: [1, 2, 3, 4, 5],
    y: 0,
    z: { m: 'test', n: true }
};

let f = {
    x: [1, 2, 3, 4, 5],
    y: -1,
    z: { m: 'test', n: false }
};

// Display results
console.log(deepComp(a, b)); // true
console.log(deepComp(a, c)); // false
console.log(deepComp(a, d)); // false
console.log(deepComp(a, e)); // false
console.log(deepComp(a, f)); // false
