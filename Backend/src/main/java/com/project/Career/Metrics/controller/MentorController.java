package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.service.MentorService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/mentor")
    public Map<String,String> askMentor(@RequestBody Map<String,String> body){

        String question = body.get("question");

        String answer = mentorService.askMentor(question);

        return Map.of("answer",answer);
    }
}