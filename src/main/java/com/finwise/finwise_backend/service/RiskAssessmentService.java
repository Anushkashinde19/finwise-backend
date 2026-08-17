package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.entity.RiskAssessment;
import com.finwise.finwise_backend.repository.RiskAssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiskAssessmentService {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentService(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    public RiskAssessment saveRiskAssessment(RiskAssessment riskAssessment) {
        Optional<RiskAssessment> existing =
                repository.findByUserId(riskAssessment.getUserId());

        if (existing.isPresent()) {
            RiskAssessment current = existing.get();
            current.setRiskScore(riskAssessment.getRiskScore());
            current.setRiskCategory(riskAssessment.getRiskCategory());
            return repository.save(current);
        }

        return repository.save(riskAssessment);
    }

    public Optional<RiskAssessment> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}