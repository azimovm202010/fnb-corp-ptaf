package com.ptaf.pages;

import com.microsoft.playwright.ElementHandle;
import com.ptaf.action_performer.ElementActionImpl;
import com.ptaf.hooks.Hooks;
import com.ptaf.interfaces.ElementAction;
import com.ptaf.page_helper.PageHelper;
import com.ptaf.utils.ScenarioUtil;
import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <h1>PageCommonMethods</h1>
 *
 * <p>
 * The <code>PageCommonMethods</code> class serves as a foundational
 * utility for executing common web element interactions in a
 * Playwright-powered test automation framework. This class encapsulates
 * operations such as clicking, filling inputs, verifying visibility,
 * and more, abstracting these actions to promote code reusability and
 * maintainability across multiple page objects.
 * </p>
 *
 * <p>
 * The class utilizes an <code>ElementAction</code> instance to perform
 * interactions with web elements specified by locators. Robust error
 * handling is incorporated to gracefully manage failures, ensuring that
 * the test execution proceeds smoothly while logging pertinent information.
 * The screenshot functionality captures the state of the application when
 * scenarios pass or fail, serving as a vital tool for debugging and
 * validation.
 * </p>
 *
 * <p>
 * This class is designed to be thread-safe and works in conjunction
 * with Cucumber scenarios to maintain context-specific information.
 * </p>
 */
public class PageCommonMethods {
    private Page page; // Playwright Page instance to interact with the browser
    private final ElementAction elementAction; // Interface instance handling element actions
    private final PageHelper pageHelper; // Utility class facilitating additional page operations
    private boolean isFailed = false; // Flag indicating if any action has failed
    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>(); // Thread-local for maintaining the current Cucumber scenario
    private static final Logger logger = LoggerFactory.getLogger(PageCommonMethods.class); // Logger for logging events

    /**
     * Initializes an instance of <code>PageCommonMethods</code> with a given Playwright page.
     *
     * @param page The Playwright Page instance to interact with.
     */
    public PageCommonMethods(Page page) {
        this.page = page;
        this.elementAction = new ElementActionImpl(page); // Initialize element action performer
        this.pageHelper = new PageHelper(page); // Initialize page helper instance
    }

    /**
     * Clicks on a web element specified by the locator.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being clicked.
     * @param locator The locator string used to identify the element.
     */
    public void click(Page page, String element, String locator) {
        performAction("click", page, element, locator, null);
    }

    /**
     * Fills an input field with the specified value.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being filled.
     * @param locator The locator string used to identify the input element.
     * @param value The value to be filled in the field.
     */
    public void fill(Page page, String element, String locator, String value) {
        performAction("fill", page, element, locator, value);
    }

    /**
     * Selects an option from a dropdown based on the provided value.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the dropdown.
     * @param locator The locator string used to identify the dropdown.
     * @param value The value of the option to be selected.
     */
    public void select(Page page, String element, String locator, String value) {
        performAction("select", page, element, locator, value);
    }

    /**
     * Checks a checkbox element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the checkbox.
     * @param locator The locator string used to identify the checkbox.
     */
    public void check(Page page, String element, String locator) {
        performAction("check", page, element, locator, null);
    }

    /**
     * Unchecks a checkbox element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the checkbox.
     * @param locator The locator string used to identify the checkbox.
     */
    public void uncheck(Page page, String element, String locator) {
        performAction("uncheck", page, element, locator, null);
    }

    /**
     * Hovers over a specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to hover.
     * @param locator The locator string used to identify the element.
     */
    public void hover(Page page, String element, String locator) {
        performAction("hover", page, element, locator, null);
    }

    /**
     * Types a specified value into a designated input field.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being typed into.
     * @param locator The locator string used to identify the input field.
     * @param value The value to be typed into the field.
     */
    public void type(Page page, String element, String locator, String value) {
        performAction("type", page, element, locator, value);
    }

    /**
     * Presses a specified key on the target element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element on which the key will be pressed.
     * @param locator The locator string used to identify the element.
     * @param value The key to be pressed.
     */
    public void press(Page page, String element, String locator, String value) {
        performAction("press", page, element, locator, value);
    }

