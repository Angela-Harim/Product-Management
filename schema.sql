
CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    creation_datetime TIMESTAMP NOT NULL
);


CREATE TABLE Product_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    product_id INT REFERENCES Product(id) ON DELETE CASCADE
);
