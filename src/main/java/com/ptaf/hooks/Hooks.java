package com.ptaf.hooks;

import com.ptaf.pages.PageCommonMethods;
import com.ptaf.utils.BrowserFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<PageCommonMethods> pageCommonMethodsThreadLocal = new ThreadLocal<>();

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    private static boolean isLastScenarioFeature = false;
    private static boolean isFirstScenarioInFeature = true;

    @Before
    public void setUp(Scenario scenario) {
        scenarioThreadLocal.set(scenario);

        if (scenario.getSourceTagNames().contains("@LastScenario")) {
            isLastScenarioFeature = true;
        }

        if (isLastScenarioFeature && !isFirstScenarioInFeature) {
            logger.info("Reusing browser instance for feature with @LastScenario tag.");
            return;
        }

        try {
            BrowserFactory.BrowserTypeEnum browserTypeEnum = BrowserFactory.BrowserTypeEnum.CHROME;
            Browser browser = BrowserFactory.createBrowser(browserTypeEnum);
            browserThreadLocal.set(browser);

            BrowserContext context = browser.newContext();
            contextThreadLocal.set(context);

            Page page = context.newPage();
            pageThreadLocal.set(page);

            PageCommonMethods pageCommonMethods = new PageCommonMethods(page);
            pageCommonMethodsThreadLocal.set(pageCommonMethods);

            logger.info("Browser setup completed for scenario: {}", scenario.getName());
        } catch (Exception e) {
            logger.error("Error setting up the browser for scenario: {}", e.getMessage());
            throw new RuntimeException("Browser setup failed", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
                PageCommonMethods pageCommonMethods = pageCommonMethodsThreadLocal.get();
                if (pageCommonMethods != null) {
                    pageCommonMethods.finalizeScenario();
                }
            }
        } catch (Exception e) {
            logger.error("Error during scenario teardown: {}", e.getMessage(), e);
        } finally {
            if (isLastScenarioFeature) {
                logger.info("Skipping browser closure for feature with @LastScenario tag.");
                isFirstScenarioInFeature = false;
            } else {
                closeBrowserResources();
            }
        }
    }

    private void closeBrowserResources() {
        try {
            Page page = pageThreadLocal.get();
            if (page != null && !page.isClosed()) {
                page.close();
                logger.info("Page closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the page: {}", e.getMessage(), e);
        } finally {
            pageThreadLocal.remove();
        }

        try {
            BrowserContext context = contextThreadLocal.get();
            if (context != null) {
                context.close();
                logger.info("Browser context closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser context: {}", e.getMessage(), e);
        } finally {
            contextThreadLocal.remove();
        }

        try {
            Browser browser = browserThreadLocal.get();
            if (browser != null) {
                browser.close();
                logger.info("Browser closed.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser: {}", e.getMessage(), e);
        } finally {
            browserThreadLocal.remove();
        }
    }

    public static Page getPage() {
        Page page = pageThreadLocal.get();
        if (page == null || page.isClosed()) {
            throw new IllegalStateException("The page is closed or not initialized.");
        }
        return page;
    }

    public static Browser getBrowser() {
        Browser browser = browserThreadLocal.get();
        if (browser == null) {
            throw new IllegalStateException("The browser is not initialized.");
        }
        return browser;
    }

    public static Scenario getCurrentScenario() {
        return scenarioThreadLocal.get();
    }

    public static void setCurrentScenario(Scenario scenario) {
        scenarioThreadLocal.set(scenario);
    }
}