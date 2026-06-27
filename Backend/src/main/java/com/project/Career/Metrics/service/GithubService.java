package com.project.Career.Metrics.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GithubService {

    private final WebClient webClient;

    public GithubService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.github.com")
                .build();
    }

    public Map<String, Object> fetchGitHubData(String username) {

        Map<String, Object> result = new HashMap<>();

        // Fetch user profile
        Map user = webClient.get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Fetch repositories
        List<Map> repos = webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        if (repos == null) repos = new ArrayList<>();

        int totalStars = 0;
        Map<String, Integer> languageCount = new HashMap<>();

        for (Map repo : repos) {

            Integer stars = (Integer) repo.get("stargazers_count");

            if (stars != null)
                totalStars += stars;

            String language = (String) repo.get("language");

            if (language != null) {
                languageCount.put(language,
                        languageCount.getOrDefault(language, 0) + 1);
            }
        }

        // Detect main language
        String mainLanguage = languageCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");

        result.put("username", user.get("login"));
        result.put("followers", user.get("followers"));
        result.put("repositories", repos.size());
        result.put("stars", totalStars);
        result.put("mainLanguage", mainLanguage);
        result.put("avatar", user.get("avatar_url"));
        result.put("bio", user.get("bio"));

        return result;
    }
}