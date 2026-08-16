package com.finwise.finwise_backend.controller;

import com.finwise.finwise_backend.service.FinancialDatasetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dataset")
@CrossOrigin(origins = "*")
public class FinancialDatasetController {

    private final FinancialDatasetService datasetService;

    public FinancialDatasetController(
            FinancialDatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getDatasetCount() {
        return ResponseEntity.ok(
                datasetService.getDatasetSize()
        );
    }

    @GetMapping("/rows")
    public ResponseEntity<List<String>> getDatasetRows() {
        return ResponseEntity.ok(
                datasetService.getDatasetRows()
        );
    }
}