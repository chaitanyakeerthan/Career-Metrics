package com.project.Career.Metrics.model;

import java.util.List;

public class RoadmapResponse {

    private String skill;
    private List<String> steps;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
