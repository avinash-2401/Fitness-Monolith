package com.project.Fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {

     private String userId;
     private String activityId;
    private String type;
    private String recommendation;
     private List<String> suggestion;
     private List<String> improvement;
     private List<String>  safety;










}
