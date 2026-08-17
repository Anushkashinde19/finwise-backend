package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.dto.UserDTO;
import com.finwise.finwise_backend.entity.User;
import com.finwise.finwise_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user.setAge(dto.getAge());
        user.setOccupation(dto.getOccupation());

        user.setAnnualIncome(dto.getAnnualIncome());
        user.setMonthlyExpenses(dto.getMonthlyExpenses());
        user.setAssets(dto.getAssets());
        user.setLiabilities(dto.getLiabilities());
        user.setRiskTolerance(dto.getRiskTolerance());
        user.setEmergencyFund(dto.getEmergencyFund());
        user.setHealthInsurance(dto.getHealthInsurance());
        user.setDependents(dto.getDependents());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}