package com.finwise.finwise_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String occupation;
    private double annualIncome;
    private double monthlyExpenses;
    private double assets;
    private double liabilities;
    private String riskTolerance;
    private double emergencyFund;
    private double healthInsurance;
    private int dependents;
}