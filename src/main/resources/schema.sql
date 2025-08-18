-- MySQL schema
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    account_no INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    phone VARCHAR(15),
    units_consumed INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    price_per_unit DECIMAL(10,2) NOT NULL
);

-- Seed a default user (change password in production)
INSERT INTO users(username,password,role) VALUES('admin','admin','ADMIN')
ON DUPLICATE KEY UPDATE username=username;
