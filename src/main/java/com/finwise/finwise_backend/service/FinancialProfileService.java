package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.dto.FinancialProfileDTO;
import com.finwise.finwise_backend.entity.FinancialProfile;
import com.finwise.finwise_backend.repository.FinancialProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;

    public FinancialProfileService(
            FinancialProfileRepository financialProfileRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }

    public FinancialProfile createProfile(FinancialProfileDTO dto) {

        FinancialProfile profile = new FinancialProfile();

        profile.setUserId(dto.getUserId());
        profile.setAge(dto.getAge());
        profile.setOccupation(dto.getOccupation());

        profile.setMonthlyIncome(dto.getMonthlyIncome());
        profile.setMonthlyExpenses(dto.getMonthlyExpenses());

        profile.setSavings(dto.getSavings());
        profile.setFixedDeposits(dto.getFixedDeposits());
        profile.setMutualFunds(dto.getMutualFunds());
        profile.setStocks(dto.getStocks());
        profile.setGold(dto.getGold());
        profile.setProperty(dto.getProperty());

        profile.setHomeLoan(dto.getHomeLoan());
        profile.setPersonalLoan(dto.getPersonalLoan());
        profile.setEducationLoan(dto.getEducationLoan());
        profile.setVehicleLoan(dto.getVehicleLoan());
        profile.setCreditCardDebt(dto.getCreditCardDebt());
        profile.setOtherDebt(dto.getOtherDebt());

        return financialProfileRepository.save(profile);
    }

    public Optional<FinancialProfile> getProfileByUserId(Long userId) {
        return financialProfileRepository.findByUserId(userId);
    }

    public FinancialProfile updateProfile(
            Long userId,
            FinancialProfileDTO dto) {

        FinancialProfile profile =
                financialProfileRepository.findByUserId(userId)
                        .orElseGet(FinancialProfile::new);

        profile.setUserId(userId);
        profile.setAge(dto.getAge());
        profile.setOccupation(dto.getOccupation());

        profile.setMonthlyIncome(dto.getMonthlyIncome());
        profile.setMonthlyExpenses(dto.getMonthlyExpenses());

        profile.setSavings(dto.getSavings());
        profile.setFixedDeposits(dto.getFixedDeposits());
        profile.setMutualFunds(dto.getMutualFunds());
        profile.setStocks(dto.getStocks());
        profile.setGold(dto.getGold());
        profile.setProperty(dto.getProperty());

        profile.setHomeLoan(dto.getHomeLoan());
        profile.setPersonalLoan(dto.getPersonalLoan());
        profile.setEducationLoan(dto.getEducationLoan());
        profile.setVehicleLoan(dto.getVehicleLoan());
        profile.setCreditCardDebt(dto.getCreditCardDebt());
        profile.setOtherDebt(dto.getOtherDebt());

        return financialProfileRepository.save(profile);
    }
}