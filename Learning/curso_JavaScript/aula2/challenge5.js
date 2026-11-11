
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
            message
        });
    }

    showMessagesHistory() {
        this.messages.forEach(msg => {
            console.log(`${msg.from} -> ${msg.to}: ${msg.message}`);
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

        if (course !== undefined) {
            return matches.find(item => item.course === course);
        }

        return matches;
    }
}

// Teacher class
class Teacher extends ExtendedUser {
    constructor({ name, surname, email }) {
        super({ name, surname, email, role: 'teacher' });
    }
}

// Student class
class Student extends ExtendedUser {
    constructor({ name, surname, email }) {
        super({ name, surname, email, role: 'student' });
    }
}

// Simulate sending an email
function sendEmail(from, to, message) {
    // Email sending is simulated
}

// Tutoring class
class Tutoring {
    constructor() {
        this.students = [];
        this.teachers = [];
    }

    getStudentByName(name, surname) {
        return this.students.find(
            student => student.name === name &&
                       student.surname === surname
        );
    }

    getTeacherByName(name, surname) {
        return this.teachers.find(
            teacher => teacher.name === name &&
                       teacher.surname === surname
        );
    }

    getStudentsForTeacher(teacher) {
        return this.students.filter(
            student => ExtendedUser.match(teacher, student).length > 0
        );
    }

    getTeacherForStudent(student) {
        return this.teachers.filter(
            teacher => ExtendedUser.match(teacher, student).length > 0
        );
    }

    addStudent(name, surname, email) {
        this.students.push(new Student({ name, surname, email }));
    }

    addTeacher(name, surname, email) {
        this.teachers.push(new Teacher({ name, surname, email }));
    }
}

// ExtendedTutoring class
class ExtendedTutoring extends Tutoring {
    sendMessages(from, to, message) {
        for (let user of to) {
            from.sendMessage(user, message);
        }
    }
}

// Test the solution
let tutoring = new ExtendedTutoring();

tutoring.addStudent('Rafael', 'Fife', 'rfife@rhyta.com');
tutoring.addStudent('Kelly', 'Estes', 'k_estes@dayrep.com');

tutoring.addTeacher(
    'Paula',
    'Thompkins',
    'PaulaThompkins@jourrapide.com'
);

let to = [];

to.push(tutoring.getStudentByName('Rafael', 'Fife'));
to.push(tutoring.getStudentByName('Kelly', 'Estes'));

tutoring.sendMessages(
    tutoring.getTeacherByName('Paula', 'Thompkins'),
    to,
    'test message'
);

for (let user of to) {
    user.showMessagesHistory();
}
