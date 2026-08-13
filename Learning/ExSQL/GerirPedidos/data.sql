-- CUSTOMERS
INSERT INTO customers VALUES
(1, 'Ana Silva', 'ana@email.com', 1),
(2, 'Bruno Costa', 'bruno@email.com', 1),
(3, 'Carla Rocha', 'carla@email.com', 0);

-- PRODUCTS
INSERT INTO products VALUES
(1, 'Laptop', 1200, 10),
(2, 'Rato', 25, 50),
(3, 'Teclado', 70, 3);

-- ORDERS
INSERT INTO orders VALUES
(1, 1, '2024-11-01', 'PAID'),
(2, 1, '2024-12-01', 'SHIPPED'),
(3, 2, '2024-12-10', 'PAID');

-- ORDER ITEMS
INSERT INTO order_items VALUES
(1, 1, 1, 1, 1200),
(2, 1, 2, 2, 25),
(3, 2, 3, 1, 70),
(4, 3, 2, 5, 25);

-- SHIPMENTS
INSERT INTO shipments VALUES
(1, 2, '2024-12-02', '2024-12-05');
