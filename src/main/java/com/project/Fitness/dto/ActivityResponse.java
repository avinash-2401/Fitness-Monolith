package com.project.Fitness.dto;


import com.project.Fitness.model.ActivityTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private String id;
    private String useId;
    private ActivityTypes type ;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesburn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private  LocalDateTime startedAt;



}
