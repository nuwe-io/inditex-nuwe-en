CREATE TABLE IF NOT EXISTS products(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS customers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    addressx INT,
    addressy INT
);

CREATE TABLE IF NOT EXISTS lockers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    addressx INT,
    addressy INT
);

CREATE TABLE IF NOT EXISTS obstacles(
    id INT AUTO_INCREMENT PRIMARY KEY,
    addressx INT,
    addressy INT
);

CREATE TABLE IF NOT EXISTS orders(
    id INT AUTO_INCREMENT PRIMARY KEY,
    productid INT,
    customerid INT,
    lockerid INT,
    FOREIGN KEY (productid) REFERENCES products(id),
    FOREIGN KEY (customerid) REFERENCES customers(id),
    FOREIGN KEY (lockerid) REFERENCES lockers(id)
);
