-- =========================
-- CUSTOMERS
-- =========================
CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    active BOOLEAN NOT NULL
);

-- =========================
-- PRODUCTS
-- =========================
CREATE TABLE products (
    product_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    price REAL NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0)
);

-- =========================
-- ORDERS (encomenda)
-- =========================
CREATE TABLE orders (
    order_id INTEGER PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    order_date DATE NOT NULL,
    status TEXT NOT NULL CHECK (
        status IN ('PENDING', 'PAID', 'SHIPPED', 'CANCELLED')
    ),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- =========================
-- ORDER ITEMS
-- =========================
CREATE TABLE order_items (
    order_item_id INTEGER PRIMARY KEY,
    order_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    unit_price REAL NOT NULL CHECK (unit_price > 0),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- =========================
-- SHIPMENTS (envios)
-- =========================
CREATE TABLE shipments (
    shipment_id INTEGER PRIMARY KEY,
    order_id INTEGER NOT NULL,
    shipment_date DATE NOT NULL,
    delivered_date DATE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);
