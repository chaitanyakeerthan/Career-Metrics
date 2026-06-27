package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.model.RoadmapResponse;
import com.project.Career.Metrics.service.RoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @PostMapping("/roadmap")
    public List<RoadmapResponse> getRoadmap(@RequestBody Map<String,String> body){

        String skills = body.get("skills");

        return roadmapService.generateRoadmap(skills);
    }
}