//package com.example.profaneTextFilter.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import com.example.profaneTextFilter.repository.ProfaneWordRepository;
//import com.example.profaneTextFilter.model.ProfaneWord;
//
//@Service
//public class ProfaneWordService {
//
//    @Autowired
//    private ProfaneWordRepository profaneWordRepository;
//
//    public void processWords(List<String> words) {
//        for (String word : words) {
//            if (!profaneWordRepository.existsByWord(word)) {
//                ProfaneWord profaneWord = new ProfaneWord();
//                profaneWord.setWord(word);
//                profaneWord.setProfane(isProfane(word));
//                // profaneWord.setCreatedAt(LocalDateTime.now());
//                // profaneWord.setUpdatedAt(LocalDateTime.now());
//                profaneWordRepository.save(profaneWord);
//            }
//        }
//    }
//
//    private boolean isProfane(String word) {
//        // Implement your logic to determine if the word is profane
//        // For simplicity, let's assume a list of profane words
//        List<String> profaneWords = List.of("badword1", "badword2", "badword3");
//        return profaneWords.contains(word);
//    }
//}

//this is the code relevant with web purify api

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

import java.util.List;
import com.example.profaneTextFilter.repository.ProfaneWordRepository;
import com.example.profaneTextFilter.model.ProfaneWord;

@Service
public class ProfaneWordService {
    @Autowired
    private ProfaneWordRepository profaneWordRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY = "14b3d07cc74e5e80e35c95ba37a4186b";
    private static final String API_URL = "http://api1.webpurify.com/services/rest/";

    public void processWords(List<String> words) {
        for (String word : words) {
            if (!profaneWordRepository.existsByWord(word)) {
                ProfaneWord profaneWord = new ProfaneWord();
                profaneWord.setWord(word);
                profaneWord.setProfane(isProfane(word));
                profaneWordRepository.save(profaneWord);
            }
        }
    }

    private boolean isProfane(String word) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("api_key", API_KEY)
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