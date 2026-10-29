package com.ui.automation.DriverManager;

import com.ui.automation.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.nio.file.Path;

public class AppiumDriverManager {

    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return DRIVER.get();
    }

    public static void initializeDriver() {
        if (DRIVER.get() != null) {
            throw new IllegalStateException("Driver already initialized");
        }
        String platform = getPlatform();
        validatePlatform(platform);

        UiAutomator2Options options = getAndroidOptions();
        configureDeviceUdid(options);

        String url = configureExecutionTarget(options);
        createDriver(url, options);
    }

    // 1. Get platform from Maven or config.properties
    private static String getPlatform() {
        String platform = System.getProperty("platform");
        if (platform == null || platform.isBlank()) {
            platform = ConfigReader.get("platform.name");
        }
        if (platform == null || platform.isBlank()) {
            platform = "Android";
        }
        System.out.println("Executing on platform: " + platform);
        return platform;
    }

    // 2. Validate selected platform
    private static void validatePlatform(String platform) {

        if ("Android".equalsIgnoreCase(platform)) {
            System.out.println("Initializing Android driver...");

        } else if ("iOS".equalsIgnoreCase(platform)) {
            throw new UnsupportedOperationException(
                    "iOS platform is not configured yet."
            );

        } else {
            throw new UnsupportedOperationException(
                    "Unsupported platform: " + platform
            );
        }
    }

    // 3. Configure Android capabilities
    private static UiAutomator2Options getAndroidOptions() {
        return new UiAutomator2Options()
                .setDeviceName(ConfigReader.get("device.name"))
                .setAutomationName(ConfigReader.get("automation.name"))
                .setNoReset(ConfigReader.getBoolean("app.noReset"))
                .setFullReset(ConfigReader.getBoolean("app.fullReset"))
                .setNewCommandTimeout(java.time.Duration.ofSeconds(ConfigReader.getInt("new.command.timeout")));
    }

    // 4. Configure UDID
    private static void configureDeviceUdid(UiAutomator2Options options) {
        String udid = System.getProperty("udid");
        if (udid == null || udid.isBlank()) {
            udid = ConfigReader.get("device.udid");
        }
        if (udid != null && !udid.isBlank()) {
            options.setUdid(udid);
            System.out.println("Executing on device UDID: " + udid);
        } else {
            System.out.println("No UDID specified. Appium will select the connected Android device.");
        }
    }

    // 5. Configure local or cloud execution
    private static String configureExecutionTarget(UiAutomator2Options options) {
        String target = ConfigReader.get("execution.target");
        if ("local".equalsIgnoreCase(target)) {
            Path apk = Path.of(ConfigReader.get("app.path")).toAbsolutePath().normalize();
            if (!java.nio.file.Files.isRegularFile(apk)) {
                throw new IllegalStateException("APK not found: " + apk);
            }
            options.setApp(apk.toString());
            return ConfigReader.get("appium.server.url");

        } else if ("cloud".equalsIgnoreCase(target)) {
            throw new UnsupportedOperationException("Cloud execution is not configured yet.");
        } else {
            throw new IllegalArgumentException("Unknown execution.target: " + target);
        }
    }

    // 6. Initialize Android driver
    private static void createDriver(String url, UiAutomator2Options options) {
        try {
            DRIVER.set(new AndroidDriver(URI.create(url).toURL(), options));
            System.out.println("Appium driver initialized successfully.");
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize Appium driver", e);
        }
    }

    public static void quitDriver() {
        AppiumDriver driver = DRIVER.get();
        try {
            if (driver != null) driver.quit();
        } finally {
            DRIVER.remove();
        }
    }
}
