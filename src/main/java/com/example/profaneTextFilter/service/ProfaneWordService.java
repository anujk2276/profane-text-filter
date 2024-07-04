package com.example.profaneTextFilter.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.profaneTextFilter.repository.ProfaneWordRepository;
import com.example.profaneTextFilter.model.ProfaneWord;
import com.example.profaneTextFilter.config.WebPurifyConfig;

@Service
public class ProfaneWordService {
    @Autowired
    private ProfaneWordRepository profaneWordRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebPurifyConfig webPurifyConfig;


    public Map<String, Boolean> processWords(List<String> words) {
        Map<String, Boolean> response = new HashMap<>();

        for (String word : words) {
            Optional<ProfaneWord> existingWord = profaneWordRepository.findByWord(word);
            if (existingWord.isPresent()) {
                response.put(word, existingWord.get().isProfane());
            } else {
                boolean isProfane = isProfane(word);
                ProfaneWord profaneWord = new ProfaneWord();
                profaneWord.setWord(word);
                profaneWord.setProfane(isProfane);
                profaneWord.setCreatedAt(LocalDateTime.now());
                profaneWord.setUpdatedAt(LocalDateTime.now());
                profaneWordRepository.save(profaneWord);
                response.put(word, isProfane);
            }
        }

        return response;
    }


    private boolean isProfane(String word) {
        String apiKey = webPurifyConfig.getApiKey();
        String apiUrl = webPurifyConfig.getApiUrl();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("text", word)
                .queryParam("format", "json");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Assuming the API response is in JSON format and contains a field "found" indicating profanity
            // You need to parse the JSON response to extract this information
            String responseBody = response.getBody();
            // Parse the JSON response and check if the word is profane
            return parseResponse(responseBody);
        } else {
            // Handle the error case
            return false;
        }
    }

    private boolean parseResponse(String responseBody) {
        // Implement JSON parsing logic to check if the word is profane
        // This is a placeholder implementation and should be replaced with actual parsing logic
        // For example, using Jackson or any other JSON parsing library
        if (responseBody.contains("\"found\":\"0\"")) {
            return false; // No profanity found
        } else {
            return true; // Profanity found or other condition
        }
    }
}