CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       login VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       avatar VARCHAR(255)
);