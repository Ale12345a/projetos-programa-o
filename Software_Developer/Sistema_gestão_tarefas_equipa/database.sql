CREATE TABLE employees (
employee_id INTEGER PRIMARY KEY,
name TEXT,
department TEXT
);

CREATE TABLE tasks (
task_id INTEGER PRIMARY KEY,
title TEXT,
employee_id INTEGER,
priority TEXT,
status TEXT,
created_at DATE
);

--perguntas:
--Funcionário com mais tarefas
SELECT
e.name,
COUNT(t.task_id) AS total_tasks
FROM employees e
JOIN tasks t
ON e.employee_id = t.employee_id
GROUP BY e.name
ORDER BY total_tasks DESC;

--Número de tarefas por prioridade
SELECT
priority,
COUNT(*) AS total
FROM tasks
GROUP BY priority;

--Tarefas concluídas
SELECT *
FROM tasks
WHERE status = 'Done';