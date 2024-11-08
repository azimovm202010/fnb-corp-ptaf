package com.ptaf.pages;

import com.ptaf.action_performer.ElementActionImpl;
import com.ptaf.hooks.Hooks;
import com.ptaf.interfaces.ElementAction;
import com.ptaf.page_helper.PageHelper;
import com.ptaf.utils.ScenarioUtil;
import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageCommonMethods {
    private Page page;
    private final ElementAction elementAction;
    private final PageHelper pageHelper;
    private boolean isFailed = false;
    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(PageCommonMethods.class);

    public PageCommonMethods(Page page) {
        this.page = page;
        this.elementAction = new ElementActionImpl(page);
        this.pageHelper = new PageHelper(page);
    }

    public void click(Page page, String element, String locator) {
        performAction("click", page, element, locator, null);
    }

    public void fill(Page page, String element, String locator, String value) {
        performAction("fill", page, element, locator, value);
    }

    public void select(Page page, String element, String locator, String value) {
        performAction("select", page, element, locator, value);
    }

    public void check(Page page, String element, String locator) {
        performAction("check", page, element, locator, null);
    }

    public void uncheck(Page page, String element, String locator) {
        performAction("uncheck", page, element, locator, null);
    }

    public void hover(Page page, String element, String locator) {
        performAction("hover", page, element, locator, null);
    }

    public void type(Page page, String element, String locator, String value) {
        performAction("type", page, element, locator, value);
    }

    public void press(Page page, String element, String locator, String value) {
        performAction("press", page, element, locator, value);
    }

    public void dblclick(Page page, String element, String locator) {
        performAction("dblclick", page, element, locator, null);
    }

    public void screenshot(Page page, String element, String locator, String value) {
        performAction("screenshot", page, element, locator, value);
    }

    public void scroll(Page page, String element, String locator) {
        performAction("scroll", page, element, locator, null);
    }

    public void focus(Page page, String element, String locator) {
        performAction("focus", page, element, locator, null);
    }

    public void blur(Page page, String element, String locator, String value) {
        performAction("blur", page, element, locator, value);
    }

    public void clear(Page page, String element, String locator) {
        performAction("clear", page, element, locator, null);
    }

    public void drag(Page page, String element, String locator) {
        performAction("drag", page, element, locator, null);
    }

    public void gettext(Page page, String element, String locator) {
        performAction("gettext", page, element, locator, null);
    }

    public void isvisible(Page page, String element, String locator) {
        performAction("isvisible", page, element, locator, null);
    }

    public void isenabled(Page page, String element, String locator) {
        performAction("isenabled", page, element, locator, null);
    }

    public void ischecked(Page page, String element, String locator) {
        performAction("ischecked", page, element, locator, null);
    }

    public void contain(Page page, String element, String locator, String expectedText) {
        executeStep(() -> {
            boolean actionStatus = elementAction.assertElementTextPage(page, element, locator, expectedText);
            if (!actionStatus) {
                handleFailure(page, "contain", element);
            }
        });
    }

    public void exists(Page page, String element, String locator) {
        performAction("exists", page, element, locator, null);
    }

    public void rightclick(Page page, String element, String locator) {
        performAction("rightclick", page, element, locator, null);
    }

    public void tap(Page page, String element, String locator) {
        performAction("tap", page, element, locator, null);
    }

    public void uploadFile(Page page, String element, String locator, String value) {
        performAction("uploadfile", page, element, locator, value);
    }

    private void performAction(String action, Page page, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionPage(page, action, element, locator, value);
            if (!actionStatus) {
                handleFailure(page, action, element);
            }
        });
    }

    private void executeStep(Runnable step) {
        if (isFailed) {
            // Skip execution if a previous step has failed
            return;
        }
        try {
            step.run();
        } catch (Exception e) {
            isFailed = true;
            logger.error("Step execution failed: {}", e.getMessage(), e);
            handleFailure(page, "Step execution failed", null);
        }
    }

    private void handleFailure(Page page, String action, String element) {
        isFailed = true;
        logger.error("Action '{}' failed on element '{}'", action, element);
        ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Failure Step");
        closeBrowserOnFailure();
        throw new RuntimeException(String.format("Action '%s' failed on element '%s', skipping further steps", action, element));
    }

    private void closeBrowserOnFailure() {
        try {
            if (page != null && !page.isClosed()) {
                page.close();
                logger.info("Page closed due to failure.");
            }
        } catch (Exception e) {
            logger.error("Error closing the page: {}", e.getMessage(), e);
        }

        try {
            if (Hooks.getBrowser() != null) {
                Hooks.getBrowser().close();
                logger.info("Browser closed due to failure.");
            }
        } catch (Exception e) {
            logger.error("Error closing the browser: {}", e.getMessage(), e);
        }
    }

    public static void setCurrentScenario(Scenario scenario) {
        currentScenario.set(scenario);
    }

    private Scenario getCurrentScenario() {
        return Hooks.getCurrentScenario();
    }

    /**
     * Captures a screenshot if all steps in the scenario pass.
     * This should be called at the end of the scenario.
     */
    public void finalizeScenario() {
        if (isFailed) {
            // Capture screenshot after the last successful scenario step
            ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Passed Step");
        }
    }
}