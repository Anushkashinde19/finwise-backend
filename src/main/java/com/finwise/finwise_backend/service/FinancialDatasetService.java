package com.finwise.finwise_backend.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialDatasetService {

    private static final String FILE_PATH =
            "dataset/finwise_financial_planning.csv";

    // Read all dataset rows
    public List<String> getDatasetRows() {

        List<String> rows = new ArrayList<>();

        try {
            ClassPathResource resource =
                    new ClassPathResource(FILE_PATH);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            resource.getInputStream(),
                            StandardCharsets.UTF_8))) {

                // Skip header
                reader.readLine();

                String line;

                while ((line = reader.readLine()) != null) {

                    if (!line.trim().isEmpty()) {
                        rows.add(line);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read financial dataset", e);
        }

        return rows;
    }

    // Number of records
    public int getDatasetSize() {
        return getDatasetRows().size();
    }

    // Find profiles with the same risk tolerance
    public List<String> findByRiskTolerance(String riskTolerance) {

        List<String> matchingProfiles = new ArrayList<>();

        for (String row : getDatasetRows()) {

            String[] columns = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            // Risk Tolerance is column 12
            if (columns.length > 11) {

                String datasetRisk =
                        columns[11]
                                .replace("\"", "")
                                .trim();

                if (datasetRisk.equalsIgnoreCase(riskTolerance)) {
                    matchingProfiles.add(row);
                }
            }
        }

        return matchingProfiles;
    }

    // Find profiles based on risk tolerance and income range
    public List<String> findSimilarProfiles(
            String riskTolerance,
            double annualIncome) {

        List<String> similarProfiles = new ArrayList<>();

        for (String row : getDatasetRows()) {

            String[] columns = row.split(
                    ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            if (columns.length <= 11) {
                continue;
            }

            String datasetRisk =
                    columns[11]
                            .replace("\"", "")
                            .trim();

            if (!datasetRisk.equalsIgnoreCase(riskTolerance)) {
                continue;
            }

            String incomeText =
                    columns[4]
                            .replace("\"", "")
                            .replace("₹", "")
                            .replace(",", "")
                            .trim();

            // Check whether the dataset income range is
            // reasonably close to user's income
            boolean incomeMatch = false;

            try {

                if (incomeText.contains("–")) {

                    String[] range =
                            incomeText.split("–");

                    double min =
                            Double.parseDouble(
                                    range[0].trim());

                    double max =
                            Double.parseDouble(
                                    range[1].trim());

                    if (annualIncome >= min
                            && annualIncome <= max) {

                        incomeMatch = true;
                    }

                } else {

                    double datasetIncome =
                            Double.parseDouble(incomeText);

                    double difference =
                            Math.abs(
                                    datasetIncome
                                            - annualIncome);

                    if (difference <= annualIncome * 0.25) {
                        incomeMatch = true;
                    }
                }

            } catch (Exception ignored) {
                // Ignore invalid income values
            }

            if (incomeMatch) {
                similarProfiles.add(row);
            }
        }

        return similarProfiles;
    }
}