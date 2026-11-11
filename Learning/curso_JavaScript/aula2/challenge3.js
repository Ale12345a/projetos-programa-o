
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
        this.courses.push({ course, level });
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

// ExtendedUser class
class ExtendedUser extends User {
    get fullName() {
        return `${this.name} ${this.surname}`;
    }

    set fullName(value) {
        let names = value.split(' ');
        this.name = names[0];
        this.surname = names.slice(1).join(' ');
    }

    // Static match method
    static match(teacher, student, course) {
        let matches = [];

        student.courses.forEach(studentCourse => {
            let teacherCourse = teacher.courses.find(
                item => item.course === studentCourse.course
            );

            if (teacherCourse &&
                teacherCourse.level >= studentCourse.level) {

                matches.push({
                    course: studentCourse.course,
                    level: studentCourse.level
                });
            }
        });

        // If a course name was provided
        if (course !== undefined) {
            return matches.find(item => item.course === course);
        }

        return matches;
    }
}

// Teacher class
class Teacher extends ExtendedUser {
    constructor({ name, surname, email }) {
        super({
            name,
            surname,
            email,
            role: 'teacher'
        });
    }
}

// Student class
class Student extends ExtendedUser {
    constructor({ name, surname, email }) {
        super({
            name,
            surname,
            email,
            role: 'student'
        });
    }
}

// Simulate sending an email
function sendEmail(from, to, message) {
    // Email sending is simulated
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
student1.addCourse('physics', 4);

teacher1.addCourse('maths', 4);

let match = ExtendedUser.match(teacher1, student1);
console.log(match);

teacher1.editCourse('maths', 1);

match = ExtendedUser.match(teacher1, student1);
console.log(match);

teacher1.addCourse('physics', 4);

match = ExtendedUser.match(teacher1, student1, 'physics');
console.log(match);
