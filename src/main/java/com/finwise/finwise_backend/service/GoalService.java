package com.finwise.finwise_backend.service;

import com.finwise.finwise_backend.dto.GoalDTO;
import com.finwise.finwise_backend.entity.Goal;
import com.finwise.finwise_backend.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal createGoal(GoalDTO dto) {

        Goal goal = new Goal();

        goal.setUserId(dto.getUserId());
        goal.setGoalName(dto.getGoalName());
        goal.setTargetAmount(dto.getTargetAmount());
        goal.setCurrentAmount(dto.getCurrentAmount());
        goal.setTargetYear(dto.getTargetYear());
        goal.setPriority(dto.getPriority());

        return goalRepository.save(goal);
    }

    public List<Goal> getGoalsByUser(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    public Goal updateGoal(Long id, GoalDTO dto) {

        Goal existingGoal = goalRepository.findById(id).orElse(null);

        if (existingGoal == null) {
            return null;
        }

        existingGoal.setUserId(dto.getUserId());
        existingGoal.setGoalName(dto.getGoalName());
        existingGoal.setTargetAmount(dto.getTargetAmount());
        existingGoal.setCurrentAmount(dto.getCurrentAmount());
        existingGoal.setTargetYear(dto.getTargetYear());
        existingGoal.setPriority(dto.getPriority());

        return goalRepository.save(existingGoal);
    }

    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}