package com.splashlearn.profaneTextFilter.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profane_words")
public class ProfaneWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private boolean profane;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isProfane() {
        return profane;
    }

    public void setProfane(boolean profane) {
        this.profane = profane;
    }
}

