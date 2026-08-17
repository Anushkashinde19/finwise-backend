package com.finwise.finwise_backend.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String email;
    private String password;

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