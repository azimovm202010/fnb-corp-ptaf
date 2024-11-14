package com.ptaf.action_performer;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ptaf.handlers.LocatorHandler;
import com.ptaf.helpers.ElementLocatorHelper;
import com.ptaf.interfaces.ElementAction;
import com.ptaf.page_helper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * ElementActionImpl is an implementation of the ElementAction interface that provides methods for
 * performing actions and assertions on web elements within an instance of a Playwright Page or FrameLocator.
 * It utilizes the ActionPerformer, LocatorHandler, and ElementLocatorHelper to manage interactions
 * with various elements on a web page.
 * <p>
 * Usage:
 * 1. Instantiate ElementActionImpl with a Page object.
 * 2. Call `performActionPage` or `performActionFrame` to execute specific actions on elements.
 * 3. Use the `assertElementTextPage` or `assertElementTextFrame` methods for validating the text
 * content of elements.
 */
public class ElementActionImpl extends PageHelper implements ElementAction {
    private static final Logger logger = LoggerFactory.getLogger(ElementActionImpl.class);
    private final ActionPerformer actionPerformer = new ActionPerformer(); // Handles action execution on Locators
    private final ElementLocatorHelper elementLocatorHelper = new ElementLocatorHelper(); // Assists in locating elements
    private final LocatorHandler locatorHandler = new LocatorHandler(); // Manages Locator creation based on type

    /**
     * Constructor for ElementActionImpl. Inheritance from PageHelper allows
     * initializing with a Page instance for element interaction.
     *
     * @param page The Playwright Page instance to interact with web elements.
     */
    public ElementActionImpl(Page page) {
        super(page); // Initialize the PageHelper with the provided Page
    }

    /**
     * Perform an action on an element located on the current Page.
     *
     * @param page    The Page where the action is performed.
     * @param action  The action to perform (e.g., "click", "fill").
     * @param element The identifier for the target element.
     * @param key     Additional identifier key for locating the element.
     * @param value   The value associated with the action (if applicable).
     * @return boolean indicating success or failure of the action.
     */
    @Override
    public boolean performActionPage(Page page, String action, String element, String key, String value) {
        return performAction(page, null, action, element, key, value, null); // Delegates to private method with null frame
    }

    /**
     * Perform an action on an element located in a specified Frame.
     *
     * @param frameLocator The FrameLocator for identifying the frame containing the element.
     * @param action       The action to perform.
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param value        The value associated with the action (if applicable).
     * @return boolean indicating success or failure of the action.
     */
    @Override
    public boolean performActionFrame(FrameLocator frameLocator, String action, String element, String key, String value) {
        return performAction(null, null, action, element, key, value, frameLocator); // Delegates with null page
    }


    @Override
    public boolean performActionPageFrame(Page page, String iFrame, String action, String element, String key, String value, FrameLocator frameLocator) {
        return performAction(page, iFrame, action, element, key, value, null);
    }


    /**
     * Retrieves a list of element handles based on a specified element located within a Page context,
     * and returns a boolean indicating if any matching elements were found.
     *
     * @param page    The Playwright Page instance on which to locate the element.
     * @param element The identifier for the target element.
     * @param key     An additional key used to assist in locating the element.
     * @return boolean indicating whether one or more element handles were located on the Page.
     */
    @Override
    public boolean getElementHandlePage(Page page, String element, String key) {
        // Use the getElementHandleList method to retrieve all ElementHandles matching the specified element in the Page context
        List<ElementHandle> elementHandles = getElementHandleList(page, element, key, null);

        // Returns true if element handles were found; otherwise, returns false
        return !elementHandles.isEmpty();
    }

    /**
     * Retrieves a list of element handles based on a specified element located within a Frame context,
     * and returns a boolean indicating if any matching elements were found.
     *
     * @param frameLocator The FrameLocator instance for identifying the frame containing the target element.
     * @param element      The identifier for the target element within the frame.
     * @param key          An additional key used to assist in locating the element.
     * @return boolean indicating whether one or more element handles were located within the specified frame.
     */
    @Override
    public boolean getElementHandleFrame(FrameLocator frameLocator, String element, String key) {
        // Use the getElementHandleList method to retrieve all ElementHandles matching the specified element in the Frame context
        List<ElementHandle> elementHandles = getElementHandleList(null, element, key, frameLocator);

        // Returns true if element handles were found; otherwise, returns false
        return !elementHandles.isEmpty();
    }


    /**
     * Asserts that the text content of an element on the current Page matches the expected text.
     *
     * @param page         The Page where the assertion is performed.
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param expectedText The expected text to match against the element's content.
     * @return boolean indicating whether the text matches the expected value.
     */
    @Override
    public boolean assertElementTextPage(Page page, String element, String key, String expectedText) {
        return assertElementText(page, element, key, expectedText, null); // Delegates to private method with null frame
    }

    /**
     * Asserts that the text content of an element in a specified Frame matches the expected text.
     *
     * @param frameLocator The FrameLocator for identifying the frame containing the element.
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param expectedText The expected text to match against the element's content.
     * @return boolean indicating whether the text matches the expected value.
     */
    @Override
    public boolean assertElementTextFrame(FrameLocator frameLocator, String element, String key, String expectedText) {
        return assertElementText(null, element, key, expectedText, frameLocator); // Delegates with null page
    }

