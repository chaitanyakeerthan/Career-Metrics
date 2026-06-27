package com.project.Career.Metrics.dto;

public class SkillDTO {

    private String name;
    private int level;

    public SkillDTO(String name, int level){
        this.name = name;
        this.level = level;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }
}