package com.project.Career.Metrics.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResumeAnalysisResponse {

    private int atsScore;

    private List<String> missingSkills;

    private List<String> suggestions;

}