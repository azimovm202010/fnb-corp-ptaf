package com.ptaf.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.ptaf.action_performer.ElementActionImpl;
import com.ptaf.hooks.Hooks;
import com.ptaf.interfaces.ElementAction;
import com.ptaf.page_helper.PageHelper;
import com.ptaf.utils.ScenarioUtil;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameCommonMethods {
    private final Page page = Hooks.getPage();
    private final ElementAction elementAction;
    private final PageHelper pageHelper;
    private boolean isFailed = false;
    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(FrameCommonMethods.class);

    public FrameCommonMethods(FrameLocator frameLocator) {
        this.pageHelper = new PageHelper(this.page);
        this.elementAction = new ElementActionImpl(this.page);
    }

    public void click(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "click", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"click", element);
            }
        });
    }

    public void fill(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "fill", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"fill", element);
            }
        });
    }

    public void select(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "select", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"select", element);
            }
        });
    }

    public void check(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "check", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"check", element);
            }
        });
    }

    public void uncheck(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "uncheck", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"uncheck", element);
            }
        });
    }

    public void hover(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "hover", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"hover", element);
            }
        });
    }

    public void type(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "type", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"type", element);
            }
        });
    }

    public void press(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "press", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"press", element);
            }
        });
    }

    public void dblclick(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "dblclick", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"dblclick", element);
            }
        });
    }

    public void screenshot(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "screenshot", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"screenshot", element);
            }
        });
    }

    public void scroll(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "scroll", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"scroll", element);
            }
        });
    }

    public void focus(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "focus", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"focus", element);
            }
        });
    }

    public void blur(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "blur", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"blur", element);
            }
        });
    }

    public void clear(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "clear", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"clear", element);
            }
        });
    }

    public void drag(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "drag", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"drag", element);
            }
        });
    }

    public void gettext(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "gettext", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"gettext", element);
            }
        });
    }

    public void isvisible(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "isvisible", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"isvisible", element);
            }
        });
    }

    public void isenabled(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "isenabled", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"isenabled", element);
            }
        });
    }

    public void ischecked(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "ischecked", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"ischecked", element);
            }
        });
    }

    public void contain(FrameLocator frameLocator, String element, String locator, String expectedText) {
        executeStep(() -> {
            boolean actionStatus = elementAction.assertElementTextFrame(frameLocator, element, locator, expectedText);
            if (!actionStatus) {
                handleFailure(page,"contain", element);
            }
        });
    }

    public void exists(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "exists", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"exists", element);
            }
        });
    }

    public void rightclick(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "rightclick", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"rightclick", element);
            }
        });
    }

    public void tap(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "tap", element, locator, null);
            if (!actionStatus) {
                handleFailure(page,"tap", element);
            }
        });
    }

    public void uploadFile(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "uploadfile", element, locator, value);
            if (!actionStatus) {
                handleFailure(page,"uploadfile", element);
            }
        });
    }

    private void executeStep(Runnable step) {
        if (isFailed) {
            return; // Skip if previous steps have failed
        }
        try {
            step.run();
        } catch (Exception e) {
            isFailed = true;
            logger.error("Step execution failed: {}", e.getMessage(), e);
            closeBrowserOnFailure();
            throw new RuntimeException("Step execution failed", e);
        }
    }

    // Failure handling
    private void handleFailure(Page page, String action, String element) {
        isFailed = true;
        logger.error("Action '{}' failed on element '{}'", action, element);
//        captureScreenshot(page, action, element);
        ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Failure");
        closeBrowserOnFailure();
        throw new RuntimeException(String.format("Action '%s' failed on element '%s', skipping further steps", action, element));
    }


    // Screenshot capture for failure
    private void captureScreenshot(FrameLocator frameLocator, String action, String element) {
        try {
            if (page != null && !page.isClosed()) {
                String filePath = "test-output/screenshots/fail/" + String.format("failure_%s_on_%s_%d.png", action, element, System.currentTimeMillis()) + ".png";
                elementAction.performActionFrame(frameLocator, "screenshot", element, "body", filePath);
                logger.info("Screenshot captured for: {}", filePath);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage(), e);
        }
    }

    // Browser closing method
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
        return Hooks.getCurrentScenario(); // Retrieve the current scenario from Hooks
    }
}