    /**
     * Double-clicks on the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being double-clicked.
     * @param locator The locator string used to identify the element.
     */
    public void dblclick(Page page, String element, String locator) {
        performAction("dblclick", page, element, locator, null);
    }

    /**
     * Takes a screenshot of the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to screenshot.
     * @param locator The locator string used to identify the element.
     * @param value Additional parameters for screenshots, if any.
     */
    public void screenshot(Page page, String element, String locator, String value) {
        performAction("screenshot", page, element, locator, value);
    }

    /**
     * Scrolls the page to the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to scroll to.
     * @param locator The locator string used to identify the element.
     */
    public void scroll(Page page, String element, String locator) {
        performAction("scroll", page, element, locator, null);
    }

    /**
     * Focuses on the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to focus on.
     * @param locator The locator string used to identify the element.
     */
    public void focus(Page page, String element, String locator) {
        performAction("focus", page, element, locator, null);
    }

    /**
     * Removes focus from the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to blur.
     * @param locator The locator string used to identify the element.
     * @param value Additional parameters for blurring, if any.
     */
    public void blur(Page page, String element, String locator, String value) {
        performAction("blur", page, element, locator, value);
    }

    /**
     * Clears the value of an input or element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to clear.
     * @param locator The locator string used to identify the element.
     */
    public void clear(Page page, String element, String locator) {
        performAction("clear", page, element, locator, null);
    }

    /**
     * Drags the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to drag.
     * @param locator The locator string used to identify the element.
     */
    public void drag(Page page, String element, String locator) {
        performAction("drag", page, element, locator, null);
    }

    /**
     * Gets the text content of the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to extract text from.
     * @param locator The locator string used to identify the element.
     */
    public void gettext(Page page, String element, String locator) {
        performAction("gettext", page, element, locator, null);
    }

    /**
     * Checks whether the given element is visible on the page.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to check visibility.
     * @param locator The locator string used to identify the element.
     */
    public void isvisible(Page page, String element, String locator) {
        performAction("isvisible", page, element, locator, null);
    }

    /**
     * Checks if the given element is enabled.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to check.
     * @param locator The locator string used to identify the element.
     */
    public void isenabled(Page page, String element, String locator) {
        performAction("isenabled", page, element, locator, null);
    }

    /**
     * Checks if the specified checkbox is currently checked.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the checkbox to check.
     * @param locator The locator string used to identify the checkbox.
     */
    public void ischecked(Page page, String element, String locator) {
        performAction("ischecked", page, element, locator, null);
    }

    /**
     * Asserts that the specified element contains the expected text.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being checked.
     * @param locator The locator string used to identify the element.
     * @param expectedText The expected text that should be contained within the element.
     */
    public void contain(Page page, String element, String locator, String expectedText) {
        executeStep(() -> {
            boolean actionStatus = elementAction.assertElementTextPage(page, element, locator, expectedText);
            if (!actionStatus) {
                handleFailure(page, "contain", element);
            }
        });
    }

    /**
     * Checks if the specified element exists on the page.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element to check.
     * @param locator The locator string used to identify the element.
     */
    public void exists(Page page, String element, String locator) {
        performAction("exists", page, element, locator, null);
    }

    /**
     * Right-clicks on the specified element.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being right-clicked.
     * @param locator The locator string used to identify the element.
     */
    public void rightclick(Page page, String element, String locator) {
        performAction("rightclick", page, element, locator, null);
    }

    /**
     * Taps on the specified element, primarily for mobile scenarios.
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the element being tapped.
     * @param locator The locator string used to identify the element.
     */
    public void tap(Page page, String element, String locator) {
        performAction("tap", page, element, locator, null);
    }

    /**
     * Uploads a file to a designated input element (type=file).
     *
     * @param page The current Playwright Page.
     * @param element The logical name of the file input.
     * @param locator The locator string used to identify the input.
     * @param value The path to the file to be uploaded.
     */
    public void uploadFile(Page page, String element, String locator, String value) {
        performAction("uploadfile", page, element, locator, value);
    }

