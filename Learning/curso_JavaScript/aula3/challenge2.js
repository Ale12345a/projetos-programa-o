
class User {
    #firstName;
    #lastName;
    #email;

    constructor(firstName, lastName, email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    get firstName() {
        return this.#firstName;
    }

    set firstName(value) {
        if (!/^[A-Z][a-zA-Z]*$/.test(value)) {
            throw new Error("Invalid first name");
        }
        this.#firstName = value;
    }

    get lastName() {
        return this.#lastName;
    }

    set lastName(value) {
        if (!/^[A-Z][a-zA-Z]*$/.test(value)) {
            throw new Error("Invalid last name");
        }
        this.#lastName = value;
    }

    get email() {
        return this.#email;
    }

    set email(value) {
        if (!/^[a-zA-Z]+(\.[a-zA-Z]+)*@[a-zA-Z]+(\.[a-zA-Z]+)+$/.test(value)) {
            throw new Error("Invalid email address");
        }
        this.#email = value;
    }
}

try {
    let user1 = new User('Aaaa', 'Bbbb', 'Aaaa@gmail.com');
    console.log(user1);

    let user2 = new User('aaaa', 'Bbbb', 'Aaaa@gmail.com');
} catch(err) {
    console.log(err.message);
}
