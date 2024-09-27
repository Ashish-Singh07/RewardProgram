DROP TABLE IF EXISTS transaction;
CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_name VARCHAR(255) NOT NULL,
    transaction_amount DECIMAL(10,2) NOT NULL,
    transaction_date DATE NOT NULL
);