    public void selectMultiple(Page page, String element, String locator) {
        performAction("selectmultiple", page, element, locator, null);
    }

    public void getAttribute(Page page, String element, String locator, String value) {
        performAction("getattribute", page, element, locator, value);
    }

    public void setAttribute(Page page, String element, String locator, String value) {
        performAction("setattribute", page, element, locator, value);
    }

    public void removeAttribute(Page page, String element, String locator, String value) {
        performAction("removeattribute", page, element, locator, value);
    }

    public void evaluate(Page page, String element, String locator, String value) {
        performAction("evaluate", page, element, locator, value);
    }

    public void waitForElement(Page page, String element, String locator) {
        performAction("waitForelement", page, element, locator, null);
    }

    public void waitForState(Page page, String element, String locator) {
        performAction("waitforstate", page, element, locator, null);
    }

    public void waitForText(Page page, String element, String locator, String value) {
        performAction("waitfortext", page, element, locator, value);
    }

    public void waitForValue(Page page, String element, String locator, String value) {
        performAction("waitforvalue", page, element, locator, value);
    }

    public void dragStart(Page page, String element, String locator) {
        performAction("dragstart", page, element, locator, null);
    }

    public void dragEnd(Page page, String element, String locator) {
        performAction("dragend", page, element, locator, null);
    }

    public void input(Page page, String element, String locator, String value) {
        performAction("input", page, element, locator, value);
    }

    public void selectFile(Page page, String element, String locator, String value){
        performAction("selectfile", page, element, locator, value);
    }

    public void hasText(Page page, String element, String locator, String value) {
        performAction("hastext", page, element, locator, value);
    }

    public void hasclass(Page page, String element, String locator) {
        performAction("hasclass", page, element, locator, null);
    }

    public void hasEqualValue(Page page, String element, String locator, String value) {
        performAction("hasqualvalue", page, element, locator, value);
    }

    public void isempty(Page page, String element, String locator) {
        performAction("isempty", page, element, locator, null);
    }

    /**
     * Centralized method to perform actions on elements,
     * managing success and failure scenarios.
     *
     * @param action The action to perform (e.g., "click", "fill").
     * @param page The current Playwright Page.
     * @param element The logical name of the element involved in the action.
     * @param locator The locator string used to identify the element.
     * @param value Any additional value needed for the action, if required.
     */
    private void performAction(String action, Page page, String element, String locator, String value) {
        executeStep(() -> {
            boolean actionStatus = elementAction.performActionPage(page, action, element, locator, value);
            if (!actionStatus) {
                handleFailure(page, action, element);
            }
        });
    }

    /**
     * Checks if the specified element has the expected value.
     *
     * <p>
     * This method interacts with the specified web element, identified by the
     * provided locator, to verify if its current value matches the expected value.
     * This is useful for assertions in test scenarios where the correctness of
     * input fields or other value-bearing elements needs to be validated.
     * </p>
     *
     * @param page The current Playwright Page instance used to interact with the browser.
     * @param element The logical name of the element being checked, for logging purposes.
     * @param locator The locator string used to identify the element on the page.
     * @param value The expected value that the element should have.
     */
    public void hasvalue(Page page, String element, String locator, String value) {
        performAction("hasvalue", page, element, locator, value); // Executes the action to check the value
    }

