package com.ui.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;

public class CriteriaValidator {

    public static void validate(String source, Set<String> requested) {

        try (InputStream in = CriteriaValidator.class.getClassLoader().getResourceAsStream("criteria/criteria.json")) {
            if (in == null) {
                throw new IllegalStateException(
                        "Missing criteria/criteria.json"
                );
            }
            JsonNode root = new ObjectMapper().readTree(in);
            JsonNode criteria = root.path("criteria");

            if (!criteria.isArray()) {
                throw new IllegalArgumentException(
                        "Criteria array not found in criteria.json"
                );
            }
            Set<String> allowedCriteria = new HashSet<>();

            for (JsonNode criterion : criteria) {
                allowedCriteria.add(
                        criterion.asText().trim().toLowerCase(Locale.ROOT)
                );
            }
            for (String criterion : requested) {

                String value = criterion.trim().toLowerCase(Locale.ROOT);

                if (!allowedCriteria.contains(value)) {
                    throw new IllegalArgumentException(
                            "Unknown criterion: " + criterion
                    );
                }
            }
        } catch (java.io.IOException e) {
            throw new IllegalStateException("Unable to read criteria.json", e);
        }
    }
}
