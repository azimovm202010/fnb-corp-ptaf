package com.ptaf.utils;

import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScenarioUtil {
    private static final Logger logger = LoggerFactory.getLogger(ScenarioUtil.class);

    /**
     * Handles the scenario teardown process, including taking a screenshot if the scenario fails.
     *
     * @param scenario The current scenario being executed
     * @param page     The Playwright page instance
     */
    public static void handleScenarioTeardown(Scenario scenario, Page page, String status) {
        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "Screenshot of the "+ status + ": " + scenario.getName());
            logger.info("Screenshot taken for failed scenario: {}", scenario.getName());
        } catch (Exception e) {
            logger.error("Error taking screenshot: {}", e.getMessage(), e);
        }
    }


}
