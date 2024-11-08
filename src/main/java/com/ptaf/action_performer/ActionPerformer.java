package com.ptaf.action_performer;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class ActionPerformer {

    private static final Logger logger = LoggerFactory.getLogger(ActionPerformer.class);

    /**
     * Perform the specified action on the given Locator.
     *
     * @param action        The action to perform (click, fill, etc.).
     * @param targetLocator The target Locator element.
     * @param value         The value for the action (used for fill, select, etc.).
     */
    public void performAction(String action, Locator targetLocator, String value) {
        try {
            switch (action.toLowerCase()) {
                case "click":
                    targetLocator.click();
                    break;
                case "fill":
                    targetLocator.fill(value);
                    break;
                case "select":
                    targetLocator.selectOption(value);
                    break;
                case "selectmultiple":
                    targetLocator.selectOption(value.split(",")); // For selecting multiple options
                    break;
                case "check":
                    targetLocator.check();
                    break;
                case "uncheck":
                    targetLocator.uncheck();
                    break;
                case "hover":
                    targetLocator.hover();
                    break;
                case "type":
                    targetLocator.type(value);
                    break;
                case "press":
                    targetLocator.press(value);
                    break;
                case "dblclick":
                    targetLocator.dblclick();
                    break;
                case "screenshot":
                    targetLocator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(value)));
                    break;
                case "scroll":
                    targetLocator.evaluate("element => element.scrollIntoView({ behavior: 'smooth', block: 'center' })");
                    break;
                case "focus":
                    targetLocator.focus();
                    break;
                case "blur":
                    targetLocator.evaluate("element => element.blur()");
                    break;
                case "clear":
                    targetLocator.clear(); // Clear the text input field
                    break;
                case "drag":
                    // Expecting the value to be the target element's selector
                    Locator target = targetLocator.page().locator(value);
                    targetLocator.dragTo(target);
                    break;
                case "getattribute":
                    System.out.println("Attribute value: " + targetLocator.getAttribute(value));
                    break;
                case "setattribute":
                    targetLocator.evaluate("(element, attributeValue) => element.setAttribute('value', attributeValue)", value);
                    break;
                case "removeattribute":
                    targetLocator.evaluate("(element, attributeName) => element.removeAttribute(attributeName)", value);
                    break;
                case "gettext":
                    String text = targetLocator.textContent();
                    System.out.println("Element text content: " + text);
                    break;
                case "getvalue":
                    String valueContent = targetLocator.inputValue();
                    System.out.println("Element value: " + valueContent);
                    break;
                case "isvisible":
                    boolean isVisible = targetLocator.isVisible();
                    System.out.println("Element is visible: " + isVisible);
                    break;
                case "isenabled":
                    boolean isEnabled = targetLocator.isEnabled();
                    System.out.println("Element is enabled: " + isEnabled);
                    break;
                case "ischecked":
                    boolean isChecked = targetLocator.isChecked();
                    System.out.println("Element is checked: " + isChecked);
                    break;
                case "exists":
                    boolean exists = targetLocator.count() > 0;
                    System.out.println("Element exists: " + exists);
                    break;
                case "evaluate":
                    // Evaluate a custom JavaScript expression
                    targetLocator.evaluate(value);
                    break;
                case "waitforelement":
                    targetLocator.waitFor();
                    break;
                case "waitforstate":
                    targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.valueOf(value.toUpperCase())));
                    break;
                case "waitfortext":
                    targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    if (!targetLocator.textContent().contains(value)) {
                        throw new IllegalArgumentException("Text '" + value + "' not found in element");
                    }
                    break;
                case "waitforvalue":
                    targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    if (!targetLocator.inputValue().equals(value)) {
                        throw new IllegalArgumentException("Expected value '" + value + "' not found in element");
                    }
                    break;
                case "rightclick":
                    targetLocator.click(new Locator.ClickOptions().setButton(MouseButton.valueOf("right")));
                    break;
                case "tap":
                    targetLocator.tap();
                    break;
                case "uploadfile":
                    targetLocator.setInputFiles(Paths.get(value)); // Assuming value is a file path
                    break;
                case "dragstart":
                    targetLocator.dispatchEvent("dragstart");
                    break;
                case "dragend":
                    targetLocator.dispatchEvent("dragend");
                    break;
                case "input":
                    targetLocator.evaluate("(element, val) => element.value = val", value); // Directly set input value
                    break;
                case "selectfile":
                    targetLocator.setInputFiles(Paths.get(value)); // Selects file to upload
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } catch (Exception e) {
            logger.error("Error while performing action: {}", e.getMessage());
            throw new RuntimeException("Action failed: " + e.getMessage(), e);
        }
    }

    public void waitForLocator(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(60000)); // 1-minute timeout
        } catch (Exception e) {
//            logger.error("Failed to wait for the element to be displayed", e);
        }
    }
}
