package com.finwise.finwise_backend.controller;

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
    public FinancialProfile createProfile(@RequestBody FinancialProfile profile) {
        return financialProfileService.createProfile(profile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FinancialProfile> getProfileByUserId(
            @PathVariable Long userId) {

        return financialProfileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public FinancialProfile updateProfile(
            @PathVariable Long userId,
            @RequestBody FinancialProfile profile) {

        return financialProfileService.updateProfile(userId, profile);
    }
}