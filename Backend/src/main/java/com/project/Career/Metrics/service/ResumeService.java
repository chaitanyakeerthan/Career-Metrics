package com.project.Career.Metrics.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ResumeService {

    private static final List<String> SKILL_DATABASE = List.of(
            "java","spring","spring boot","react","angular","node",
            "mysql","mongodb","docker","kubernetes","aws",
            "javascript","typescript","html","css","python"
    );

    // stored extracted skills
    private Set<String> extractedSkills = new HashSet<>();

    // stored resume text
    private String resumeText = "";

    public Set<String> extractSkills(MultipartFile resume){

        Set<String> detectedSkills = new HashSet<>();

        try{

            Tika tika = new Tika();

            // parse resume text
            String text = tika.parseToString(resume.getInputStream()).toLowerCase();

            for(String skill : SKILL_DATABASE){

                if(text.contains(skill)){
                    detectedSkills.add(skill);
                }
            }

            extractedSkills = detectedSkills;

        }catch(Exception e){
            e.printStackTrace();
        }

        return detectedSkills;
    }

    public String getResumeText(){
        return resumeText;
    }

    public Set<String> getExtractedSkills(){
        return extractedSkills;
    }

}