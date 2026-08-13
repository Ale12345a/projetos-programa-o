-- Clientes do Porto
SELECT * FROM customers WHERE city = 'Porto';

-- Encomendas com nome do cliente
SELECT o.order_id, c.name, o.order_date, o.status
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id;

-- Valor total por encomenda
SELECT o.order_id, SUM(oi.quantity * oi.unit_price) AS total_value
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id
GROUP BY o.order_id;

-- Produtos nunca vendidos
SELECT p.name
FROM products p
LEFT JOIN order_items oi ON p.product_id = oi.product_id
WHERE oi.product_id IS NULL;

-- Atualizar stock após encomenda 1
UPDATE products
SET stock = stock - (
    SELECT oi.quantity
    FROM order_items oi
    WHERE oi.product_id = products.product_id
      AND oi.order_id = 1
)
WHERE product_id IN (
    SELECT product_id
    FROM order_items
    WHERE order_id = 1
);
