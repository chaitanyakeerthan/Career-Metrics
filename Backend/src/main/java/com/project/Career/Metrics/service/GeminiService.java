package com.project.Career.Metrics.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }

    public String analyzeResume(String resumeText, String jobDescription) {

        String prompt = """
You are an ATS resume analyzer.

Compare the resume with the job description.

Resume:
%s

Job Description:
%s

Return ONLY JSON:

{
"atsScore": number,
"missingSkills": [],
"suggestions": []
}
""".formatted(resumeText, jobDescription);

        Map<String,Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String,Object> part = new HashMap<>();
        part.put("parts", List.of(textPart));

        Map<String,Object> request = new HashMap<>();
        request.put("contents", List.of(part));

        return webClient.post()
                .uri("/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public String askGemini(String prompt) {

        Map<String,Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String,Object> part = new HashMap<>();
        part.put("parts", List.of(textPart));

        Map<String,Object> request = new HashMap<>();
        request.put("contents", List.of(part));

        return webClient.post()
                .uri("/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}