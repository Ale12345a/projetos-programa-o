-- Clientes
CREATE TABLE Clientes (
    id INT PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50),
    cidade VARCHAR(50)
);

-- Produtos
CREATE TABLE Produtos (
    id INT PRIMARY KEY,
    nome VARCHAR(50),
    categoria VARCHAR(50),
    preco DECIMAL(10,2),
    estoque INT
);

-- Pedidos
CREATE TABLE Pedidos (
    id INT PRIMARY KEY,
    cliente_id INT,
    data DATE,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

-- ItensPedido
CREATE TABLE ItensPedido (
    id INT PRIMARY KEY,
    pedido_id INT,
    produto_id INT,
    quantidade INT,
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);

-- Pagamentos
CREATE TABLE Pagamentos (
    id INT PRIMARY KEY,
    pedido_id INT,
    valor_pago DECIMAL(10,2),
    metodo VARCHAR(20),
    data_pagamento DATE,
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(id)
);

-- Inserção de dados
INSERT INTO Clientes (id, nome, email, cidade) VALUES
(1, 'Alice', 'alice@email.com', 'Lisboa'),
(2, 'Bruno', 'bruno@email.com', 'Porto'),
(3, 'Carla', 'carla@email.com', 'Lisboa');

INSERT INTO Produtos (id, nome, categoria, preco, estoque) VALUES
(1, 'Teclado', 'Informatica', 25, 50),
(2, 'Rato', 'Informatica', 15, 100),
(3, 'Caneta', 'Escritorio', 2, 500),
(4, 'Caderno', 'Escritorio', 5, 200),
(5, 'Monitor', 'Informatica', 150, 30);

INSERT INTO Pedidos (id, cliente_id, data) VALUES
(1, 1, '2025-12-01'),
(2, 2, '2025-12-05'),
(3, 1, '2025-12-10'),
(4, 3, '2025-12-12');

INSERT INTO ItensPedido (id, pedido_id, produto_id, quantidade) VALUES
(1, 1, 1, 2),
(2, 1, 2, 1),
(3, 2, 3, 10),
(4, 3, 5, 1),
(5, 4, 4, 5),
(6, 4, 3, 20);

INSERT INTO Pagamentos (id, pedido_id, valor_pago, metodo, data_pagamento) VALUES
(1, 1, 65, 'Cartao', '2025-12-02'),
(2, 2, 20, 'Paypal', '2025-12-06'),
(3, 3, 150, 'Boleto', '2025-12-11'),
(4, 4, 50, 'Cartao', '2025-12-13');

--exercicio 1 -> Total gasto por cliente (considerando pagamentos reais)
SELECT c.nome, 
       SUM(pag.valor_pago) AS total_gasto
FROM Clientes c
JOIN pedidos ped ON c.id = ped.cliente_id
JOIN Pagamentos pag ON ped.id = pag.pedido_id
GROUP BY c.id, c.nome;

--Exercicio 2: Produtos com estoque crítico -> Listar produtos onde estoque < 20 unidades.
SELECT id, nome, categoria, preco, estoque
FROM Produtos
WHERE estoque < 20;

--Exercicio 3: Pagamentos por método -> Calcular total arrecadado por cada método de pagamento (Cartão, Paypal, Boleto)
SELECT metodo,
       SUM(valor_pago) AS total_arrecadado
FROM Pagamentos
GROUP BY metodo;

--exercicio 4: Clientes que compraram produtos de mais de uma categoria
SELECT c.nome
FROM clientes c
JOIN Pedidos ped ON c.id = ped.cliente_id
JOIN ItensPedido ip ON ped.id = ip.pedido_id
JOIN Produtos p ON ip.produto_id = p.id
GROUP BY c.id, c.nome
HAVING COUNT(DISTINCT p.categoria) >= 2;

--Exercício 5: Produto mais vendido por categoria
SELECT t.categoria, t.nome AS produto, t.total_vendido
FROM (
    SELECT p.categoria, p.nome, SUM(ip.quantidade) AS total_vendido
    FROM Produtos p
    JOIN ItensPedido ip ON p.id = ip.produto_id
    GROUP BY p.categoria, p.id, p.nome
) t
WHERE t.total_vendido = (
    SELECT MAX(t2.total_vendido)
    FROM (
        SELECT p2.categoria, p2.id, SUM(ip2.quantidade) AS total_vendido
        FROM Produtos p2
        JOIN ItensPedido ip2 ON p2.id = ip2.produto_id
        WHERE p2.categoria = t.categoria
        GROUP BY p2.id
    ) t2
);

--Exercício 6: Relatório mensal de faturamento -> Para cada mês, mostrar: - mês/ano - total vendido (somando valor_pago) -Número de pedidos realizados
SELECT 
    strftime('%Y-%m', ped.data) AS mes_ano,
    SUM(pag.valor_pago) AS total_vendido,
    COUNT(DISTINCT ped.id) AS numero_pedidos
FROM Pedidos ped
JOIN Pagamentos pag ON ped.id = pag.pedido_id
GROUP BY mes_ano
ORDER BY mes_ano;

