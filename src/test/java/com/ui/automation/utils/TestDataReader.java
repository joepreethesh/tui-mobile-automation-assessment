package com.ui.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class TestDataReader {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode load(String source, List<String> criteria) {
        source = source.trim().toLowerCase(Locale.ROOT);
        if (!source.matches("[a-z][a-z0-9]*"))
            throw new IllegalArgumentException("Invalid screen name: " + source);
        String path = "testdata/" + source + ".json";
        Set<String> requested = normalize(criteria);
        CriteriaValidator.validate(source, requested);
        try (InputStream in = TestDataReader.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) throw new IllegalStateException("Missing data file: " + path);
            JsonNode records = MAPPER.readTree(in);
            if (!records.isArray()) throw new IllegalStateException(path + " must contain a JSON array");
            List<JsonNode> matches = new ArrayList<>();
            for (JsonNode record : records) {
                JsonNode labels = record.path("criteria");
                if (!labels.isArray()) throw new IllegalStateException("Record missing criteria array in " + path);
                List<String> recordCriteria = new ArrayList<>();
                labels.forEach(v -> recordCriteria.add(v.asText()));
                Set<String> normalized = normalize(recordCriteria);
                if (normalized.size() != recordCriteria.size()) throw new IllegalStateException("Duplicate criteria in " + path);
                CriteriaValidator.validate(source, normalized);
                if (normalized.equals(requested)) matches.add(record);
            }
            if (matches.size() != 1)
                throw new IllegalStateException("Expected exactly one record for " + source + " criteria " + requested + ", found " + matches.size());
            return matches.get(0).deepCopy();
        } catch (java.io.IOException e) {
            throw new IllegalStateException("Unable to read " + path, e);
        }
    }

    static Set<String> normalize(Collection<String> values) {
        Set<String> result = values.stream().map(v -> v.trim().toLowerCase(Locale.ROOT)).collect(Collectors.toCollection(LinkedHashSet::new));
        if (result.size() != values.size()) throw new IllegalArgumentException("Duplicate criteria: " + values);
        if (result.isEmpty() || result.contains("")) throw new IllegalArgumentException("Criteria cannot be empty");
        return result;
    }
}
