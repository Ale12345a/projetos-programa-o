-- CLIENTS
INSERT INTO clients VALUES
(1, 'Empresa Alpha', 'alpha@email.com', 1),
(2, 'Empresa Beta', 'beta@email.com', 1),
(3, 'Empresa Gamma', 'gamma@email.com', 0);

-- CONTRACTS
INSERT INTO contracts VALUES
(1, 1, 500, '2024-01-01', NULL),
(2, 1, 300, '2024-06-01', NULL),
(3, 2, 700, '2023-01-01', NULL);

-- INVOICES
INSERT INTO invoices VALUES
(1, 1, '2024-12-01', '2024-12-10', 500, 'OPEN'),
(2, 1, '2024-11-01', '2024-11-10', 500, 'PAID'),
(3, 2, '2024-12-01', '2024-12-05', 300, 'OVERDUE'),
(4, 3, '2024-12-01', '2024-12-15', 700, 'OPEN');

-- PAYMENTS
INSERT INTO payments VALUES
(1, 2, '2024-11-05', 500),
(2, 3, '2024-12-06', 100);
