package com.project.Career.Metrics.service;

import com.project.Career.Metrics.dto.DashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProfileService {

    @Autowired
    private GithubService githubService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillAnalysisService skillService;

    public DashboardResponse analyzeProfile(
            MultipartFile resume,
            String github,
            String manualSkills){

        DashboardResponse response = new DashboardResponse();

        // Extract skills from resume
        Set<String> resumeSkills =
                resumeService.extractSkills(resume);

        // Add manual skills
        Set<String> manual =
                new HashSet<>(Arrays.asList(
                        manualSkills.toLowerCase().split(",")
                ));

        resumeSkills.addAll(manual);

        // Find missing skills
        List<String> missingSkills =
                skillService.findMissingSkills(resumeSkills);

        // Fetch GitHub data
        Map<String, Object> githubData =
                githubService.fetchGitHubData(github);

        int repoCount = (int) githubData.get("repositories");

        response.setTotalSkills(resumeSkills.size());
        response.setGithubRepos(repoCount);
        response.setMissingSkills(missingSkills);
        response.setResumeScore(resumeSkills.size() * 5);
        response.setGithubActivity(repoCount * 2);
        response.setSkillMatch(100 - missingSkills.size() * 10);
        response.setCareerReadiness(70);

        return response;
    }
}