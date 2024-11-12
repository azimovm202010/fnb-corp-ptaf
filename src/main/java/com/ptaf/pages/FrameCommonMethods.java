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

/**
 * <h1>FrameCommonMethods</h1>
 *
 * <p>
 * The <code>FrameCommonMethods</code> class encapsulates a collection of methods
 * that facilitate interactions with web elements located within HTML frames. Using
 * this class, automated tests can perform actions such as clicking, filling fields,
 * selecting options, and more on elements contained within frames, leveraging the
 * Playwright library's capabilities.
 * </p>
 *
 * <p>
 * This utility class integrates with the <code>ElementAction</code> to execute
 * defined actions on elements specified through locators. Exceptional cases are
 * handled gracefully, with logging and clean-up procedures incorporated to maintain
 * the robustness of the test execution framework.
 * </p>
 *
 * <p>
 * The class is designed to operate within a multi-threaded environment in tandem
 * with Cucumber scenarios, ensuring that the current scenario context is preserved
 * appropriately.
 * </p>
 */
public class FrameCommonMethods {
    private final Page page = Hooks.getPage(); // Reference to the current Playwright Page instance
    private final ElementAction elementAction; // Instance handling element actions
    private final PageHelper pageHelper; // Helper for additional page operations
    private boolean isFailed = false; // Flag indicating if any action has failed
    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>(); // Thread-local variable for current Cucumber scenario
    private static final Logger logger = LoggerFactory.getLogger(FrameCommonMethods.class); // Logger for traceability

    /**
     * Initializes an instance of <code>FrameCommonMethods</code> with the given <code>FrameLocator</code>.
     *
     * @param frameLocator The FrameLocator instance to interact with frames.
     */
    public FrameCommonMethods(FrameLocator frameLocator) {
        this.pageHelper = new PageHelper(this.page); // Initialize PageHelper with the current page
        this.elementAction = new ElementActionImpl(this.page); // Initialize ElementAction interface
    }

