package com.finwise.finwise_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_plans")
public class FinancialPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Double monthlySavings;

    private Double emergencyFundTarget;

    private Double insuranceRecommendation;

    private Double investmentRecommendation;

    private Double debtRecommendation;

    private String goalRecommendation;

    private String riskProfile;

    private Integer similarProfiles;

    @Column(length = 1000)
    private String datasetInsight;

    @Column(length = 1000)
    private String planSummary;

    public FinancialPlan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMonthlySavings() {
        return monthlySavings;
    }

    public void setMonthlySavings(Double monthlySavings) {
        this.monthlySavings = monthlySavings;
    }

    public Double getEmergencyFundTarget() {
        return emergencyFundTarget;
    }

    public void setEmergencyFundTarget(Double emergencyFundTarget) {
        this.emergencyFundTarget = emergencyFundTarget;
    }

    public Double getInsuranceRecommendation() {
        return insuranceRecommendation;
    }

    public void setInsuranceRecommendation(Double insuranceRecommendation) {
        this.insuranceRecommendation = insuranceRecommendation;
    }

    public Double getInvestmentRecommendation() {
        return investmentRecommendation;
    }

    public void setInvestmentRecommendation(Double investmentRecommendation) {
        this.investmentRecommendation = investmentRecommendation;
    }

    public Double getDebtRecommendation() {
        return debtRecommendation;
    }

    public void setDebtRecommendation(Double debtRecommendation) {
        this.debtRecommendation = debtRecommendation;
    }

    public String getGoalRecommendation() {
        return goalRecommendation;
    }

    public void setGoalRecommendation(String goalRecommendation) {
        this.goalRecommendation = goalRecommendation;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(String riskProfile) {
        this.riskProfile = riskProfile;
    }

    public Integer getSimilarProfiles() {
        return similarProfiles;
    }

    public void setSimilarProfiles(Integer similarProfiles) {
        this.similarProfiles = similarProfiles;
    }

    public String getDatasetInsight() {
        return datasetInsight;
    }

    public void setDatasetInsight(String datasetInsight) {
        this.datasetInsight = datasetInsight;
    }

    public String getPlanSummary() {
        return planSummary;
    }

    public void setPlanSummary(String planSummary) {
        this.planSummary = planSummary;
    }
}