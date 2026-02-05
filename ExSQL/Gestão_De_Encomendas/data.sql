INSERT INTO customers VALUES
(1, 'Ana Silva', 'ana@email.com', 'Porto'),
(2, 'Bruno Costa', 'bruno@email.com', 'Lisboa'),
(3, 'Carla Mendes', 'carla@email.com', 'Porto');

INSERT INTO products VALUES
(1, 'Teclado', 25.00, 100),
(2, 'Rato', 15.00, 200),
(3, 'Monitor', 150.00, 50),
(4, 'Webcam', 80.00, 30);

INSERT INTO orders VALUES
(1, 1, '2025-01-10', 'PAID'),
(2, 2, '2025-01-12', 'SHIPPED'),
(3, 1, '2025-01-15', 'PENDING');

INSERT INTO order_items VALUES
(1, 1, 1, 2, 25.00),
(2, 1, 2, 1, 15.00),
(3, 2, 3, 1, 150.00),
(4, 3, 2, 2, 15.00);
