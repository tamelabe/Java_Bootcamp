DROP TABLE product IF EXISTS;
CREATE TABLE IF NOT EXISTS product (
    identifier INT PRIMARY KEY IDENTITY,
    name VARCHAR(20) NOT NULL,
    price REAL NOT NULL
);