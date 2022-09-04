CREATE TABLE if not exists users (
  id SERIAL PRIMARY KEY,
  name varchar(255) UNIQUE,
  email varchar(255) UNIQUE,
  password TEXT
);