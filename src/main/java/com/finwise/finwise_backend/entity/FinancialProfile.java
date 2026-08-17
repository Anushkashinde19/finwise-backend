package com.finwise.finwise_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "financial_profiles")
@Data
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

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