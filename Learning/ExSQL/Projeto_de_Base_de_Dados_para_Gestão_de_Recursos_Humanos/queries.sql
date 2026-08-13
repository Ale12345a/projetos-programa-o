--Total de horas trabalhadas por funcionário
SELECT
    e.name,
    SUM(
        JULIANDAY('2000-01-01 ' || check_out) -
        JULIANDAY('2000-01-01 ' || check_in)
    ) * 24 AS total_hours
FROM attendance a
JOIN employees e ON a.employee_id = e.employee_id
GROUP BY e.name;

--Horas extra por funcionário
SELECT
    e.name,
    COALESCE(SUM(o.hours), 0) AS overtime_hours
FROM employees e
LEFT JOIN overtime o ON e.employee_id = o.employee_id
GROUP BY e.name;

--Salário total mensal (base + extras) Assumindo: 160h mensais; hora extra = +50%
SELECT
    e.name,
    p.base_salary +
    COALESCE(SUM(o.hours) * (p.base_salary / 160) * 1.5, 0)
    AS total_salary
FROM employees e
JOIN positions p ON e.position_id = p.position_id
LEFT JOIN overtime o ON e.employee_id = o.employee_id
GROUP BY e.name;

--Custo total por departamento
SELECT
    d.name,
    SUM(
        p.base_salary +
        COALESCE(o.hours * (p.base_salary / 160) * 1.5, 0)
    ) AS department_cost
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN positions p ON e.position_id = p.position_id
LEFT JOIN overtime o ON e.employee_id = o.employee_id
GROUP BY d.name;

--Funcionários com mais de 10h extra no mês
SELECT
    e.name,
    SUM(o.hours) AS overtime_hours
FROM employees e
JOIN overtime o ON e.employee_id = o.employee_id
GROUP BY e.name
HAVING SUM(o.hours) > 10;

--Funcionários sem qualquer hora extra
SELECT e.name
FROM employees e
LEFT JOIN overtime o ON e.employee_id = o.employee_id
WHERE o.overtime_id IS NULL;

--Ranking de funcionários por custo
SELECT
    e.name,
    p.base_salary +
    COALESCE(SUM(o.hours) * (p.base_salary / 160) * 1.5, 0)
    AS total_cost
FROM employees e
JOIN positions p ON e.position_id = p.position_id
LEFT JOIN overtime o ON e.employee_id = o.employee_id
GROUP BY e.name
ORDER BY total_cost DESC;

