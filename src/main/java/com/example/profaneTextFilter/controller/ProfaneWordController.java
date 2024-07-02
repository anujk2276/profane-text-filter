package com.example.profaneTextFilter.controller;

import com.example.profaneTextFilter.service.ProfaneWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProfaneWordController {

    @Autowired
    private ProfaneWordService profaneWordService;

    @GetMapping("/process-profane-words")
    public String processProfaneWords() {
        List<String> wordsToProcess = fetchWordsToProcess();
        profaneWordService.processWords(wordsToProcess);
        return "Profane words processed successfully";
    }

    private List<String> fetchWordsToProcess() {
        // Implement logic to fetch words that need to be processed
        // For example, from a file, database, or API
        return List.of("sample3", "sample4");
    }
}