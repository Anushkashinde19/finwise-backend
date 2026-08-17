package com.finwise.finwise_backend.repository;

import com.finwise.finwise_backend.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {

    Optional<RiskAssessment> findByUserId(Long userId);
}