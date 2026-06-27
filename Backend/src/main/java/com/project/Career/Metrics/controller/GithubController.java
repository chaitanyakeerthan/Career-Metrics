package com.project.Career.Metrics.controller;

import com.project.Career.Metrics.service.GithubService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/github")
@CrossOrigin(origins = "*")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public Map<String, Object> getGithubInsights(@PathVariable String username){

        return githubService.fetchGitHubData(username);

    }
}