    /**
     * Retrieves the current value of the specified element.
     *
     * <p>
     * This method interacts with the element identified by the locator and
     * retrieves its current value. This can be useful in scenarios where the
     * current state of a field (for example, after a user action) needs to be
     * checked or stored for further verification.
     * </p>
     *
     * @param page The current Playwright Page instance used to interact with the browser.
     * @param element The logical name of the element whose value is being retrieved, for logging purposes.
     * @param locator The locator string used to identify the element on the page.
     */
    public void getvalue(Page page, String element, String locator) {
        performAction("getvalue", page, element, locator, null); // Executes the action to retrieve the value
    }
    /**
     * Retrieves and prints a list of elements on the specified page.
     * This method locates elements based on the provided element name and locator key,
     * and prints each located element's details for debugging or verification purposes.
     *
     * @param page     The Playwright Page object representing the current browser page.
     * @param element  The name of the element to locate, typically corresponding to a descriptive identifier.
     * @param locator  The locator key, as specified in the YAML configuration, used to retrieve the element.
     */
    public void getListOfElements(Page page, String element, String locator) {
        // Retrieve a list of element handles matching the provided element and locator key on the page.
        List<ElementHandle> elements = elementAction.getElementHandleList(page, element, locator, null);

        // Check if elements list is empty and log appropriate information
        if (elements.isEmpty()) {
            logger.info("No elements found for the specified locator.");
        } else {
            // Iterate through each element in the list and print its details.
            for (int i = 0; i < elements.size(); i++) {
                ElementHandle handle = elements.get(i);
                logger.info("Element " + (i + 1) + ": " + handle.toString());
            }
        }
    }

    /**
     * Attempts to click a radio button within a list of elements on a specified page.
     * This method retrieves a list of element handles matching the provided element name and locator key,
     * then iterates through each radio button in the list until it finds an enabled one to click.
     *
     * @param page     The Playwright Page object representing the current browser page.
     * @param element  The name of the element to locate, typically corresponding to a descriptive identifier.
     * @param locator  The locator key, as specified in the YAML configuration, used to retrieve the element.
     */
    public void clickRadioButton(Page page, String element, String locator) {
        // Retrieve a list of element handles matching the provided element and locator key on the page.
        List<ElementHandle> elements = elementAction.getElementHandleList(page, element, locator, null);

        // Iterate through the list of element handles.
        for (ElementHandle radioButton : elements) {
            // Check if the current radio button is enabled and, if so, select it.
            if (radioButton.isEnabled()) {
                radioButton.check();  // Selects (clicks) the radio button
                logger.info("Radio button clicked!");  // Logs the successful selection
                break;  // Exit the loop once a radio button is successfully selected
            } else {
                // If the radio button is not enabled, log the information and continue to the next element.
                logger.info("Radio button is not enabled, moving to the next one.");
            }
        }
    }

    /**
     * Executes a step while monitoring for exceptions and
     * logging failures appropriately.
     *
     * @param step A Runnable representing the action to be executed.
     */
    private void executeStep(Runnable step) {
        if (isFailed) {
            // Skip execution of further steps if a previous one has failed
            return;
        }
        try {
            step.run();
        } catch (Exception e) {
            isFailed = true; // Mark the failure
            logger.error("Step execution failed: {}", e.getMessage(), e); // Log the error
            handleFailure(page, "Step execution failed", null); // Handle failure cleanly
        }
    }

    /**
     * Handles a failure during execution by logging errors and
     * triggering cleanup measures.
     *
     * @param page The current Playwright Page.
     * @param action The action that failed.
     * @param element The logical name of the element involved.
     */
    private void handleFailure(Page page, String action, String element) {
        isFailed = true; // Update internal state to indicate failure
        logger.error("Action '{}' failed on element '{}'", action, element); // Log the failure
        ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Failure Step"); // Clean up for the scenario
        closeBrowserOnFailure(); // Attempt to close resources on failure
        throw new RuntimeException(String.format("Action '%s' failed on element '%s', skipping further steps", action, element));
    }

    /**
     * Closes the page and the browser if a failure occurs.
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
     * Stores the current Cucumber scenario in a thread-local variable.
     *
     * @param scenario The current Cucumber scenario.
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
        return Hooks.getCurrentScenario(); // Access the saved scenario
    }

    /**
     * Finalizes the scenario. Captures a screenshot if all steps have passed.
     * This method should be called at the completion of a scenario.
     */
    public void finalizeScenario() {
        if (isFailed) {
            // Handle the scenario teardown if a step has failed
            ScenarioUtil.handleScenarioTeardown(getCurrentScenario(), page, "Passed Step");
        }
    }
}