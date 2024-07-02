package com.example.profaneTextFilter.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.profaneTextFilter.service.ProfaneWordService;
import java.util.List;

@Component
public class ProfaneWordScheduler {

    @Autowired
    private ProfaneWordService profaneWordService;

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression means midnight every day
    public void scheduleProfaneWordProcessing() {
        List<String> wordsToProcess = fetchWordsToProcess();
        profaneWordService.processWords(wordsToProcess);
    }

    private List<String> fetchWordsToProcess() {
        // Implement logic to fetch words that need to be processed
        // For example, from a file, database, or API
        return List.of("exampleWord1", "exampleWord2");
    }
}
