package com.ptaf.utils;

import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ScenarioUtil is a utility class that provides methods for handling
 * scenarios during test execution, particularly for teardown processes.
 * It includes functionality to take screenshots in case of scenario failures.
 */
public class ScenarioUtil {
    private static final Logger logger = LoggerFactory.getLogger(ScenarioUtil.class);

    /**
     * Handles the teardown process for a given scenario.
     * It will take a screenshot if the scenario fails and attach it to the scenario report.
     *
     * @param scenario The current scenario being executed, providing context for logging.
     * @param page     The Playwright Page instance from which to capture a screenshot.
     * @param status   A string representing the status of the scenario (e.g., "failed", "passed").
     */
    public static void handleScenarioTeardown(Scenario scenario, Page page, String status) {
        try {
            // Capture a full-page screenshot and store it in a byte array
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

            // Attach the screenshot to the scenario report, with a descriptive name
            scenario.attach(screenshot, "image/png", "Screenshot of the " + status + ": " + scenario.getName());
            logger.info("Screenshot taken for {} scenario: {}", status, scenario.getName());  // Log info about the screenshot
        } catch (Exception e) {
            // Log any errors that occur during the screenshot capturing process
            logger.error("Error taking screenshot: {}", e.getMessage(), e);
        }
    }
}