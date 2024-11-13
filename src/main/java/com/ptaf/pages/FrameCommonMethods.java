package com.ptaf.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.ptaf.action_performer.ElementActionImpl;
import com.ptaf.hooks.Hooks;
import com.ptaf.interfaces.ElementAction;
import com.ptaf.page_helper.PageHelper;
import com.ptaf.utils.ScenarioUtil;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FrameCommonMethods {
    private final Page page;
    private final ElementAction elementAction;
    private boolean isFailed = false;
    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(FrameCommonMethods.class);

    public FrameCommonMethods(Page page) {
        this.page = page;
        this.elementAction = new ElementActionImpl(page);
    }

    public void click(Page page, String iFrame, String element, String locator) {
        performAction("click", page, iFrame, element, locator, null);
    }

    public void fill(Page page, String iFrame, String element, String locator, String value) {
        performAction("fill", page, iFrame, element, locator, value);
    }

    public void select(Page page, String iFrame, String element, String locator, String value) {
        performAction("select", page, iFrame, element, locator, value);
    }

    public void check(Page page, String iFrame, String element, String locator) {
        performAction("check", page, iFrame, element, locator, null);
    }

    public void uncheck(Page page, String iFrame, String element, String locator) {
        performAction("uncheck", page, iFrame, element, locator, null);
    }

    public void hover(Page page, String iFrame, String element, String locator) {
        performAction("hover", page, iFrame, element, locator, null);
    }

    public void type(Page page, String iFrame, String element, String locator, String value) {
        performAction("type", page, iFrame, element, locator, value);
    }

    public void press(Page page, String iFrame, String element, String locator, String value) {
        performAction("press", page, iFrame, element, locator, value);
    }

    public void dblclick(Page page, String iFrame, String element, String locator) {
        performAction("dblclick", page, iFrame, element, locator, null);
    }

    public void screenshot(Page page, String iFrame, String element, String locator, String value) {
        performAction("screenshot", page, iFrame, element, locator, value);
    }

    public void scroll(Page page, String iFrame, String element, String locator) {
        performAction("scroll", page, iFrame, element, locator, null);
    }

    public void focus(Page page, String iFrame, String element, String locator) {
        performAction("focus", page, iFrame, element, locator, null);
    }

    public void blur(Page page, String iFrame, String element, String locator, String value) {
        performAction("blur", page, iFrame, element, locator, value);
    }

    public void clear(Page page, String iFrame, String element, String locator) {
        performAction("clear", page, iFrame, element, locator, null);
    }

    public void drag(Page page, String iFrame, String element, String locator) {
        performAction("drag", page, iFrame, element, locator, null);
    }

    public void gettext(Page page, String iFrame, String element, String locator) {
        performAction("gettext", page, iFrame, element, locator, null);
    }

    public void isvisible(Page page, String iFrame, String element, String locator) {
        performAction("isvisible", page, iFrame, element, locator, null);
    }

    public void isenabled(Page page, String iFrame, String element, String locator) {
        performAction("isenabled", page, iFrame, element, locator, null);
    }

    public void ischecked(Page page, String iFrame, String element, String locator) {
        performAction("ischecked", page, iFrame, element, locator, null);
    }

    public void contain(Page page, String iFrame, String element, String locator, String expectedText) {
        executeStep(() -> {
            boolean actionStatus = elementAction.assertElementTextPage(page, element, locator, expectedText);
            if (!actionStatus) {
                handleFailure(page, "contain", element);
            }
        });
    }

    public void exists(Page page, String iFrame, String element, String locator) {
        performAction("exists", page, iFrame, element, locator, null);
    }

    public void rightclick(Page page, String iFrame, String element, String locator) {
        performAction("rightclick", page, iFrame, element, locator, null);
    }

    public void tap(Page page, String iFrame, String element, String locator) {
        performAction("tap", page, iFrame, element, locator, null);
    }

    public void uploadFile(Page page, String iFrame, String element, String locator, String value) {
        performAction("uploadfile", page, iFrame, element, locator, value);
    }

    public void selectMultiple(Page page, String iFrame, String element, String locator) {
        performAction("selectmultiple", page, iFrame, element, locator, null);
    }

    public void getAttribute(Page page, String iFrame, String element, String locator, String value) {
        performAction("getattribute", page, iFrame, element, locator, value);
    }

    public void setAttribute(Page page, String iFrame, String element, String locator, String value) {
        performAction("setattribute", page, iFrame, element, locator, value);
    }

    public void removeAttribute(Page page, String iFrame, String element, String locator, String value) {
        performAction("removeattribute", page, iFrame, element, locator, value);
    }

    public void evaluate(Page page, String iFrame, String element, String locator, String value) {
        performAction("evaluate", page, iFrame, element, locator, value);
    }

    public void waitForElement(Page page, String iFrame, String element, String locator) {
        performAction("waitForelement", page, iFrame, element, locator, null);
    }

    public void waitForState(Page page, String iFrame, String element, String locator) {
        performAction("waitforstate", page, iFrame, element, locator, null);
    }

    public void waitForText(Page page, String iFrame, String element, String locator, String value) {
        performAction("waitfortext", page, iFrame, element, locator, value);
    }

    public void waitForValue(Page page, String iFrame, String element, String locator, String value) {
        performAction("waitforvalue", page, iFrame, element, locator, value);
    }

    public void dragStart(Page page, String iFrame, String element, String locator) {
        performAction("dragstart", page, iFrame, element, locator, null);
    }

    public void dragEnd(Page page, String iFrame, String element, String locator) {
        performAction("dragend", page, iFrame, element, locator, null);
    }

    public void input(Page page, String iFrame, String element, String locator, String value) {
        performAction("input", page, iFrame, element, locator, value);
    }

    public void selectFile(Page page, String iFrame, String element, String locator, String value) {
        performAction("selectfile", page, iFrame, element, locator, value);
    }

    public void hasText(Page page, String iFrame, String element, String locator, String value) {
        performAction("hastext", page, iFrame, element, locator, value);
    }

    public void hasclass(Page page, String iFrame, String element, String locator) {
        performAction("hasclass", page, iFrame, element, locator, null);
    }

    public void hasEqualValue(Page page, String iFrame, String element, String locator, String value) {
        performAction("hasqualvalue", page, iFrame, element, locator, value);
    }

    public void isempty(Page page, String iFrame, String element, String locator) {
        performAction("isempty", page, iFrame, element, locator, null);
    }

    private void performAction(String action, Page page, String iFrame, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionPageFrame(page, iFrame, action, element, locator, value, null);
            if (!actionStatus) {
                handleFailure(page, action, element);
            }
        });
    }

    public void hasvalue(Page page, String iFrame, String element, String locator, String value) {
        performAction("hasvalue", page, iFrame, element, locator, value);
    }

    public void getvalue(Page page, String iFrame, String element, String locator) {
        performAction("getvalue", page, iFrame, element, locator, null);
    }

    public void getListOfElements(Page page, String iFrame, String element, String locator) {
        List<ElementHandle> elements = elementAction.getElementHandleList(page, element, locator, null);

        if (elements.isEmpty()) {
            logger.info("No elements found for the specified locator.");
        } else {
            for (int i = 0; i < elements.size(); i++) {
                ElementHandle handle = elements.get(i);
                logger.info("Element " + (i + 1) + ": " + handle.toString());
            }
        }
    }

    public void clickRadioButton(Page page, String iFrame, String element, String locator) {
        List<ElementHandle> elements = elementAction.getElementHandleList(page, element, locator, null);

        for (ElementHandle radioButton : elements) {
            if (radioButton.isEnabled()) {
                radioButton.check();
                logger.info("Radio button clicked!");
                break;
            } else {
                logger.info("Radio button is not enabled, moving to the next one.");
            }
        }
    }

    private void executeStep(Runnable step) {
        if (isFailed) {
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

    public void finalizeScenario() {
        if (isFailed) {
            ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Passed Step");
        }
    }
}