package com.ptaf.utils;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
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

    public static void handleScenarioTeardown(Scenario scenario, FrameLocator frameLocator, String status) {
        try {
            // Wait for the frame content to be stable before taking a screenshot
            frameLocator.locator("#body").waitFor();

            // Capture a screenshot of the frame and store it in a byte array
            byte[] screenshot = frameLocator.locator("#body").screenshot();

            // Attach the screenshot to the scenario report, with a descriptive name
            scenario.attach(screenshot, "image/png", "Screenshot of the " + status + ": " + scenario.getName());
            logger.info("Screenshot taken for {} scenario: {}", status, scenario.getName());  // Log info about the screenshot
        } catch (Exception e) {
            // Log any errors that occur during the screenshot capturing process
            logger.error("Error taking screenshot: {}", e.getMessage(), e);
        }
    }

//    public static void handleScenarioTeardown(Scenario scenario, Page page, String status, String iFrameSelector) {
//        try {
//            // Capture a full-page screenshot and store it in a byte array
//            byte[] fullPageScreenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
//
//            // Attach the full-page screenshot to the scenario report
//            scenario.attach(fullPageScreenshot, "image/png", "Full Page Screenshot of the " + status + ": " + scenario.getName());
//            logger.info("Full page screenshot taken for {} scenario: {}", status, scenario.getName());
//
//            // Locate the iframe as an ElementHandle
//            ElementHandle frameElement = page.frameLocator(iFrameSelector).first().owner().elementHandle();
//
//            if (frameElement != null) {
//                // Capture a screenshot of the iframe element
//                byte[] frameScreenshot = frameElement.screenshot();
//
//                // Attach the iframe screenshot to the scenario report
//                scenario.attach(frameScreenshot, "image/png", "Screenshot of the iframe in " + status + ": " + scenario.getName());
//                logger.info("Iframe screenshot taken for {} scenario: {}", status, scenario.getName());
//            } else {
//                logger.warn("Iframe with selector '{}' not found for scenario: {}", iFrameSelector, scenario.getName());
//            }
//        } catch (Exception e) {
//            // Log any errors that occur during the screenshot capturing process
//            logger.error("Error taking screenshot: {}", e.getMessage(), e);
//        }
//    }
}
