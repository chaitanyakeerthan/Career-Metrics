package com.project.Career.Metrics.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Career.Metrics.model.CareerResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CareerService {

    private final GeminiService geminiService;

    public CareerService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public List<CareerResponse> predictCareer(String skills) {

        String prompt = """
You are an AI career advisor.

Based on these skills:

%s

Suggest 5 suitable tech careers.

Return ONLY valid JSON.
Do not add explanation.

Example:

[
 {"role":"Backend Developer","score":90},
 {"role":"Full Stack Developer","score":85},
 {"role":"Software Engineer","score":80},
 {"role":"DevOps Engineer","score":70},
 {"role":"Cloud Engineer","score":65}
]
""".formatted(skills);

        String response = geminiService.askGemini(prompt);

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            String text = root
                    .get("candidates")
                    .get(0)
                    .get("content")
                    .get("parts")
                    .get(0)
                    .get("text")
                    .asText();

            text = text.replace("```json", "")
                    .replace("```", "")
                    .trim();

            CareerResponse[] careers =
                    mapper.readValue(text, CareerResponse[].class);

            return Arrays.asList(careers);

        } catch (Exception e) {

            e.printStackTrace();
            return new ArrayList<>();

        }
    }
}