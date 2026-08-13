-- =========================
-- CLIENTS
-- =========================
CREATE TABLE clients (
    client_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    active BOOLEAN NOT NULL
);

-- =========================
-- CONTRACTS
-- =========================
CREATE TABLE contracts (
    contract_id INTEGER PRIMARY KEY,
    client_id INTEGER NOT NULL,
    monthly_fee REAL NOT NULL CHECK (monthly_fee > 0),
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

-- =========================
-- INVOICES(faturas)
-- =========================
CREATE TABLE invoices (
    invoice_id INTEGER PRIMARY KEY,
    contract_id INTEGER NOT NULL,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    total_amount REAL NOT NULL CHECK (total_amount > 0),
    status TEXT NOT NULL CHECK (status IN ('OPEN', 'PAID', 'OVERDUE')),
    FOREIGN KEY (contract_id) REFERENCES contracts(contract_id)
);

-- =========================
-- PAYMENTS
-- =========================
CREATE TABLE payments (
    payment_id INTEGER PRIMARY KEY,
    invoice_id INTEGER NOT NULL,
    payment_date DATE NOT NULL,
    amount REAL NOT NULL CHECK (amount > 0),
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id)
);