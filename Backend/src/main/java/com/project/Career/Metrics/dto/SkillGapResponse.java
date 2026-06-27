package com.project.Career.Metrics.dto;

import java.util.List;

public class SkillGapResponse {

    private List<String> missingSkills;

    public SkillGapResponse(){}

    public SkillGapResponse(List<String> missingSkills){
        this.missingSkills = missingSkills;
    }

    public List<String> getMissingSkills(){
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills){
        this.missingSkills = missingSkills;
    }
}