    /**
     * Internal method for performing actions on either a Page or FrameLocator.
     *
     * @param page         The Page object (if applicable).
     * @param iFrame       The identifier for the iframe (if applicable).
     * @param action       The action to perform.
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param value        The value associated with the action (if applicable).
     * @param frameLocator The FrameLocator (if applicable).
     * @return boolean indicating success or failure of the action.
     */
    private boolean performAction(Page page, String iFrame, String action, String element, String key, String value, FrameLocator frameLocator) {
        Locator targetLocator = null;

        // Determine the correct Locator based on the context (Page, FrameLocator, or Page with iframe)
        try {
            if (page != null && iFrame == null) {
                targetLocator = getLocatorBasedOnPage(page, element, key);
            } else if (frameLocator != null) {
                targetLocator = getLocatorBasedOnFrame(frameLocator, element, key);
            } else if (page != null) {
                targetLocator = getLocatorBasedOnPageFrame(page, iFrame, element, key);
            } else {
                throw new IllegalArgumentException("Both page and frameLocator cannot be null.");
            }

            if (targetLocator == null) {
                throw new IllegalStateException("Failed to resolve a target Locator.");
            }

            // Ensure the Locator is ready before performing actions
            actionPerformer.waitForLocator(targetLocator);

            // Execute the action using ActionPerformer
            actionPerformer.performAction(action, targetLocator, value);
            return true; // Action was successful
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument provided: {}", e.getMessage());
        } catch (IllegalStateException e) {
            logger.error("Locator resolution error: {}", e.getMessage());
        } catch (Exception e) {
            // Log other errors and return false to indicate failure
            logger.error("Error while performing action '{}' on element '{}'", action, element, e);
        }

        return false; // Indicate action failed
    }


    /**
     * Internal method to assert the text content of an element, applicable to Page or Frame.
     *
     * @param page         The Page object (if applicable).
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param expectedText The expected text to match against the element's content.
     * @param frameLocator The FrameLocator (if applicable).
     * @return boolean indicating whether the text matches the expected value.
     */
    private boolean assertElementText(Page page, String element, String key, String expectedText, FrameLocator frameLocator) {
        try {
            // Determine the correct Locator based on the context
            Locator targetLocator = (page != null)
                    ? getLocatorBasedOnPage(page, element, key)
                    : getLocatorBasedOnFrame(frameLocator, element, key);
            String actualText = targetLocator.textContent(); // Retrieve the actual text content
            boolean isTextMatching = expectedText.equals(actualText); // Compare expected and actual text

            // Log the actual and expected text content
            logger.info("Asserting text on element '{}': expected '{}', actual '{}'", element, expectedText, actualText);

            if (!isTextMatching) {
                logger.error("Text mismatch: expected '{}' but found '{}'", expectedText, actualText); // Log error on mismatch
            }
            return isTextMatching; // Return result of text comparison
        } catch (Exception e) {
            // Log the error details and return false to indicate failure
            logger.error("Error while asserting text on element '{}'", element, e);
            return false;
        }
    }

    /**
     * Retrieves a list of ElementHandles for a given element, considering page and frame contexts.
     *
     * @param page         The Page object (if applicable).
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param frameLocator The FrameLocator (if applicable).
     * @return List<ElementHandle> containing the element handles, or an empty list if not found.
     */
    @Override
    public List<ElementHandle> getElementHandleList(Page page, String element, String key, FrameLocator frameLocator) {
        List<ElementHandle> elementHandles = new ArrayList<>();
        try {
            Locator targetLocator = (page != null)
                    ? getLocatorBasedOnPage(page, element, key)
                    : getLocatorBasedOnFrame(frameLocator, element, key);

            if (targetLocator != null) {
                elementHandles = targetLocator.elementHandles();
            } else {
                logger.error("Target locator for element '{}' could not be determined.", element);
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve element handles for '{}'", element, e);
        }
        return elementHandles;
    }

    /**
     * Retrieves a Locator based on a given Page context.
     *
     * @param page    The Page from which to locate the element.
     * @param element The identifier for the target element.
     * @param key     Additional identifier key for locating the element.
     * @return The Locator for the identified element.
     */
    private Locator getLocatorBasedOnPage(Page page, String element, String key) {
        return getLocator(null, element, key, page, null);
    }

    private Locator getLocatorBasedOnPageFrame(Page page, String iFrame, String element, String key) {
        return getLocator(iFrame, element, key, page, null);
    }

    /**
     * Retrieves a Locator based on a given Frame context.
     *
     * @param frameLocator The FrameLocator containing the element.
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @return The Locator for the identified element.
     */
    private Locator getLocatorBasedOnFrame(FrameLocator frameLocator, String element, String key) {
        return getLocator(null, element, key, null, frameLocator);
    }

    /**
     * Retrieves a Locator for the specified element by determining its type and context.
     *
     * @param element      The identifier for the target element.
     * @param key          Additional identifier key for locating the element.
     * @param page         The Page object (if applicable).
     * @param frameLocator The FrameLocator (if applicable).
     * @return The Locator for the identified element.
     * @throws RuntimeException If the context (Page or Frame) is unknown.
     */
    private Locator getLocator(String iFrame, String element, String key, Page page, FrameLocator frameLocator) {
        String locatorValue = elementLocatorHelper.getElement(element, key); // Get the locator value based on the element and key
        String locatorType = elementLocatorHelper.getLocatorType(locatorValue); // Get the locator type (e.g., ID, class)
        String locator = elementLocatorHelper.getLocator(locatorValue); // Extract the locator string

        try {
            // Return the appropriate Locator based on whether being called in a Page or Frame context
            if (iFrame == null) {
                return locatorHandler.getLocatorForType(locatorType, page, locator);
            } else if (frameLocator != null) {
                return locatorHandler.getLocatorForType(locatorType, frameLocator, locator);
            } else if (page != null) {
                return locatorHandler.getLocatorForTypeFrame(page, iFrame, locatorType, locator);
            }
                // If both page and frameLocator are null, handle the unknown context case
                throw new IllegalArgumentException("Both page and frameLocator cannot be null");

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to get locator for context: " + (page != null ? "Page" : "Frame"), e);
        }
    }

}
