package com.finwise.finwise_backend.dto;

import lombok.Data;

@Data
public class GoalDTO {

    private Long userId;
    private String goalName;
    private double targetAmount;
    private double currentAmount;
    private int targetYear;
    private String priority;
}