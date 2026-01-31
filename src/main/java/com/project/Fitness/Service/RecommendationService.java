package com.project.Fitness.Service;

import com.project.Fitness.Repository.ActivityRepository;
import com.project.Fitness.Repository.RecommendationRepo;
import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.dto.RecommendationRequest;
import com.project.Fitness.model.Activity;
import com.project.Fitness.model.Recommendation;
import com.project.Fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepo recommendationRepo;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public  Recommendation genrateRecommendation(RecommendationRequest request) {
      User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User not found" + request.getUserId()));
      Activity activity= activityRepository.findById(request.getActivityId()).orElseThrow(()->new RuntimeException("Activity not match" + request.getActivityId()));

      Recommendation recommendation=Recommendation.builder()
              .user(user)
              .activity(activity)
              .improvement(request.getImprovement())
              .suggestion(request.getSuggestion())
              .safety(request.getSafety())
              .build();

        Recommendation savedRecommendation = recommendationRepo.save(recommendation);
          return savedRecommendation;

    }

    public List<Recommendation> getUserRecommendation(String userId) {

        return recommendationRepo.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepo.findByActivityId(activityId);
    }
}
