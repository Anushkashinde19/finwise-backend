package com.finwise.finwise_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "goals")
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String goalName;

    private double targetAmount;

    private double currentAmount;

    private int targetYear;

    private String priority;
}