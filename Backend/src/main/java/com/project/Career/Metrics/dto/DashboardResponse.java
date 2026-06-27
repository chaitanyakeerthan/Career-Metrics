package com.project.Career.Metrics.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardResponse {

    private int totalSkills;
    private int resumeScore;
    private int githubRepos;
    private int githubActivity;
    private int skillMatch;
    private List<String> missingSkills;
    private int careerReadiness;
}
