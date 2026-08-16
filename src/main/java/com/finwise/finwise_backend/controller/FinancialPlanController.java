package com.finwise.finwise_backend.controller;

import com.finwise.finwise_backend.entity.FinancialPlan;
import com.finwise.finwise_backend.service.FinancialPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-plans")
@CrossOrigin(origins = "*")
public class FinancialPlanController {

    private final FinancialPlanService financialPlanService;

    public FinancialPlanController(FinancialPlanService financialPlanService) {
        this.financialPlanService = financialPlanService;
    }

    // Create a financial plan manually
    @PostMapping
    public ResponseEntity<FinancialPlan> createPlan(
            @RequestBody FinancialPlan plan) {

        return ResponseEntity.ok(
                financialPlanService.createPlan(plan)
        );
    }

    // Get all financial plans
    @GetMapping
    public ResponseEntity<List<FinancialPlan>> getAllPlans() {

        return ResponseEntity.ok(
                financialPlanService.getAllPlans()
        );
    }

    // Get financial plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<FinancialPlan> getPlanById(
            @PathVariable Long id) {

        return financialPlanService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get financial plan by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<FinancialPlan> getPlanByUserId(
            @PathVariable Long userId) {

        return financialPlanService.getPlanByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update financial plan
    @PutMapping("/{id}")
    public ResponseEntity<FinancialPlan> updatePlan(
            @PathVariable Long id,
            @RequestBody FinancialPlan plan) {

        try {
            return ResponseEntity.ok(
                    financialPlanService.updatePlan(id, plan)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete financial plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable Long id) {

        financialPlanService.deletePlan(id);

        return ResponseEntity.noContent().build();
    }

    // Generate a financial plan from user's financial information
    @PostMapping("/generate")
    public ResponseEntity<FinancialPlan> generatePlan(
            @RequestParam Long userId,
            @RequestParam Double annualIncome,
            @RequestParam Double monthlyExpenses,
            @RequestParam Double assets,
            @RequestParam Double liabilities,
            @RequestParam Double emergencyFund,
            @RequestParam Double healthInsurance,
            @RequestParam String riskTolerance) {

        FinancialPlan plan = financialPlanService.generatePlan(
                userId,
                annualIncome,
                monthlyExpenses,
                assets,
                liabilities,
                emergencyFund,
                healthInsurance,
                riskTolerance
        );

        return ResponseEntity.ok(plan);
    }
}