-- Ver todos os dados
SELECT * FROM sales;

-- Total de revenue
SELECT SUM(price * quantity) AS total_revenue FROM sales;

-- Vendas por produto
SELECT product, SUM(quantity) AS total_sold
FROM sales
GROUP BY product;

-- Revenue por categoria
SELECT category, SUM(price * quantity) AS revenue
FROM sales
GROUP BY category;

-- Produto mais vendido
SELECT product, SUM(quantity) AS total_sold
FROM sales
GROUP BY product
ORDER BY total_sold DESC
LIMIT 1;

-- Vendas por mês
SELECT substr(date, 1, 7) AS month, SUM(quantity) AS total_sold
FROM sales
GROUP BY month;