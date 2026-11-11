
class MyIterable {
    constructor() {
        this.elements = [];
    }

    add(element) {
        if (!this.has(element)) {
            this.elements.push(element);
        }
    }

    has(element) {
        return this.elements.includes(element);
    }

    del(element) {
        let index = this.elements.indexOf(element);

        if (index !== -1) {
            this.elements.splice(index, 1);
        }
    }

    get length() {
        return this.elements.length;
    }

    *[Symbol.iterator]() {
        for (let element of this.elements) {
            yield element;
        }
    }
}

// Testes
let iterable = new MyIterable();

iterable.add(2);
iterable.add(5);
iterable.add(3);
iterable.add(2);

iterable.del(3);

console.log(iterable.length); // 2
console.log(iterable.has(2)); // true
console.log(iterable.has(3)); // false
console.log(...iterable);     // 2 5

// Testar for...of
for (let element of iterable) {
    console.log(element);
}
