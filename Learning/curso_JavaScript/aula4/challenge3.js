
function getPromiseArray(array) {
    return array.map(value => {
        return new Promise((resolve, reject) => {
            if (Number.isInteger(value) && value > 0) {
                setTimeout(() => {
                    resolve(value);
                }, value);
            } else {
                reject(new Error(`${value} is not a positive integer`));
            }
        });
    });
}

// Testes
let promises1 = getPromiseArray([10, 30, 5, 20, 'a']);

Promise.all(promises1)
    .then(a => console.log(`all: ${a}`))
    .catch(e => console.log(`all: ${e.message}`));

Promise.any(promises1)
    .then(a => console.log(`any: ${a}`))
    .catch(e => console.log(`any: ${e.message}`));
