package com.example.profaneTextFilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.profaneTextFilter.model.ProfaneWord;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfaneWordRepository extends JpaRepository<ProfaneWord, Long> {
    Optional<ProfaneWord> findByWord(String word);
}
