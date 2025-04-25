CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO products (name, price) VALUES
('Laptop', 1200.00),
('Smartphone', 799.99),
('Tablet', 450.00),
('Monitor', 199.99),
('Keyboard', 49.99);
