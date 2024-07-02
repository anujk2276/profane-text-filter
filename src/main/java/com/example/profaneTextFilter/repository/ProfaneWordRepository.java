package com.example.profaneTextFilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.profaneTextFilter.model.ProfaneWord;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfaneWordRepository extends JpaRepository<ProfaneWord, Long> {
    boolean existsByWord(String word);
}
