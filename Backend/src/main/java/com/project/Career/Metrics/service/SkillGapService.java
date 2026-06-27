package com.project.Career.Metrics.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillGapService {

    private static final List<String> SKILL_DATABASE = List.of(
            "java","spring","spring boot","react","angular","node",
            "mysql","mongodb","docker","kubernetes","aws",
            "javascript","typescript","html","css","python"
    );

    public List<String> findMissingSkills(Set<String> resumeSkills,
                                          String jobDescription){

        String jobText = jobDescription.toLowerCase();

        Set<String> requiredSkills = new HashSet<>();

        for(String skill : SKILL_DATABASE){

            // flexible matching
            if(jobText.contains(skill)){
                requiredSkills.add(skill);
            }

        }

        System.out.println("Resume Skills: " + resumeSkills);
        System.out.println("Required Skills: " + requiredSkills);

        List<String> missingSkills = new ArrayList<>();

        for(String skill : requiredSkills){

            if(!resumeSkills.contains(skill)){
                missingSkills.add(skill);
            }

        }

        return missingSkills;
    }
}