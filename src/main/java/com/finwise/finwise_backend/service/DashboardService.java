package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.dto.DashboardResponse;
import com.finwise.finwise_backend.entity.FinancialProfile;
import com.finwise.finwise_backend.entity.Goal;
import com.finwise.finwise_backend.entity.RiskAssessment;
import com.finwise.finwise_backend.repository.FinancialProfileRepository;
import com.finwise.finwise_backend.repository.GoalRepository;
import com.finwise.finwise_backend.repository.RiskAssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final FinancialProfileRepository profileRepository;
    private final GoalRepository goalRepository;
    private final RiskAssessmentRepository riskRepository;

    public DashboardService(
            FinancialProfileRepository profileRepository,
            GoalRepository goalRepository,
            RiskAssessmentRepository riskRepository) {

        this.profileRepository = profileRepository;
        this.goalRepository = goalRepository;
        this.riskRepository = riskRepository;
    }

    public DashboardResponse getDashboard(Long userId) {

        FinancialProfile profile = profileRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Financial profile not found"));

        RiskAssessment risk = riskRepository
                .findByUserId(userId)
                .orElse(null);

        List<Goal> goals = goalRepository.findByUserId(userId);

        double totalAssets =
                profile.getSavings()
                        + profile.getFixedDeposits()
                        + profile.getMutualFunds()
                        + profile.getStocks()
                        + profile.getGold()
                        + profile.getProperty();

        double totalLiabilities =
                profile.getHomeLoan()
                        + profile.getPersonalLoan()
                        + profile.getEducationLoan()
                        + profile.getVehicleLoan()
                        + profile.getCreditCardDebt()
                        + profile.getOtherDebt();

        double monthlySurplus =
                profile.getMonthlyIncome()
                        - profile.getMonthlyExpenses();

        double netWorth =
                totalAssets - totalLiabilities;

        double debtRatio = 0;

        if (profile.getMonthlyIncome() > 0) {
            debtRatio = totalLiabilities /
                    (profile.getMonthlyIncome() * 12);
        }

        int financialHealthScore = calculateHealthScore(
                monthlySurplus,
                profile.getMonthlyIncome(),
                profile.getSavings(),
                profile.getMonthlyExpenses(),
                totalLiabilities
        );

        int resilienceScore = calculateResilienceScore(
                profile.getSavings(),
                profile.getMonthlyExpenses()
        );

        int riskScore = risk != null ? risk.getRiskScore() : 0;

        String riskCategory =
                risk != null ? risk.getRiskCategory() : "Not Assessed";

        String recommendedPlan =
                getRecommendedPlan(riskCategory);

        DashboardResponse response = new DashboardResponse();

        response.setUserId(userId);

        response.setNetWorth(netWorth);
        response.setMonthlyIncome(profile.getMonthlyIncome());
        response.setMonthlyExpenses(profile.getMonthlyExpenses());
        response.setMonthlySurplus(monthlySurplus);

        response.setTotalAssets(totalAssets);
        response.setTotalLiabilities(totalLiabilities);
        response.setDebt(totalLiabilities);

        response.setEmergencyFund(profile.getSavings());
        response.setFinancialHealthScore(financialHealthScore);
        response.setRiskScore(riskScore);
        response.setResilienceScore(resilienceScore);

        response.setRiskCategory(riskCategory);
        response.setRecommendedPlan(recommendedPlan);

        response.setGoals(goals);

        return response;
    }

    private int calculateHealthScore(
            double surplus,
            double income,
            double savings,
            double expenses,
            double liabilities) {

        if (income <= 0) {
            return 0;
        }

        double savingsRate = surplus / income;

        int score = 0;

        if (savingsRate >= 0.30) {
            score += 40;
        } else if (savingsRate >= 0.20) {
            score += 30;
        } else if (savingsRate >= 0.10) {
            score += 20;
        } else if (savingsRate > 0) {
            score += 10;
        }

        double emergencyMonths =
                expenses > 0 ? savings / expenses : 0;

        if (emergencyMonths >= 6) {
            score += 30;
        } else if (emergencyMonths >= 3) {
            score += 20;
        } else if (emergencyMonths >= 1) {
            score += 10;
        }

        double annualIncome = income * 12;

        if (annualIncome > 0) {

            double debtRatio = liabilities / annualIncome;

            if (debtRatio <= 0.20) {
                score += 30;
            } else if (debtRatio <= 0.40) {
                score += 20;
            } else if (debtRatio <= 0.60) {
                score += 10;
            }
        }

        return Math.min(score, 100);
    }

    private int calculateResilienceScore(
            double savings,
            double expenses) {

        if (expenses <= 0) {
            return 100;
        }

        double monthsCovered = savings / expenses;

        if (monthsCovered >= 6) {
            return 100;
        }

        if (monthsCovered >= 3) {
            return 75;
        }

        if (monthsCovered >= 1) {
            return 50;
        }

        return 25;
    }

    private String getRecommendedPlan(String riskCategory) {

        if (riskCategory == null) {
            return "Balanced Plan";
        }

        switch (riskCategory.toLowerCase()) {

            case "conservative":
                return "Conservative Plan";

            case "aggressive":
                return "Growth Plan";

            case "moderate":
                return "Balanced Plan";

            default:
                return "Balanced Plan";
        }
    }
}