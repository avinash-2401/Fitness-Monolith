package com.project.Fitness.Service;

import com.project.Fitness.Repository.ActivityRepository;
import com.project.Fitness.Repository.RecommendationRepo;
import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.dto.RecommendationRequest;
import com.project.Fitness.model.Activity;
import com.project.Fitness.model.Recommendation;
import com.project.Fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepo recommendationRepo;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    // ⭐ get logged-in user from JWT
    private String getLoggedUserId() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    // ✅ generate recommendation (JWT-based user)
    public Recommendation genrateRecommendation(RecommendationRequest request) {

        String userId = getLoggedUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found " + userId));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() ->
                        new RuntimeException("Activity not match " + request.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .type(request.getType())                 // 🔥 ADD THIS
                .recommendation(request.getRecommendation())
                .improvement(request.getImprovement())
                .suggestion(request.getSuggestion())
                .safety(request.getSafety())
                .build();

        return recommendationRepo.save(recommendation);
    }

    // ✅ get recommendations of logged user
    public List<Recommendation> getUserRecommendationForLoggedUser() {

        String userId = getLoggedUserId();
        return recommendationRepo.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepo.findByActivityId(activityId);
    }
}
