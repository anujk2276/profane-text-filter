package com.splashlearn.profaneTextFilter.controller;

import com.splashlearn.profaneTextFilter.service.ProfaneWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
public class ProfaneWordController {

    @Autowired
    private ProfaneWordService profaneWordService;

    @GetMapping("/process-profane-words")
    public ResponseEntity<Map<String, Boolean>> processProfaneWords(@RequestBody List<String> wordsToProcess) {
        Map<String, Boolean> result = profaneWordService.processWords(wordsToProcess);
        return ResponseEntity.ok(result);
    }
}