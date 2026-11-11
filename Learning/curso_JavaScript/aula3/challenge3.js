
class User {
    #name;
    #surname;
    #email;

    constructor(name, surname, email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    get name() {
        return this.#name;
    }

    set name(value) {
        if (!/^[A-Z][a-zA-Z]*$/.test(value)) {
            throw new Error("Invalid name");
        }
        this.#name = value;
    }

    get surname() {
        return this.#surname;
    }

    set surname(value) {
        if (!/^[A-Z][a-zA-Z]*$/.test(value)) {
            throw new Error("Invalid surname");
        }
        this.#surname = value;
    }

    get email() {
        return this.#email;
    }

    set email(value) {
        if (!/^[a-zA-Z]+(\.[a-zA-Z]+)*@[a-zA-Z]+(\.[a-zA-Z]+)+$/.test(value)) {
            throw new Error("Invalid email");
        }
        this.#email = value;
    }
}

class Users {
    #users = new Map();

    add(name, surname, email) {
        let user = new User(name, surname, email);
        this.#users.set(email, user);
    }

    delete(email) {
        return this.#users.delete(email);
    }

    get(email) {
        return this.#users.get(email);
    }

    getAll(field) {
        if (!["name", "surname", "email"].includes(field)) {
            throw new Error("Invalid sorting field");
        }

        return Array.from(this.#users.values()).sort((a, b) =>
            a[field].localeCompare(b[field])
        );
    }
}

// Testes
let users = new Users();

users.add("Aaaa", "Bbbb", "cccc@gmail.com");
users.add("Mmmm", "Ffff", "eeee@gmail.com");
users.add("Aaaa", "Bbbb", "cccc@gmail.com");
users.add("Xxxx", "Oooo", "dddd@gmail.com");

console.log(users.get("dddd@gmail.com"));

console.log(users.getAll("name").map(u => u.name));
console.log(users.getAll("surname").map(u => u.surname));
console.log(users.getAll("email").map(u => u.email));
