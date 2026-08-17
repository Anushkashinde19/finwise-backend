package com.finwise.finwise_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "risk_assessments")
@Data
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int riskScore;

    private String riskCategory;
}