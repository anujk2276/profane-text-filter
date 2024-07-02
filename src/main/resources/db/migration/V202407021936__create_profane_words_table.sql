CREATE TABLE profane_words (
    id SERIAL PRIMARY KEY,
    word TEXT NOT NULL,
    profane BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);