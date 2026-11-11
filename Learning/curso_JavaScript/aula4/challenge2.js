
function myDecorator(fn) {
    let calls = [];

    return function(...args) {
        let alreadyUsed = calls.some(call =>
            call.length === args.length &&
            call.every((value, index) => value === args[index])
        );

        if (alreadyUsed) {
            console.log(`arguments already used: ${args}`);
        } else {
            calls.push(args);
        }

        return fn(...args);
    };
}

// Função original
let sum = function(...args) {
    let retVal = 0;

    for (let arg of args) {
        retVal += arg;
    }

    return retVal;
};

// Aplicar o decorator
let dfn = myDecorator(sum);

// Testes
console.log(dfn(2, 3, 4));
console.log(dfn(4, 5));
console.log(dfn(2, 3, 4));
console.log(dfn(4, 5));
