-- Total revenue
SELECT SUM(revenue) FROM sales;

-- Revenue por categoria
SELECT category, SUM(revenue)
FROM sales
GROUP BY category;

-- Top produto por quantidade
SELECT product, SUM(quantity) as total
FROM sales
GROUP BY product
ORDER BY total DESC;

-- Média de preço por categoria
SELECT category, AVG(price)
FROM sales
GROUP BY category;

-- Vendas por mês
SELECT strftime('%Y-%m', date) as month, SUM(revenue)
FROM sales
GROUP BY month;