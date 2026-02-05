INSERT INTO departments VALUES
(1, 'Desenvolvimento'),
(2, 'Recursos Humanos'),
(3, 'Financeiro');

INSERT INTO employees VALUES
(1, 'Ana Silva', 'ana@empresa.com', 2000, 1),
(2, 'Bruno Costa', 'bruno@empresa.com', 1800, 1),
(3, 'Carla Mendes', 'carla@empresa.com', 1500, 2),
(4, 'Daniel Rocha', 'daniel@empresa.com', 2200, 1);

INSERT INTO projects VALUES
(1, 'Sistema Interno', '2025-01-01', '2025-06-30'),
(2, 'Website Corporativo', '2025-02-01', '2025-04-30'),
(3, 'Migração de Dados', '2024-10-01', '2025-01-31');

INSERT INTO employee_projects VALUES
(1, 1, 120),
(1, 2, 40),
(2, 1, 80),
(4, 1, 100),
(3, 3, 60);
