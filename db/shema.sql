CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name TEXT
);

CREATE TABLE candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   photo_id integer
);

CREATE TABLE photo (
   id SERIAL PRIMARY KEY,
   name TEXT
);

CREATE TABLE users (
    id serial primary key,
    name text,
    email text,
    password text
)