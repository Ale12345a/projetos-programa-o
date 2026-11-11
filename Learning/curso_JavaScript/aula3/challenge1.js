
// Function to generate random integers
function getRandomSet(m, n, canRepeat, shouldSort) {
    let numbers = new Set();

    // Generate random numbers
    while (numbers.size < m) {
        let randomNumber = Math.floor(Math.random() * (n + 1));

        if (canRepeat) {
            // Repeated numbers are allowed
            numbers.add(randomNumber);
        } else {
            // Only unique numbers are allowed
            numbers.add(randomNumber);
        }
    }

    // Convert Set to Array
    let result = Array.from(numbers);

    // If repetitions are allowed, generate an array instead
    if (canRepeat) {
        result = [];

        for (let i = 0; i < m; i++) {
            result.push(Math.floor(Math.random() * (n + 1)));
        }
    }

    // Sort the array if requested
    if (shouldSort) {
        result.sort((a, b) => a - b);
    }

    return result;
}

// Tests
console.log(getRandomSet(10, 20, false, false));
console.log(getRandomSet(10, 20, false, true));
console.log(getRandomSet(10, 20, true, false));
console.log(getRandomSet(10, 20, true, true));
