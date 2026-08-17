package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.entity.FinancialProfile;
import com.finwise.finwise_backend.repository.FinancialProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;

    public FinancialProfileService(FinancialProfileRepository financialProfileRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }

    public FinancialProfile createProfile(FinancialProfile profile) {
        return financialProfileRepository.save(profile);
    }

    public Optional<FinancialProfile> getProfileByUserId(Long userId) {
        return financialProfileRepository.findByUserId(userId);
    }

    public FinancialProfile updateProfile(Long userId, FinancialProfile updatedProfile) {
        Optional<FinancialProfile> existingProfile =
                financialProfileRepository.findByUserId(userId);

        if (existingProfile.isPresent()) {
            FinancialProfile profile = existingProfile.get();

            profile.setMonthlyIncome(updatedProfile.getMonthlyIncome());
            profile.setMonthlyExpenses(updatedProfile.getMonthlyExpenses());

            profile.setSavings(updatedProfile.getSavings());
            profile.setFixedDeposits(updatedProfile.getFixedDeposits());
            profile.setMutualFunds(updatedProfile.getMutualFunds());
            profile.setStocks(updatedProfile.getStocks());
            profile.setGold(updatedProfile.getGold());
            profile.setProperty(updatedProfile.getProperty());

            profile.setHomeLoan(updatedProfile.getHomeLoan());
            profile.setPersonalLoan(updatedProfile.getPersonalLoan());
            profile.setEducationLoan(updatedProfile.getEducationLoan());
            profile.setVehicleLoan(updatedProfile.getVehicleLoan());
            profile.setCreditCardDebt(updatedProfile.getCreditCardDebt());
            profile.setOtherDebt(updatedProfile.getOtherDebt());

            return financialProfileRepository.save(profile);
        }

        return financialProfileRepository.save(updatedProfile);
    }
}