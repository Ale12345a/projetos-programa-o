--Clientes ativos + total gasto
SELECT
    c.name,
    SUM(oi.quantity * oi.unit_price) AS total_gasto
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
WHERE c.active = 1
AND o.status IN ('PAID', 'SHIPPED')
GROUP BY c.name;

--Produtos com stock baixo
SELECT name, stock
FROM products
WHERE stock < 5;

--Valor total por encomenda
SELECT
    o.order_id,
    SUM(oi.quantity * oi.unit_price) AS total_encomenda
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id
GROUP BY o.order_id;

--Encomendas não enviadas
SELECT *
FROM orders
WHERE status != 'SHIPPED';

--Produtos mais vendidos
SELECT
    p.name,
    SUM(oi.quantity) AS total_vendido
FROM products p
JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY p.name
ORDER BY total_vendido DESC;

--Clientes sem encomendas
SELECT c.name
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;

--Tempo médio de entrega
SELECT
    AVG(JULIANDAY(delivered_date) - JULIANDAY(shipment_date)) AS avg_days
FROM shipments
WHERE delivered_date IS NOT NULL;

-- Clientes que gastaram acima da média
SELECT name
FROM (
    SELECT
        c.name,
        SUM(oi.quantity * oi.unit_price) AS total_gasto
    FROM customers c
    JOIN orders o ON c.customer_id = o.customer_id
    JOIN order_items oi ON o.order_id = oi.order_id
    GROUP BY c.name
) AS customer_totals
WHERE total_gasto > (
    SELECT AVG(total_gasto)
    FROM (
        SELECT
            SUM(oi.quantity * oi.unit_price) AS total_gasto
        FROM orders o
        JOIN order_items oi ON o.order_id = oi.order_id
        GROUP BY o.customer_id
    ) AS avg_totals
);


--Atualizar stock após envio
UPDATE products
SET stock = stock - (
    SELECT COALESCE(SUM(oi.quantity), 0)
    FROM order_items oi
    JOIN orders o ON oi.order_id = o.order_id
    WHERE oi.product_id = products.product_id
    AND o.status = 'SHIPPED'
);

--Clientes em risco
SELECT c.name
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
WHERE c.active = 1
GROUP BY c.name
HAVING
    SUM(oi.quantity * oi.unit_price) > 1000
    AND MAX(o.order_date) < DATE('now', '-6 months');

