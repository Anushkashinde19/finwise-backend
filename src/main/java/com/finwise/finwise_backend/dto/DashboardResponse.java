package com.finwise.finwise_backend.dto;

import com.finwise.finwise_backend.entity.Goal;
import lombok.Data;

import java.util.List;

@Data
public class DashboardResponse {

    private Long userId;

    private double netWorth;
    private double monthlyIncome;
    private double monthlyExpenses;
    private double monthlySurplus;

    private double totalAssets;
    private double totalLiabilities;
    private double debt;

    private double emergencyFund;
    private int financialHealthScore;
    private int riskScore;
    private int resilienceScore;

    private String riskCategory;
    private String recommendedPlan;

    private List<Goal> goals;
}