package com.project.Fitness.Controller;

import com.project.Fitness.Service.ActivityService;
import com.project.Fitness.dto.ActivityRequest;
import com.project.Fitness.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // ✅ Track Activity (UserId comes from JWT)
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(
            @RequestBody ActivityRequest request,
            Authentication authentication) {

        String userId = (String) authentication.getPrincipal();

        return ResponseEntity.ok(
                activityService.trackActivity(request, userId)
        );
    }

    // ✅ Get Logged-in User Activities
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivity(
            Authentication authentication) {

        String userId = (String) authentication.getPrincipal();

        return ResponseEntity.ok(
                activityService.getUserActivity(userId)
        );
    }
}
