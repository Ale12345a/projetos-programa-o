--Clientes ativos + valor mensal contratado
SELECT
    c.name,
    SUM(ct.monthly_fee) AS total_mensal
FROM clients c
JOIN contracts ct ON c.client_id = ct.client_id
WHERE c.active = 1
GROUP BY c.name;

--Contratos ativos hoje
SELECT *
FROM contracts
WHERE start_date <= DATE('now')
AND (end_date IS NULL OR end_date >= DATE('now'));

--Valor em dívida por fatura
SELECT
    i.invoice_id,
    i.total_amount - COALESCE(SUM(p.amount), 0) AS amount_due
FROM invoices i
LEFT JOIN payments p ON i.invoice_id = p.invoice_id
GROUP BY i.invoice_id;

--Faturas em atraso com dias de atraso
SELECT
    c.name,
    i.invoice_id,
    JULIANDAY('now') - JULIANDAY(i.due_date) AS days_late,
    i.total_amount - COALESCE(SUM(p.amount), 0) AS amount_due
FROM invoices i
JOIN contracts ct ON i.contract_id = ct.contract_id
JOIN clients c ON ct.client_id = c.client_id
LEFT JOIN payments p ON i.invoice_id = p.invoice_id
WHERE i.status = 'OVERDUE'
GROUP BY i.invoice_id;

--Clientes com mais de 2 faturas em atraso
SELECT
    c.name,
    COUNT(*) AS overdue_invoices
FROM invoices i
JOIN contracts ct ON i.contract_id = ct.contract_id
JOIN clients c ON ct.client_id = c.client_id
WHERE i.status = 'OVERDUE'
GROUP BY c.name
HAVING COUNT(*) > 2;

--Faturação mensal (pagas)
SELECT
    strftime('%Y-%m', invoice_date) AS month,
    SUM(total_amount) AS total_faturado
FROM invoices
WHERE status = 'PAID'
GROUP BY month;

--Top 5 clientes por faturação
--SELECT
  --  c.name,
    --SUM(i.total_amount) AS total
--FROM invoices i
--JOIN contracts ct ON i.contract_id = ct.contract_id
--JOIN clients c ON ct.client_id = c.client_id
--WHERE i.status = 'PAID'
--GROUP BY c.name
--ORDER BY total DESC
--LIMIT 5;

--Clientes que nunca pagaram atrasado
SELECT c.name
FROM clients c
WHERE NOT EXISTS (
    SELECT 1
    FROM invoices i
    JOIN contracts ct ON i.contract_id = ct.contract_id
    WHERE ct.client_id = c.client_id
    AND i.status = 'OVERDUE'
);

--Atualizar estado das faturas
-- Marcar como PAID
UPDATE invoices
SET status = 'PAID'
WHERE invoice_id IN (
    SELECT i.invoice_id
    FROM invoices i
    LEFT JOIN payments p ON i.invoice_id = p.invoice_id
    GROUP BY i.invoice_id
    HAVING i.total_amount - COALESCE(SUM(p.amount), 0) = 0
);

-- Marcar como OVERDUE
UPDATE invoices
SET status = 'OVERDUE'
WHERE due_date < DATE('now')
AND status != 'PAID';

--Clientes em risco
SELECT DISTINCT c.name
FROM clients c
JOIN contracts ct ON c.client_id = ct.client_id
JOIN invoices i ON ct.contract_id = i.contract_id
LEFT JOIN payments p ON i.invoice_id = p.invoice_id
WHERE c.active = 1
AND i.status = 'OVERDUE'
AND (JULIANDAY('now') - JULIANDAY(i.due_date)) > 30
GROUP BY c.name
HAVING SUM(i.total_amount - COALESCE(p.amount, 0)) > 500;

