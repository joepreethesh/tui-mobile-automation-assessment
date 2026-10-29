package com.ui.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (in == null) throw new IllegalStateException("Missing config/config.properties");
            PROPERTIES.load(in);
        } catch (Exception e) { throw new ExceptionInInitializerError(e); }
    }

    public static String get(String key) {
        String override = System.getProperty(key);
        if (override != null && !override.isBlank()) return override.trim();
        String value = PROPERTIES.getProperty(key);
        if (value == null) throw new IllegalArgumentException("Missing config key: " + key);
        return value.trim();
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