    /**
     * Clicks on an element specified within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being clicked.
     * @param locator The locator string used to identify the element.
     */
    public void click(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "click", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "click", element);
            }
        });
    }

    /**
     * Fills an input field with the specified value.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being filled.
     * @param locator The locator string used to identify the input element.
     * @param value The value to fill in the input field.
     */
    public void fill(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "fill", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "fill", element);
            }
        });
    }

    /**
     * Selects an option from a dropdown within the frame specified by the provided value.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the dropdown.
     * @param locator The locator string used to identify the dropdown.
     * @param value The value of the option to select.
     */
    public void select(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "select", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "select", element);
            }
        });
    }

    /**
     * Checks a checkbox element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the checkbox.
     * @param locator The locator string used to identify the checkbox.
     */
    public void check(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "check", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "check", element);
            }
        });
    }

    /**
     * Unchecks a checkbox element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the checkbox.
     * @param locator The locator string used to identify the checkbox.
     */
    public void uncheck(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "uncheck", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "uncheck", element);
            }
        });
    }

    /**
     * Hovers over a specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to hover.
     * @param locator The locator string used to identify the element.
     */
    public void hover(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "hover", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "hover", element);
            }
        });
    }

    /**
     * Types a specified value into a designated input field within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being typed into.
     * @param locator The locator string used to identify the input field.
     * @param value The value to be typed into the field.
     */
    public void type(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "type", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "type", element);
            }
        });
    }

    /**
     * Presses a specified key on the target element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element on which the key will be pressed.
     * @param locator The locator string used to identify the element.
     * @param value The key to be pressed.
     */
    public void press(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "press", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "press", element);
            }
        });
    }

    /**
     * Double-clicks on the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being double-clicked.
     * @param locator The locator string used to identify the element.
     */
    public void dblclick(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "dblclick", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "dblclick", element);
            }
        });
    }

    /**
     * Takes a screenshot of the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to screenshot.
     * @param locator The locator string used to identify the element.
     * @param value Additional parameters for the screenshot, if necessary.
     */
    public void screenshot(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "screenshot", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "screenshot", element);
            }
        });
    }

    /**
     * Scrolls the page to the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to scroll to.
     * @param locator The locator string used to identify the element.
     */
    public void scroll(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "scroll", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "scroll", element);
            }
        });
    }

    /**
     * Focuses on the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to focus on.
     * @param locator The locator string used to identify the element.
     */
    public void focus(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "focus", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "focus", element);
            }
        });
    }

    /**
     * Removes focus from the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to blur.
     * @param locator The locator string used to identify the element.
     * @param value Additional parameters for blurring, if any.
     */
    public void blur(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "blur", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "blur", element);
            }
        });
    }

    /**
     * Clears the value of an input or element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to clear.
     * @param locator The locator string used to identify the element.
     */
    public void clear(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "clear", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "clear", element);
            }
        });
    }

    /**
     * Drags the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to drag.
     * @param locator The locator string used to identify the element.
     */
    public void drag(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "drag", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "drag", element);
            }
        });
    }

    /**
     * Gets the text content of the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to extract text from.
     * @param locator The locator string used to identify the element.
     */
    public void gettext(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "gettext", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "gettext", element);
            }
        });
    }

    /**
     * Checks whether the given element is visible on the page within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to check visibility.
     * @param locator The locator string used to identify the element.
     */
    public void isvisible(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "isvisible", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "isvisible", element);
            }
        });
    }

    /**
     * Checks if the given element is enabled within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to check.
     * @param locator The locator string used to identify the element.
     */
    public void isenabled(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "isenabled", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "isenabled", element);
            }
        });
    }

    /**
     * Checks if the specified checkbox is currently checked within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the checkbox to check.
     * @param locator The locator string used to identify the checkbox.
     */
    public void ischecked(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "ischecked", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "ischecked", element);
            }
        });
    }

    /**
     * Asserts that the specified element within the frame contains the expected text.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being checked.
     * @param locator The locator string used to identify the element.
     * @param expectedText The expected text that should be contained within the element.
     */
    public void contain(FrameLocator frameLocator, String element, String locator, String expectedText) {
        executeStep(() -> {
            boolean actionStatus = elementAction.assertElementTextFrame(frameLocator, element, locator, expectedText);
            if (!actionStatus) {
                handleFailure(page, "contain", element);
            }
        });
    }

    /**
     * Checks if the specified element exists within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element to check.
     * @param locator The locator string used to identify the element.
     */
    public void exists(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "exists", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "exists", element);
            }
        });
    }

    /**
     * Right-clicks on the specified element within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being right-clicked.
     * @param locator The locator string used to identify the element.
     */
    public void rightclick(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "rightclick", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "rightclick", element);
            }
        });
    }

    /**
     * Taps on the specified element, primarily for mobile applications.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the element being tapped.
     * @param locator The locator string used to identify the element.
     */
    public void tap(FrameLocator frameLocator, String element, String locator) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "tap", element, locator, null);
            if (!actionStatus) {
                handleFailure(page, "tap", element);
            }
        });
    }

    /**
     * Uploads a file to a designated input element (type=file) within the frame.
     *
     * @param frameLocator The FrameLocator instance interacting with the frame.
     * @param element The logical name of the file input.
     * @param locator The locator string used to identify the input element.
     * @param value The path to the file to be uploaded.
     */
    public void uploadFile(FrameLocator frameLocator, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionFrame(frameLocator, "uploadfile", element, locator, value);
            if (!actionStatus) {
                handleFailure(page, "uploadfile", element);
            }
        });
    }

    /**
     * Executes the given step while handling exceptions and marking the
     * overall execution state based on success or failure.
     *
     * @param step A Runnable representing the step to execute.
     */
    private void executeStep(Runnable step) {
        if (isFailed) {
            return; // Skip execution if previous steps have failed
        }
        try {
            step.run();
        } catch (Exception e) {
            isFailed = true; // Mark the execution status as failed
            logger.error("Step execution failed: {}", e.getMessage(), e); // Log the error
            closeBrowserOnFailure(); // Initiate cleanup due to failure
            throw new RuntimeException("Step execution failed", e); // Propagate the exception
        }
    }

    /**
     * Handles failure during an action by logging and performing
     * necessary clean-up operations.
     *
     * @param page The current Playwright Page instance.
     * @param action The action that failed.
     * @param element The logical name of the element involved.
     */
    private void handleFailure(Page page, String action, String element) {
        isFailed = true; // Update internal state to indicate failure
        logger.error("Action '{}' failed on element '{}'", action, element); // Log the failure
        ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Failure"); // Clean up the scenario context
        closeBrowserOnFailure(); // Attempt to close resources on failure
        throw new RuntimeException(String.format("Action '%s' failed on element '%s', skipping further steps", action, element));
    }

    /**
     * Closes the page and browser in case of a failure to ensure
     * proper resource management and prevent dangling sessions.
     */
    private void closeBrowserOnFailure() {
        try {
            if (page != null && !page.isClosed()) {
                page.close(); // Attempt to close the page
                logger.info("Page closed due to failure."); // Log closure of page
            }
        } catch (Exception e) {
            logger.error("Error closing the page: {}", e.getMessage(), e); // Log any errors during closure
        }

        try {
            if (Hooks.getBrowser() != null) {
                Hooks.getBrowser().close(); // Attempt to close the browser instance
                logger.info("Browser closed due to failure."); // Log closure of browser
            }
        } catch (Exception e) {
            logger.error("Error closing the browser: {}", e.getMessage(), e); // Log any errors during closure
        }
    }

    /**
     * Sets the current Cucumber scenario in a thread-local variable.
     *
     * @param scenario The current Cucumber scenario to set.
     */
    public static void setCurrentScenario(Scenario scenario) {
        currentScenario.set(scenario); // Save the scenario context
    }

    /**
     * Retrieves the current Cucumber scenario.
     *
     * @return The current Cucumber scenario.
     */
    private Scenario getCurrentScenario() {
        return Hooks.getCurrentScenario(); // Access the saved scenario context
    }
}