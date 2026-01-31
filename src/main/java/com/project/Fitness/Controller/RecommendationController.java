package com.project.Fitness.Controller;

import com.project.Fitness.Service.RecommendationService;
import com.project.Fitness.dto.RecommendationRequest;
import com.project.Fitness.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/genrate")
     public ResponseEntity<Recommendation> genrateRecommendation(@RequestBody RecommendationRequest request){

        Recommendation recommendation = recommendationService.genrateRecommendation(request);
        return ResponseEntity.ok(recommendation);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId )
    {
        List<Recommendation> recommendationList = recommendationService.getUserRecommendation(userId);

        return ResponseEntity.ok(recommendationList);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String activityId )
    {
        List<Recommendation> recommendationList = recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationList);
    }

}
