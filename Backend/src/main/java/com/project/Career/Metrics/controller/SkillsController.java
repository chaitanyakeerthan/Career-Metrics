package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.dto.SkillDTO;
import com.project.Career.Metrics.service.ResumeService;
import com.project.Career.Metrics.service.SkillsService;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins="*")
public class SkillsController {

    private final SkillsService skillsService;
    private final ResumeService resumeService;

    public SkillsController(
            SkillsService skillsService,
            ResumeService resumeService){

        this.skillsService = skillsService;
        this.resumeService = resumeService;
    }

    @GetMapping
    public List<SkillDTO> getSkills(){

        Set<String> resumeSkills =
                resumeService.getExtractedSkills();

        return skillsService.analyzeSkills(resumeSkills);
    }
}