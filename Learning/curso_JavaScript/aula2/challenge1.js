
// Simulate sending an email
function sendEmail(from, to, message) {
    // Email sending is simulated
}

// User class
class User {
    constructor({ name, surname, email, role }) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.courses = [];
        this.messages = [];
    }

    // Add a course
    addCourse(course, level) {
        this.courses.push({
            course: course,
            level: level
        });
    }

    // Remove a course
    removeCourse(course) {
        this.courses = this.courses.filter(
            item => item.course !== course
        );
    }

    // Edit a course level
    editCourse(course, level) {
        let foundCourse = this.courses.find(
            item => item.course === course
        );

        if (foundCourse) {
            foundCourse.level = level;
        }
    }

    // Send a message to another user
    sendMessage(to, message) {
        sendEmail(this.email, to.email, message);

        to.messages.push({
            from: this.email,
            to: to.email,
            message: message
        });
    }

    // Display received messages
    showMessagesHistory() {
        this.messages.forEach(msg => {
            console.log(`${msg.to} -> ${msg.from}: ${msg.message}`);
        });
    }
}

// Test the solution
let student1 = new User({
    name: 'Rafael',
    surname: 'Fife',
    email: 'rfife@rhyta.com',
    role: 'student'
});

let student2 = new User({
    name: 'Kelly',
    surname: 'Estes',
    email: 'k_estes@dayrep.com',
    role: 'student'
});

let teacher1 = new User({
    name: 'Paula',
    surname: 'Thompkins',
    email: 'PaulaThompkins@jourrapide.com',
    role: 'teacher'
});

student1.addCourse('maths', 2);
student1.addCourse('physics', 1);
student1.removeCourse('physics');

teacher1.addCourse('biology', 3);
teacher1.editCourse('biology', 4);

console.log(`${student1.name}: ${student1.courses.length} courses`);
console.log(`${teacher1.name}: ${teacher1.courses.length} courses`);

teacher1.sendMessage(student1, 'test message');
teacher1.sendMessage(student1, 'another message');

teacher1.showMessagesHistory();
