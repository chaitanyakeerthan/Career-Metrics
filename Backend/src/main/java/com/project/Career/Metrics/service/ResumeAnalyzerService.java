package com.project.Career.Metrics.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Career.Metrics.dto.ResumeAnalysisResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeAnalyzerService {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private GeminiService geminiService;

    public ResumeAnalysisResponse analyze(
            MultipartFile resume,
            String jobDescription){

        try {

            // Extract skills + store resume text internally
            resumeService.extractSkills(resume);

            // Get parsed resume text
            String resumeText = resumeService.getResumeText();

            // Call Gemini AI
            String geminiResponse =
                    geminiService.analyzeResume(resumeText, jobDescription);

            System.out.println("Gemini response:");
            System.out.println(geminiResponse);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(geminiResponse);

            String aiText = root
                    .path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text")
                    .asText();

            // remove markdown if Gemini returns it
            aiText = aiText.replace("```json", "")
                    .replace("```", "")
                    .trim();

            return mapper.readValue(aiText, ResumeAnalysisResponse.class);

        } catch (Exception e) {

            e.printStackTrace();

            // fallback response
            ResumeAnalysisResponse fallback = new ResumeAnalysisResponse();

            fallback.setAtsScore(60);
            fallback.setMissingSkills(
                    java.util.List.of("Docker","AWS")
            );

            fallback.setSuggestions(
                    java.util.List.of(
                            "Add more backend projects",
                            "Mention deployment tools like Docker"
                    )
            );

            return fallback;
        }
    }
}