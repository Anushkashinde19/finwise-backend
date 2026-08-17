package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.dto.RiskAssessmentDTO;
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

    public RiskAssessment saveRiskAssessment(RiskAssessmentDTO dto) {

        Optional<RiskAssessment> existing =
                repository.findByUserId(dto.getUserId());

        if (existing.isPresent()) {

            RiskAssessment current = existing.get();

            current.setRiskScore(dto.getRiskScore());
            current.setRiskCategory(dto.getRiskCategory());

            return repository.save(current);
        }

        RiskAssessment riskAssessment = new RiskAssessment();

        riskAssessment.setUserId(dto.getUserId());
        riskAssessment.setRiskScore(dto.getRiskScore());
        riskAssessment.setRiskCategory(dto.getRiskCategory());

        return repository.save(riskAssessment);
    }

    public Optional<RiskAssessment> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}