--Funcionários com departamento
SELECT e.name AS employee, d.name AS department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.department_id;

--Funcionários sem projetos
SELECT e.name
FROM employees e
LEFT JOIN employee_projects ep ON e.employee_id = ep.employee_id
WHERE ep.project_id IS NULL;

--Total de horas por funcionário
SELECT e.name,
       SUM(ep.hours_worked) AS total_hours
FROM employees e
LEFT JOIN employee_projects ep ON e.employee_id = ep.employee_id
GROUP BY e.employee_id;

--Projetos com mais de 100 horas
SELECT p.name,
       SUM(ep.hours_worked) AS total_hours
FROM projects p
JOIN employee_projects ep ON p.project_id = ep.project_id
GROUP BY p.project_id
HAVING total_hours > 100;

--Custo total por projeto
SELECT p.name,  
       SUM((e.salary / 160) * ep.hours_worked) AS total_cost
FROM projects p
JOIN employee_projects ep ON p.project_id = ep.project_id
JOIN employees e ON ep.employee_id = e.employee_id
GROUP BY p.project_id;

--Funcionários em mais de 1 projeto
SELECT e.name,
       COUNT(ep.project_id) AS project_count
FROM employees e
JOIN employee_projects ep ON e.employee_id = ep.employee_id
GROUP BY e.employee_id
HAVING project_count > 1;

--Projetos ativos hoje
SELECT *
FROM projects
WHERE date('now') BETWEEN start_date AND end_date;

--Aumentar salário em 10% (projetos ativos)
UPDATE employees
SET salary = salary * 1.10
WHERE employee_id IN (
    SELECT DISTINCT ep.employee_id
    FROM employee_projects ep
    JOIN projects p ON ep.project_id = p.project_id
    WHERE date('now') BETWEEN p.start_date AND p.end_date
);

