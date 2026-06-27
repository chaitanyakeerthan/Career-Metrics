package com.project.Career.Metrics.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillAnalysisService {

    private static final List<String> REQUIRED_BACKEND_SKILLS = List.of(
            "java",
            "spring",
            "spring boot",
            "rest api",
            "docker",
            "kubernetes",
            "system design",
            "microservices"
    );

    public List<String> findMissingSkills(Set<String> userSkills){

        List<String> missing = new ArrayList<>();

        for(String skill : REQUIRED_BACKEND_SKILLS){

            if(!userSkills.contains(skill)){
                missing.add(skill);
            }
        }

        return missing;
    }
}
