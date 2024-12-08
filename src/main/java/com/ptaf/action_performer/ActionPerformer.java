package com.ptaf.action_performer;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

/**
 * ActionPerformer is a utility class that provides methods to interact with UI elements
 * using Playwright's Locator API. It allows performing different actions on web elements.
 *
 * <h2>Usage:</h2>
 * <ol>
 *     <li>Create an instance of ActionPerformer.</li>
 *     <li>Use the 'performAction' method to execute an action on a specific Locator.</li>
 *     <li>Call 'waitForLocator' to ensure an element is visible before performing actions on it.</li>
 * </ol>
 *
 * <h2>Example:</h2>
 * <pre>
 * ActionPerformer actionPerformer = new ActionPerformer();
 * Locator myLocator = page.locator("selector-for-element");
 * actionPerformer.waitForLocator(myLocator);
 * actionPerformer.performAction("click", myLocator, null);
 * </pre>
 */
public class ActionPerformer {

    private static final Logger logger = LoggerFactory.getLogger(ActionPerformer.class);

    /**
     * Perform the specified action on the given Locator.
     *
     * @param action        The action to perform (e.g., "click", "fill", "select").
     * @param targetLocator The target Locator element on which the action will be performed.
     * @param value         The value for the action (e.g., text to fill in or an attribute to set).
     *                      For actions like "selectmultiple" or "uploadfile", this value should be formatted accordingly.
     *                      It can also be null if not required for the action.
     */
    public void performAction(Page page, String action, Locator targetLocator, String value) {
        try {
            switch (action.toLowerCase()) {
                case "click":
                    targetLocator.click(); // Clicks the element represented by the Locator
                    break;
                case "fill":
                    targetLocator.fill(value); // Fills the input field with the specified value
                    break;
                case "select":
                    targetLocator.selectOption(value); // Selects a single option in a dropdown
                    break;
                case "selectmultiple":
                    targetLocator.selectOption(value.split(",")); // Selects multiple options from a dropdown
                    break;
                case "check":
                    targetLocator.check(); // Checks a checkbox
                    break;
                case "uncheck":
                    targetLocator.uncheck(); // Unchecks a checkbox
                    break;
                case "hover":
                    targetLocator.hover(); // Hovers over the element
                    break;
                case "type":
                    targetLocator.type(value); // Types the specified value into an input field
                    break;
                case "press":
                    targetLocator.press(value); // Simulates pressing a keyboard key
                    break;
                case "dblclick":
                    targetLocator.dblclick(); // Double-clicks the element
                    break;
                case "screenshot":
                    // Takes a screenshot of the element and saves it to the specified file path
                    targetLocator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(value)));
                    break;
                case "scroll":
                    // Scrolls the element into view smoothly
                    targetLocator.evaluate("element => element.scrollIntoView({ behavior: 'smooth', block: 'center' })");
                    break;
                case "focus":
                    targetLocator.focus(); // Sets focus on the element
                    break;
                case "blur":
                    targetLocator.evaluate("element => element.blur()"); // Removes focus from the element
                    break;
                case "clear":
                    targetLocator.clear(); // Clears the text input field
                    break;
                case "drag":
                    // Drags the target element to another specified element
                    Locator target = targetLocator.page().locator(value);
                    targetLocator.dragTo(target);
                    break;
                case "getattribute":
                    // Retrieves the value of a specified attribute and prints it
                    System.out.println("Attribute value: " + targetLocator.getAttribute(value));
                    break;
                case "setattribute":
                    // Sets a specified attribute value for the element
                    targetLocator.evaluate("(element, attributeValue) => element.setAttribute('value', attributeValue)", value);
                    break;
                case "removeattribute":
                    // Removes a specified attribute from the element
                    targetLocator.evaluate("(element, attributeName) => element.removeAttribute(attributeName)", value);
                    break;
                case "gettext":
                    // Retrieves and prints the text content of the element
                    String text = targetLocator.textContent();
                    System.out.println("Element text content: " + text);
                    break;
                case "getvalue":
                    // Retrieves and prints the current value of the element (for input fields)
                    String valueContent = targetLocator.inputValue();
                    System.out.println("Element value: " + valueContent);
                    break;
                case "hasvalue":
                    // Checks if the current value of the input element matches the expected value
                    String currentValue = targetLocator.inputValue();
                    if (currentValue != null && currentValue.equals(value)) {
                        System.out.println("Element has the expected value: " + value);
                    } else {
                        throw new AssertionError("Element does not have the expected value. Expected: " + value + ", but found: " + currentValue);
                    }
                    break;
                case "isvisible":
                    // Asserts that the element is visible on the page
                    boolean isVisible = targetLocator.isVisible();
                    assertCondition(isVisible, "Element is not visible.");
                    break;
                case "isenabled":
                    // Asserts that the element is enabled (interactable)
                    boolean isEnabled = targetLocator.isEnabled();
                    assertCondition(isEnabled, "Element is not enabled.");
                    break;
                case "ischecked":
                    // Asserts that a checkbox is checked
                    boolean isChecked = targetLocator.isChecked();
                    assertCondition(isChecked, "Element is not checked.");
                    break;
                case "exists":
                    // Asserts that the element exists in the DOM
                    boolean exists = targetLocator.count() > 0;
                    assertCondition(exists, "Element does not exist in the DOM.");
                    break;
                case "evaluate":
                    // Evaluates a custom JavaScript expression within the context of the element
                    targetLocator.evaluate(value);
                    break;
                case "waitforelement":
                    // Waits for the element to be present in the DOM and visible
                    targetLocator.waitFor();
                    break;
                case "waitforstate":
                    // Waits for the specified state (e.g., visible, hidden) of the element
                    targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.valueOf(value.toUpperCase())));
                    break;
                case "waitfortext":
                    // Waits until the specified text is present in the element
                    targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    if (!targetLocator.textContent().contains(value)) {
                        throw new AssertionError("Text '" + value + "' not found in element.");
                    }
                    break;
                case "waitforvalue":
                    // Waits until the input value matches the specified value
                    targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    if (!targetLocator.inputValue().equals(value)) {
                        throw new AssertionError("Expected value '" + value + "' not found in element.");
                    }
                    break;
                case "rightclick":
                    // Simulates a right-click on the element
                    targetLocator.click(new Locator.ClickOptions().setButton(MouseButton.valueOf("right")));
                    break;
                case "tap":
                    // Simulates a tap action on touch devices
                    targetLocator.tap();
                    break;
                case "uploadfile":
                    // Uploads a file to the element (input file type)
                    targetLocator.setInputFiles(Paths.get(value)); // Assuming value is a file path
                    break;
                case "dragstart":
                    // Dispatches the dragstart event on the element
                    targetLocator.dispatchEvent("dragstart");
                    break;
                case "dragend":
                    // Dispatches the dragend event on the element
                    targetLocator.dispatchEvent("dragend");
                    break;
                case "input":
                    // Directly sets the input field's value
                    targetLocator.evaluate("(element, val) => element.value = val", value);
                    break;
                case "selectfile":
                    // Similar to uploadfile, but specifically for <input type="file">
                    targetLocator.setInputFiles(Paths.get(value)); // Selects file to upload
                    break;
                // More Playwright assertions can be added here
                case "hastext":
                    // Asserts that the element contains the specified text
                    String elementText = targetLocator.textContent();
                    assertCondition(elementText.contains(value), "Element does not contain text: " + value);
                    break;
                case "hasclass":
                    // Asserts that the element has the specified CSS class
                    String className = value; // Assuming value is the class name
                    boolean hasClass = targetLocator.getAttribute("class").contains(className);
                    assertCondition(hasClass, "Element does not have expected class: " + className);
                    break;
                case "hasequalvalue":
                    // Asserts that the element's value is equal to the expected value
                    String actualValue = targetLocator.inputValue();
                    assertCondition(actualValue.equals(value), "Element's value is not equal to expected value: " + value);
                    break;
                case "isempty":
                    // Asserts that the input is empty
                    String inputValue = targetLocator.inputValue();
                    assertCondition(inputValue.isEmpty(), "Element is not empty.");
                    break;
                case "file_chooser_for_upload":
                    // Wait for a file chooser to be displayed
                    page.waitForFileChooser(() -> click(targetLocator)); // Assuming value is not needed here.
                    break;

                default:
                    throw new IllegalArgumentException("Unknown action: " + action); // Throws error if action is not recognized
            }
        } catch (Exception e) {
            // Logs the error and throws a runtime exception
            logger.error("Error while performing action: {} for Target Locator " + targetLocator, e.getMessage());
            throw new RuntimeException("Action failed: for Target Locator " + targetLocator + e.getMessage(), e);
        }
    }

    private void click(Locator targetLocator) {
        try {
            targetLocator.click();
        } catch (Exception e) {
            logger.error("Error while clicking on target locator: {}", e.getMessage());
            throw new RuntimeException("Click action failed: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts a condition and raises an AssertionError if the condition is false.
     *
     * @param condition The condition to assert.
     * @param errorMessage The error message to display if the assertion fails.
     */
    private void assertCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new AssertionError(errorMessage); // Throws an assertion error with the provided message
        }
    }

    /**
     * Waits for a specified Locator to become visible.
     *
     * @param locator The Locator to wait for.
     *                Will time out after 60 seconds if the element is not visible.
     */
    public void waitForLocator(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE) // Wait until the element is visible
                    .setTimeout(60000)); // 1-minute timeout
        } catch (Exception e) {
            // Logs the error if waiting for the element fails
            logger.error("Failed to wait for the element to be displayed", e);
        }
    }
}