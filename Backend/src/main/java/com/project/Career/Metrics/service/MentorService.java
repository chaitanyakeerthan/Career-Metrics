package com.project.Career.Metrics.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class MentorService {

    private final GeminiService geminiService;

    public MentorService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String askMentor(String question) {

        String prompt = """
You are an AI software mentor.

Answer the user's question clearly and help them grow as a developer.

Question:
%s
""".formatted(question);

        String response = geminiService.askGemini(prompt);

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            return root
                    .get("candidates")
                    .get(0)
                    .get("content")
                    .get("parts")
                    .get(0)
                    .get("text")
                    .asText();

        } catch (Exception e) {

            e.printStackTrace();
            return "Sorry, I couldn't generate a response.";
        }
    }
}