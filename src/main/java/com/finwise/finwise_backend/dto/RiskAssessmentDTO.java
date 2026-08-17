package com.finwise.finwise_backend.dto;

import lombok.Data;

@Data
public class RiskAssessmentDTO {

    private Long userId;
    private int riskScore;
    private String riskCategory;
}