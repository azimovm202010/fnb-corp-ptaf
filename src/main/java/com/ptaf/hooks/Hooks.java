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

/**
 * Hooks class is responsible for managing the setup and teardown processes
 * for automated tests using Cucumber and Playwright. It handles browser
 * initialization, scenario context, and cleanup after each scenario execution.
 *
 * The setup occurs before each scenario, and the cleanup after each scenario.
 */
public class Hooks {

    // Static variables for managing browser, context, and page instances
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static Scenario currentScenario; // Variable to store the current Cucumber scenario
    private static PageCommonMethods pageCommonMethods; // Instance for common page methods

    // Logger for logging information and errors
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Method annotated with @Before to indicate that it will run before each scenario.
     * It initializes the browser, context, and page instances needed for the tests.
     *
     * @param scenario The Cucumber scenario being executed.
     */
    @Before
    public void setUp(Scenario scenario) {
        currentScenario = scenario; // Store the current scenario for later use
        try {
            // Defaulting to Chrome browser for tests; could be configured to use others
            BrowserFactory.BrowserTypeEnum browserTypeEnum = BrowserFactory.BrowserTypeEnum.CHROME;

            // Create a new browser instance (in headless mode by default)
            browser = BrowserFactory.createBrowser(browserTypeEnum);

            // Create a new browser context within which the tests will run
            context = browser.newContext();

            // Create a new page in the context for interaction
            page = context.newPage();

            // Initialize PageCommonMethods to handle interactions with webpage elements
            pageCommonMethods = new PageCommonMethods(page);

            // Log successful setup of the browser
            logger.info("Browser setup completed: {}", browserTypeEnum);
        } catch (Exception e) {
            logger.error("Error setting up the browser: {}", e.getMessage());
            throw new RuntimeException("Browser setup failed", e);
        }
    }

    /**
     * Method annotated with @After to indicate that it will run after each scenario.
     * It cleans up by closing the browser, browser context, and page instances,
     * and takes a screenshot if the scenario fails.
     *
     * @param scenario The Cucumber scenario being executed.
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            // Check the status of the scenario; if passed, finalize it
            if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
                if (pageCommonMethods != null) {
                    pageCommonMethods.finalizeScenario(); // Finalize and capture status for passed scenario
                }
            }
        } catch (Exception e) {
            logger.error("Error during scenario teardown: {}", e.getMessage(), e);
        } finally {
            // Ensure the browser and context are closed regardless of success or failure
            closeBrowserOnFailure();
        }
    }

    /**
     * Helper method to ensure that the page, context, and browser are safely closed.
     */
    private void closeBrowserOnFailure() {
        try {
            // Close the page if it is still active
            if (page != null && !page.isClosed()) {
                page.close(); // Close the Playwright page
                logger.info("Page closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the page: {}", e.getMessage(), e);
        }

        try {
            // Close the browser context if it's still open
            if (context != null) {
                context.close(); // Close the browser context
                logger.info("Browser context closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser context: {}", e.getMessage(), e);
        }

        try {
            // Close the browser if it's still open
            if (browser != null) {
                browser.close(); // Close the browser
                logger.info("Browser closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser: {}", e.getMessage(), e);
        }
    }

    /**
     * Static method to get the current active page instance.
     *
     * @return The current active Page instance.
     * @throws IllegalStateException if the page instance is closed or not initialized.
     */
    public static Page getPage() {
        // Ensure the page is active and hasn't been closed
        if (page == null || page.isClosed()) {
            throw new IllegalStateException("The page is closed or not initialized.");
        }
        return page; // Return the current page instance
    }

    /**
     * Static method to get the current browser instance.
     *
     * @return The current Browser instance.
     * @throws IllegalStateException if the browser instance is not initialized.
     */
    public static Browser getBrowser() {
        // Ensure the browser instance is active
        if (browser == null) {
            throw new IllegalStateException("The browser is not initialized.");
        }
        return browser; // Return the current browser instance
    }

    /**
     * Static method to get the current scenario instance.
     *
     * @return The current Scenario instance.
     */
    public static Scenario getCurrentScenario() {
        return currentScenario; // Return the current scenario
    }

    /**
     * Static method to set the current scenario instance.
     * Typically invoked in the @Before hook to update context.
     *
     * @param scenario The current scenario to be set.
     */
    public static void setCurrentScenario(Scenario scenario) {
        currentScenario = scenario; // Update the stored current scenario
    }
}