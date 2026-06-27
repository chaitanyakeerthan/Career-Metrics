package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.dto.DashboardResponse;
import com.project.Career.Metrics.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/analyze")
    public DashboardResponse analyzeProfile(

            @RequestParam("resume") MultipartFile resume,
            @RequestParam("github") String github,
            @RequestParam("skills") String skills
    ){

        return profileService.analyzeProfile(
                resume,
                github,
                skills
        );
    }
}


