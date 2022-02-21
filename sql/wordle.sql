CREATE TABLE original_word_list (
    id SERIAL PRIMARY KEY,
    name VARCHAR(5),
    probability numeric,
    score numeric
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50), UNIQUE
    email VARCHAR(50), UNIQUE
    username VARCHAR(50) UNIQUE 
);