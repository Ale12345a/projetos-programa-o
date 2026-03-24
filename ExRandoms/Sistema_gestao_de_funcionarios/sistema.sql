CREATE TABLE departments (
    department_id INTEGER PRIMARY KEY,
    name TEXT
);

CREATE TABLE employees (
    employee_id INTEGER PRIMARY KEY,
    name TEXT,
    department_id INTEGER,
    hourly_rate REAL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

CREATE TABLE work_hours (
    id INTEGER PRIMARY KEY,
    employee_id INTEGER,
    hours INTEGER,
    work_date DATE,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

INSERT INTO departments VALUES
(1,'IT'),
(2,'Marketing'),
(3,'Financeiro'),
(4,'RH');

INSERT INTO employees VALUES
(1,'Ana Martins',1,22),
(2,'João Silva',1,20),
(3,'Maria Costa',2,18),
(4,'Pedro Santos',3,25);

--mostrar todos os funcionarios
SELECT *
FROM employees;

--Mostrar funcionários com departamento
SELECT
e.name,
d.name AS department
FROM employees e
JOIN departments d
ON e.department_id = d.department_id;

--Calcular salário total
SELECT
e.name,
SUM(w.hours * e.hourly_rate) AS salary
FROM employees e
JOIN work_hours w
ON e.employee_id = w.employee_id
GROUP BY e.name;

--Funcionário que trabalhou mais horas
SELECT TOP 1
e.name,
SUM(w.hours) AS total_hours
FROM employees e
JOIN work_hours w
ON e.employee_id = w.employee_id
GROUP BY e.name
ORDER BY total_hours DESC;

--Salário médio da empresa
SELECT
AVG(hourly_rate)
FROM employees;

--Funcionários do departamento IT
SELECT
name
FROM employees
WHERE department_id = 1;

--Total de horas por departamento
SELECT
d.name,
SUM(w.hours) AS total_hours
FROM departments d
JOIN employees e ON d.department_id = e.department_id
JOIN work_hours w ON e.employee_id = w.employee_id
GROUP BY d.name;