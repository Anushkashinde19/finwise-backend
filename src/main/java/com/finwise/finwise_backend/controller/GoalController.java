package com.finwise.finwise_backend.controller;

import com.finwise.finwise_backend.dto.GoalDTO;
import com.finwise.finwise_backend.entity.Goal;
import com.finwise.finwise_backend.service.GoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://localhost:5173")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public Goal createGoal(@RequestBody GoalDTO dto) {
        return goalService.createGoal(dto);
    }

    @GetMapping("/{userId}")
    public List<Goal> getGoalsByUser(@PathVariable Long userId) {
        return goalService.getGoalsByUser(userId);
    }

    @PutMapping("/{id}")
    public Goal updateGoal(
            @PathVariable Long id,
            @RequestBody GoalDTO dto) {
        return goalService.updateGoal(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return "Goal deleted successfully";
    }
}