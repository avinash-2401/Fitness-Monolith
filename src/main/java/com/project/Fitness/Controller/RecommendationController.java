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

    // ✅ CREATE recommendation
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody RecommendationRequest request) {

        Recommendation recommendation =
                recommendationService.genrateRecommendation(request);

        return ResponseEntity.ok(recommendation);
    }

    // ✅ Logged-in user recommendations
    @GetMapping("/user")
    public ResponseEntity<List<Recommendation>> getUserRecommendation() {

        List<Recommendation> recommendationList =
                recommendationService.getUserRecommendationForLoggedUser();

        return ResponseEntity.ok(recommendationList);
    }

    // ✅ Activity wise
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(
            @PathVariable String activityId) {

        List<Recommendation> recommendationList =
                recommendationService.getActivityRecommendation(activityId);

        return ResponseEntity.ok(recommendationList);
    }
}
