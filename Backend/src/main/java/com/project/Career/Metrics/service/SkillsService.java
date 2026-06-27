package com.project.Career.Metrics.service;

import com.project.Career.Metrics.dto.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillsService {

    @Autowired
    private ResumeService resumeService;

    public List<SkillDTO> analyzeSkills(Set<String> resumeSkills){

        List<SkillDTO> result = new ArrayList<>();

        String resumeText = resumeService.getResumeText().toLowerCase();

        for(String skill : resumeSkills){

            int count = countOccurrences(resumeText, skill);

            int level = Math.min(40 + (count * 20), 100);

            result.add(new SkillDTO(skill, level));
        }

        return result;
    }

    private int countOccurrences(String text, String word){

        int count = 0;
        int index = 0;

        while((index = text.indexOf(word, index)) != -1){
            count++;
            index += word.length();
        }

        return count;
    }
}