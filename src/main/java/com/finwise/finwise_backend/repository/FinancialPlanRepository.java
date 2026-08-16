package com.finwise.finwise_backend.repository;

import com.finwise.finwise_backend.entity.FinancialPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialPlanRepository extends JpaRepository<FinancialPlan, Long> {

    Optional<FinancialPlan> findByUserId(Long userId);
}