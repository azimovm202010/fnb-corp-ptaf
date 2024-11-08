package com.ptaf.interfaces;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides methods to locate elements based on their type (XPath, CSS, etc.)
 * in both page and frame contexts.
 */
public interface ElementLocator {

    /**
     * Locates an element in the context of a page based on its locator type.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS, Button).
     * @param page        The page object where the element is located.
     * @param locator     The locator string used to identify the element.
     * @return A Locator object for the specified element.
     */
    Locator getLocatorForType(String locatorType, Page page, String locator);

    /**
     * Locates an element in the context of a frame based on its locator type.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS, Button).
     * @param frame       The frame object where the element is located.
     * @param locator     The locator string used to identify the element.
     * @return A Locator object for the specified element within the frame.
     */
    Locator getLocatorForType(String locatorType, FrameLocator frame, String locator);
}
