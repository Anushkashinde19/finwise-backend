package com.finwise.finwise_backend.dto;

import lombok.Data;

@Data
public class FinancialProfileDTO {

    private Long userId;

    private int age;
    private String occupation;

    private double monthlyIncome;
    private double monthlyExpenses;

    private double savings;
    private double fixedDeposits;
    private double mutualFunds;
    private double stocks;
    private double gold;
    private double property;

    private double homeLoan;
    private double personalLoan;
    private double educationLoan;
    private double vehicleLoan;
    private double creditCardDebt;
    private double otherDebt;
}