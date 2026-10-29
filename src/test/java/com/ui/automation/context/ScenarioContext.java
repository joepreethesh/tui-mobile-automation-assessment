package com.ui.automation.context;

import com.fasterxml.jackson.databind.JsonNode;

public class ScenarioContext {

    private JsonNode testData;

    public void setTestData(JsonNode data) {
        this.testData = data;
    }

    public JsonNode getTestData() {
        if (testData == null) throw new IllegalStateException("Test data not loaded for this scenario");
        return testData;
    }

    public String getValue(String field) {
        JsonNode value = getTestData().get(field);
        if (value == null || value.isNull() || value.asText().isBlank())
            throw new IllegalArgumentException("Missing or blank test data field: " + field);
        return value.asText();
    }
}
