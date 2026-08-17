package com.finwise.finwise_backend.controller;

import com.finwise.finwise_backend.dto.FinancialProfileDTO;
import com.finwise.finwise_backend.entity.FinancialProfile;
import com.finwise.finwise_backend.service.FinancialProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "http://localhost:5173")
public class FinancialProfileController {

    private final FinancialProfileService financialProfileService;

    public FinancialProfileController(FinancialProfileService financialProfileService) {
        this.financialProfileService = financialProfileService;
    }

    @PostMapping
    public FinancialProfile createProfile(
            @RequestBody FinancialProfileDTO dto) {

        return financialProfileService.createProfile(dto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FinancialProfile> getProfileByUserId(
            @PathVariable Long userId) {

        return financialProfileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<FinancialProfile> updateProfile(
            @PathVariable Long userId,
            @RequestBody FinancialProfileDTO dto) {

        return ResponseEntity.ok(
                financialProfileService.updateProfile(userId, dto)
        );
    }
}