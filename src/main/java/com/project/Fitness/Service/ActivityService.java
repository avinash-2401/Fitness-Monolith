package com.project.Fitness.Service;


import com.project.Fitness.Repository.ActivityRepository;
import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.dto.ActivityRequest;
import com.project.Fitness.dto.ActivityResponse;
import com.project.Fitness.model.Activity;
import com.project.Fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public  ActivityResponse trackActivity(ActivityRequest request) {
       User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("Invaild User ID" + request.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .caloriesburn(request.getCaloriesburn())
                .duration(request.getDuration())
                .startedAt(request.getStartedAt())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);
        return maptoResponse(savedActivity);
    }

    private ActivityResponse maptoResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUseId(activity.getUser().getId());
        response.setType(activity.getType());
        response.setCaloriesburn(activity.getCaloriesburn());
        response.setDuration(activity.getDuration());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        response.setStartedAt(activity.getStartedAt());
        return response;

    }

    public  List<ActivityResponse> getUserActivity( String userId) {
         List<Activity> activityList = activityRepository.findUserById(userId);
         return activityList.stream()
                 .map(this::maptoResponse)
                 .collect(Collectors.toList());


    }
}
