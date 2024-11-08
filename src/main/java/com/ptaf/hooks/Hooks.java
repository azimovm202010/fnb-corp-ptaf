package com.ptaf.hooks;

import com.ptaf.pages.PageCommonMethods;
import com.ptaf.utils.BrowserFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.ptaf.utils.ScenarioUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    // Static variables for browser, context, and page instances
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static Scenario currentScenario; // Added to store the current scenario
    private static PageCommonMethods pageCommonMethods;

    // Logger for logging information
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Method annotated with @Before to indicate it runs before each scenario.
     * Sets up the browser, context, and page instances.
     */
    @Before
    public void setUp(Scenario scenario) {
        currentScenario = scenario; // Store the current scenario before each test
        try {
            // Default to using Chrome browser, can be changed to other supported browsers
            BrowserFactory.BrowserTypeEnum browserTypeEnum = BrowserFactory.BrowserTypeEnum.CHROME;

            // Create a new browser instance with headless mode
            browser = BrowserFactory.createBrowser(browserTypeEnum);

            // Create a new browser context
            context = browser.newContext();

            // Create a new page in the browser context
            page = context.newPage();

            // Initialize PageCommonMethods for interacting with page elements
            pageCommonMethods = new PageCommonMethods(page);

            // Log that the browser setup is complete
            logger.info("Browser setup completed: {}", browserTypeEnum);
        } catch (Exception e) {
            logger.error("Error setting up the browser: {}", e.getMessage());
            throw new RuntimeException("Browser setup failed", e);
        }
    }

    /**
     * Method annotated with @After to indicate it runs after each scenario.
     * Cleans up by closing the page, context, and browser instances, and takes a screenshot if the scenario fails.
     *
     * @param scenario The current scenario being executed
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            // Check the status of the scenario and capture the screenshot if the scenario passed
            if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
                if (pageCommonMethods != null) {
                    pageCommonMethods.finalizeScenario();  // Call the method to finalize and capture screenshot for passed scenario
                }
            }
        } catch (Exception e) {
            logger.error("Error during scenario teardown: {}", e.getMessage(), e);
        } finally {
            // Always close the browser and context
            closeBrowserOnFailure();
        }
    }

    /**
     * Helper method to close the page, context, and browser instances safely.
     */
    private void closeBrowserOnFailure() {
        try {
            // Close the page if it's still open
            if (page != null && !page.isClosed()) {
                page.close();
                logger.info("Page closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the page: {}", e.getMessage(), e);
        }

        try {
            // Close the browser context if it's still open
            if (context != null) {
                context.close();
                logger.info("Browser context closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser context: {}", e.getMessage(), e);
        }

        try {
            // Close the browser if it's still open
            if (browser != null) {
                browser.close();
                logger.info("Browser closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser: {}", e.getMessage(), e);
        }
    }

    /**
     * Static method to get the current page instance.
     *
     * @return The current page instance
     */
    public static Page getPage() {
        // Check if the page is still available and hasn't been closed
        if (page == null || page.isClosed()) {
            throw new IllegalStateException("The page is closed or not initialized.");
        }
        return page;
    }

    /**
     * Static method to get the current browser instance.
     *
     * @return The current browser instance
     */
    public static Browser getBrowser() {
        // Check if the browser is still available
        if (browser == null) {
            throw new IllegalStateException("The browser is not initialized.");
        }
        return browser;
    }

    /**
     * Static method to get the current scenario instance.
     *
     * @return The current scenario instance
     */
    public static Scenario getCurrentScenario() {
        return currentScenario;
    }

    /**
     * Static method to set the current scenario instance.
     * This is typically called in the @Before hook.
     *
     * @param scenario The current scenario to be set
     */
    public static void setCurrentScenario(Scenario scenario) {
        currentScenario = scenario;
    }
}
