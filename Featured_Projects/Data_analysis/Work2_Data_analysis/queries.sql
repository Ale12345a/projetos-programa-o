-- Total Revenue
SELECT SUM(revenue) FROM sales;

-- Revenue per Category
SELECT category, SUM(revenue) FROM sales GROUP BY category;

-- Top Products by Revenue
SELECT product, SUM(revenue) as total FROM sales GROUP BY product ORDER BY total DESC;

-- Average Price per Category
SELECT category, AVG(price) FROM sales GROUP BY category;

-- Revenue per Month
SELECT strftime('%Y-%m', date) as month, SUM(revenue) FROM sales GROUP BY month;

-- Forecast preparation (only for reference)
SELECT month, SUM(revenue) FROM (
    SELECT strftime('%Y-%m', date) as month, revenue FROM sales
) GROUP BY month;