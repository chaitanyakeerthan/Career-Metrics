package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.dto.SkillGapResponse;
import com.project.Career.Metrics.service.ResumeService;
import com.project.Career.Metrics.service.SkillGapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class SkillGapController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillGapService skillGapService;

    @PostMapping(value="/skillgap", consumes="multipart/form-data")
    public SkillGapResponse analyzeGap(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("jobDescription") String jobDescription){

        Set<String> resumeSkills =
                resumeService.extractSkills(resume);

        List<String> missingSkills =
                skillGapService.findMissingSkills(
                        resumeSkills,
                        jobDescription
                );

        return new SkillGapResponse(missingSkills);
    }
}