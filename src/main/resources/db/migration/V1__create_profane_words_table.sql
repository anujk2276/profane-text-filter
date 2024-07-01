CREATE TABLE profane_words (
    id SERIAL PRIMARY KEY,
    word TEXT NOT NULL,
    profane BOOLEAN NOT NULL
);