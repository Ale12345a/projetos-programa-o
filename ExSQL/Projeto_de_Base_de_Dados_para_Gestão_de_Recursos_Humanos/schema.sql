CREATE TABLE departments (
    department_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE positions (
    position_id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    base_salary REAL NOT NULL CHECK (base_salary > 0)
);

CREATE TABLE employees (
    employee_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    hire_date DATE NOT NULL,
    department_id INTEGER NOT NULL,
    position_id INTEGER NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (position_id) REFERENCES positions(position_id)
);

CREATE TABLE attendance (
    attendance_id INTEGER PRIMARY KEY,
    employee_id INTEGER NOT NULL,
    date DATE NOT NULL,
    check_in TIME NOT NULL,
    check_out TIME NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE overtime (
    overtime_id INTEGER PRIMARY KEY,
    employee_id INTEGER NOT NULL,
    date DATE NOT NULL,
    hours REAL NOT NULL CHECK (hours > 0),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
