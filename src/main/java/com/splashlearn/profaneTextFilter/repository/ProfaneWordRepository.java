package com.splashlearn.profaneTextFilter.repository;

import com.splashlearn.profaneTextFilter.model.ProfaneWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfaneWordRepository extends JpaRepository<ProfaneWord, Long> {
    Optional<ProfaneWord> findByWord(String word);
}
