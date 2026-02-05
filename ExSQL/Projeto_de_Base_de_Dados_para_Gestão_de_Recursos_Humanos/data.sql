INSERT INTO departments VALUES
(1, 'IT'),
(2, 'HR'),
(3, 'Finance');

INSERT INTO positions VALUES
(1, 'Junior Developer', 1200),
(2, 'Senior Developer', 2500),
(3, 'HR Manager', 2200);

INSERT INTO employees VALUES
(1, 'Ana Silva', '2022-01-10', 1, 2, 1),
(2, 'Bruno Costa', '2023-03-15', 1, 1, 1),
(3, 'Carla Rocha', '2021-06-01', 2, 3, 1);

INSERT INTO attendance VALUES
(1, 1, '2024-12-01', '09:00', '18:00'),
(2, 1, '2024-12-02', '09:10', '18:30'),
(3, 2, '2024-12-01', '09:00', '17:00'),
(4, 3, '2024-12-01', '08:50', '17:50');

INSERT INTO overtime VALUES
(1, 1, '2024-12-02', 2),
(2, 2, '2024-12-01', 1.5);
