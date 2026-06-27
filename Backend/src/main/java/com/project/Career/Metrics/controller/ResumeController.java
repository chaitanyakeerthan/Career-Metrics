package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.dto.ResumeAnalysisResponse;
import com.project.Career.Metrics.service.ResumeAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "http://localhost:5173")
public class ResumeController {

    @Autowired
    private ResumeAnalyzerService analyzerService;

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("jobDescription") String jobDescription) {

        try {

            ResumeAnalysisResponse result =
                    analyzerService.analyze(resume, jobDescription);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body("Resume analysis failed");

        }
    }
}