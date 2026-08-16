package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.entity.FinancialPlan;
import com.finwise.finwise_backend.repository.FinancialPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialPlanService {

    private final FinancialPlanRepository financialPlanRepository;
    private final FinancialDatasetService financialDatasetService;

    public FinancialPlanService(
            FinancialPlanRepository financialPlanRepository,
            FinancialDatasetService financialDatasetService) {

        this.financialPlanRepository = financialPlanRepository;
        this.financialDatasetService = financialDatasetService;
    }

    // Create or save a financial plan
    public FinancialPlan createPlan(FinancialPlan plan) {
        return financialPlanRepository.save(plan);
    }

    // Get all financial plans
    public List<FinancialPlan> getAllPlans() {
        return financialPlanRepository.findAll();
    }

    // Get plan by ID
    public Optional<FinancialPlan> getPlanById(Long id) {
        return financialPlanRepository.findById(id);
    }

    // Get plan for a particular user
    public Optional<FinancialPlan> getPlanByUserId(Long userId) {
        return financialPlanRepository.findByUserId(userId);
    }

    // Update an existing plan
    public FinancialPlan updatePlan(
            Long id,
            FinancialPlan updatedPlan) {

        FinancialPlan existingPlan =
                financialPlanRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Financial plan not found"));

        existingPlan.setUserId(updatedPlan.getUserId());

        existingPlan.setMonthlySavings(
                updatedPlan.getMonthlySavings());

        existingPlan.setEmergencyFundTarget(
                updatedPlan.getEmergencyFundTarget());

        existingPlan.setInsuranceRecommendation(
                updatedPlan.getInsuranceRecommendation());

        existingPlan.setInvestmentRecommendation(
                updatedPlan.getInvestmentRecommendation());

        existingPlan.setDebtRecommendation(
                updatedPlan.getDebtRecommendation());

        existingPlan.setGoalRecommendation(
                updatedPlan.getGoalRecommendation());

        existingPlan.setRiskProfile(
                updatedPlan.getRiskProfile());

        existingPlan.setSimilarProfiles(
                updatedPlan.getSimilarProfiles());

        existingPlan.setDatasetInsight(
                updatedPlan.getDatasetInsight());

        existingPlan.setPlanSummary(
                updatedPlan.getPlanSummary());

        return financialPlanRepository.save(existingPlan);
    }

    // Delete a plan
    public void deletePlan(Long id) {
        financialPlanRepository.deleteById(id);
    }

    // Generate personalized financial plan
    public FinancialPlan generatePlan(
            Long userId,
            Double annualIncome,
            Double monthlyExpenses,
            Double assets,
            Double liabilities,
            Double emergencyFund,
            Double healthInsurance,
            String riskTolerance) {

        // ==========================================
        // 1. BASIC FINANCIAL CALCULATIONS
        // ==========================================

        double monthlyIncome =
                annualIncome / 12.0;

        double monthlySavings =
                monthlyIncome - monthlyExpenses;

        if (monthlySavings < 0) {
            monthlySavings = 0;
        }

        // Recommended emergency fund = 6 months expenses
        double emergencyFundTarget =
                monthlyExpenses * 6;


        // ==========================================
        // 2. FIND SIMILAR DATASET PROFILES
        // ==========================================

        List<String> similarProfiles =
                financialDatasetService.findSimilarProfiles(
                        riskTolerance,
                        annualIncome);

        int similarProfileCount =
                similarProfiles.size();


        // ==========================================
        // 3. INVESTMENT RECOMMENDATION
        // ==========================================

        double investmentPercentage;

        if (similarProfileCount >= 10) {

            // Dataset has many similar profiles
            if ("High".equalsIgnoreCase(riskTolerance)
                    || "Aggressive".equalsIgnoreCase(riskTolerance)) {

                investmentPercentage = 0.60;

            } else if ("Low".equalsIgnoreCase(riskTolerance)
                    || "Conservative".equalsIgnoreCase(riskTolerance)) {

                investmentPercentage = 0.30;

            } else {

                investmentPercentage = 0.45;
            }

        } else {

            // Fallback when fewer similar profiles exist
            if ("High".equalsIgnoreCase(riskTolerance)
                    || "Aggressive".equalsIgnoreCase(riskTolerance)) {

                investmentPercentage = 0.70;

            } else if ("Low".equalsIgnoreCase(riskTolerance)
                    || "Conservative".equalsIgnoreCase(riskTolerance)) {

                investmentPercentage = 0.30;

            } else {

                investmentPercentage = 0.50;
            }
        }

        double investmentRecommendation =
                monthlySavings * investmentPercentage;


        // ==========================================
        // 4. INSURANCE RECOMMENDATION
        // ==========================================

        double insuranceRecommendation =
                annualIncome * 5;

        if (healthInsurance >= insuranceRecommendation) {
            insuranceRecommendation = healthInsurance;
        }


        // ==========================================
        // 5. DEBT RECOMMENDATION
        // ==========================================

        double debtRecommendation = 0;

        if (liabilities > 0) {

            debtRecommendation =
                    monthlySavings * 0.30;
        }


        // ==========================================
        // 6. RISK PROFILE
        // ==========================================

        String riskProfile;

        if ("High".equalsIgnoreCase(riskTolerance)
                || "Aggressive".equalsIgnoreCase(riskTolerance)) {

            riskProfile = "Aggressive";

        } else if ("Low".equalsIgnoreCase(riskTolerance)
                || "Conservative".equalsIgnoreCase(riskTolerance)) {

            riskProfile = "Conservative";

        } else {

            riskProfile = "Moderate";
        }


        // ==========================================
        // 7. DATASET INSIGHT
        // ==========================================

        String datasetInsight;

        if (similarProfileCount > 0) {

            datasetInsight =
                    "The dataset contains "
                    + similarProfileCount
                    + " profiles with similar income "
                    + "and risk tolerance. "
                    + "These profiles were used as a "
                    + "benchmark for your financial "
                    + "recommendation.";

        } else {

            datasetInsight =
                    "No closely matching profiles were "
                    + "found in the dataset. "
                    + "The recommendation is based on "
                    + "standard financial rules and your "
                    + "provided financial information.";
        }


        // ==========================================
        // 8. GOAL RECOMMENDATION
        // ==========================================

        String goalRecommendation;

        if (monthlySavings <= 0) {

            goalRecommendation =
                    "Your expenses are currently higher "
                    + "than your income. Focus on reducing "
                    + "expenses and creating positive "
                    + "monthly savings.";

        } else if (emergencyFund < emergencyFundTarget) {

            goalRecommendation =
                    "Your emergency fund is below the "
                    + "recommended 6-month target. "
                    + "Prioritize building your emergency "
                    + "fund before increasing long-term "
                    + "investments.";

        } else if (liabilities > 0) {

            goalRecommendation =
                    "You have existing liabilities. "
                    + "Prioritize repayment of high-interest "
                    + "debt while continuing regular "
                    + "investments.";

        } else if (similarProfileCount > 0) {

            goalRecommendation =
                    "Based on "
                    + similarProfileCount
                    + " similar financial profiles in the "
                    + "dataset, your current savings and "
                    + "risk profile support regular "
                    + "long-term investing.";

        } else {

            goalRecommendation =
                    "Continue saving regularly and invest "
                    + "according to your risk tolerance.";
        }


        // ==========================================
        // 9. PLAN SUMMARY
        // ==========================================

        String planSummary =
                "Monthly income: ₹"
                + String.format("%.2f", monthlyIncome)

                + ". Monthly savings: ₹"
                + String.format("%.2f", monthlySavings)

                + ". Recommended emergency fund: ₹"
                + String.format(
                        "%.2f",
                        emergencyFundTarget)

                + ". Recommended insurance coverage: ₹"
                + String.format(
                        "%.2f",
                        insuranceRecommendation)

                + ". Recommended monthly investment: ₹"
                + String.format(
                        "%.2f",
                        investmentRecommendation)

                + ". Similar dataset profiles found: "
                + similarProfileCount

                + ". Your recommendation is based on "
                + "your income, expenses, assets, "
                + "liabilities, emergency fund, "
                + "health insurance and risk tolerance."

                + " Current assets: ₹"
                + String.format("%.2f", assets)

                + ". Current liabilities: ₹"
                + String.format("%.2f", liabilities)

                + ". Risk profile: "
                + riskProfile + ".";


        // ==========================================
        // 10. CREATE FINANCIAL PLAN
        // ==========================================

        FinancialPlan plan =
                new FinancialPlan();

        plan.setUserId(userId);

        plan.setMonthlySavings(
                monthlySavings);

        plan.setEmergencyFundTarget(
                emergencyFundTarget);

        plan.setInsuranceRecommendation(
                insuranceRecommendation);

        plan.setInvestmentRecommendation(
                investmentRecommendation);

        plan.setDebtRecommendation(
                debtRecommendation);

        plan.setGoalRecommendation(
                goalRecommendation);

        plan.setRiskProfile(
                riskProfile);

        plan.setSimilarProfiles(
                similarProfileCount);

        plan.setDatasetInsight(
                datasetInsight);

        plan.setPlanSummary(
                planSummary);


        // ==========================================
        // 11. SAVE TO MYSQL
        // ==========================================

        plan.setSimilarProfiles(similarProfileCount);
        plan.setDatasetInsight(datasetInsight);
        return financialPlanRepository.save(plan);
    }
}