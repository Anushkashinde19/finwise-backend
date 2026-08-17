package com.finwise.finwise_backend.controller;

import com.finwise.finwise_backend.dto.RiskAssessmentDTO;
import com.finwise.finwise_backend.entity.RiskAssessment;
import com.finwise.finwise_backend.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk-assessment")
@CrossOrigin(origins = "http://localhost:5173")
public class RiskAssessmentController {

    private final RiskAssessmentService service;

    public RiskAssessmentController(RiskAssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public RiskAssessment saveRiskAssessment(
            @RequestBody RiskAssessmentDTO dto) {

        return service.saveRiskAssessment(dto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RiskAssessment> getRiskAssessment(
            @PathVariable Long userId) {

        return service.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}