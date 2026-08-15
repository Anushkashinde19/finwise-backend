package com.finwise.finwise_backend.service;

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

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> getGoalsByUser(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    public Goal getGoal(Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    public Goal updateGoal(Long id, Goal goal) {

        Goal existingGoal = goalRepository.findById(id).orElse(null);

        if (existingGoal == null) {
            return null;
        }

        existingGoal.setUserId(goal.getUserId());
        existingGoal.setGoalName(goal.getGoalName());
        existingGoal.setTargetAmount(goal.getTargetAmount());
        existingGoal.setCurrentAmount(goal.getCurrentAmount());
        existingGoal.setTargetYear(goal.getTargetYear());
        existingGoal.setPriority(goal.getPriority());

        return goalRepository.save(existingGoal);
    }

    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}