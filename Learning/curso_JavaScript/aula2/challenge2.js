
// Simulate sending an email
function sendEmail(from, to, message) {
    // Email sending is simulated
}

// Base User class
class User {
    constructor({ name, surname, email, role }) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.courses = [];
        this.messages = [];
    }

    addCourse(course, level) {
        this.courses.push({
            course: course,
            level: level
        });
    }

    removeCourse(course) {
        this.courses = this.courses.filter(
            item => item.course !== course
        );
    }

    editCourse(course, level) {
        let foundCourse = this.courses.find(
            item => item.course === course
        );

        if (foundCourse) {
            foundCourse.level = level;
        } else {
            this.addCourse(course, level);
        }
    }

    sendMessage(to, message) {
        sendEmail(this.email, to.email, message);

        to.messages.push({
            from: this.email,
            to: to.email,
            message: message
        });
    }

    showMessagesHistory() {
        this.messages.forEach(msg => {
            console.log(`${msg.to} -> ${msg.from}: ${msg.message}`);
        });
    }
}

// ExtendedUser inherits from User
class ExtendedUser extends User {
    get fullName() {
        return `${this.name} ${this.surname}`;
    }

    set fullName(value) {
        let names = value.split(' ');
        this.name = names[0];
        this.surname = names.slice(1).join(' ');
    }
}

// Teacher inherits from ExtendedUser
class Teacher extends ExtendedUser {
    constructor({ name, surname, email }) {
        super({
            name: name,
            surname: surname,
            email: email,
            role: 'teacher'
        });
    }
}

// Student inherits from ExtendedUser
class Student extends ExtendedUser {
    constructor({ name, surname, email }) {
        super({
            name: name,
            surname: surname,
            email: email,
            role: 'student'
        });
    }
}

// Test the solution
let student1 = new Student({
    name: 'Rafael',
    surname: 'Fife',
    email: 'rfife@rhyta.com'
});

let student2 = new Student({
    name: 'Kelly',
    surname: 'Estes',
    email: 'k_estes@dayrep.com'
});

let teacher1 = new Teacher({
    name: 'Paula',
    surname: 'Thompkins',
    email: 'PaulaThompkins@jourrapide.com'
});

student1.addCourse('maths', 2);
teacher1.addCourse('biology', 3);
teacher1.editCourse('chemistry', 4);

console.log(`${student1.fullName}: ${student1.courses.length} courses`);
console.log(`${teacher1.fullName}: ${teacher1.courses.length} courses`);

student1.fullName = 'Rafael Fifer';

console.log(`${student1.fullName}: ${student1.courses.length} courses`);
