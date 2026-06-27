package com.project.Career.Metrics.controller;





import com.project.Career.Metrics.model.CareerResponse;
import com.project.Career.Metrics.service.CareerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping("/career-predict")
    public List<CareerResponse> predictCareer(@RequestBody Map<String,String> body){

        String skills = body.get("skills");

        return careerService.predictCareer(skills);
    }
}