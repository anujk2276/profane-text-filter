CREATE TABLE profane_words (
    id SERIAL PRIMARY KEY,
    word TEXT NOT NULL,
    profane BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Add unique index to the 'word' column in the 'profane_words' table
CREATE UNIQUE INDEX idx_word ON profane_words(word);