package com.project.Career.Metrics.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Career.Metrics.model.RoadmapResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoadmapService {

    private final GeminiService geminiService;

    public RoadmapService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public List<RoadmapResponse> generateRoadmap(String skills) {

        String prompt = """
You are a software career mentor.

The user is missing these skills:

%s

Create a learning roadmap for each skill.

Return ONLY JSON like this:

[
 {
  "skill":"Docker",
  "steps":[
   "Learn Docker fundamentals",
   "Understand Dockerfile",
   "Build containers",
   "Deploy applications with Docker"
  ]
 }
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

            RoadmapResponse[] roadmap =
                    mapper.readValue(text, RoadmapResponse[].class);

            return Arrays.asList(roadmap);

        } catch (Exception e) {

            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
