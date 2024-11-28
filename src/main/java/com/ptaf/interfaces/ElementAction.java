package com.ptaf.interfaces;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * ElementAction interface defines a set of methods for interacting with web elements
 * on a page or within a frame using Playwright. It provides abstractions for performing
 * actions and assertions on these elements, making it easier to automate user interactions
 * and verify content.
 */
public interface ElementAction {

    /**
     * Performs an action on a web element located on the main page.
     *
     * @param page   The Playwright Page instance representing the current web page.
     * @param action The action to be performed (e.g., click, type).
     * @param element The name or identifier of the element on which the action will be performed.
     * @param key    The specific key or locator used to identify the element.
     * @param value  The value to be used with the action (e.g., the text to type).
     * @return true if the action was performed successfully, false otherwise.
     */
    boolean performActionPage(Page page, String action, String element, String key, String value);

    /**
     * Performs an action on a web element located within a frame.
     *
     * @param frameLocator The Playwright FrameLocator instance used to identify the frame.
     * @param action The action to be performed (e.g., click, type).
     * @param element The name or identifier of the element on which the action will be performed.
     * @param key    The specific key or locator used to identify the element.
     * @param value  The value to be used with the action (e.g., the text to type).
     * @return true if the action was performed successfully, false otherwise.
     */
    boolean performActionFrame(FrameLocator frameLocator, String action, String element, String key, String value);


    boolean performActionPageFrame(Page page, String iFrame, String iFrame_2, String iFrame_3, String action, String element, String key, String value, FrameLocator frameLocator);

    boolean getElementHandlePage(Page page, String element, String key);

    boolean getElementHandleFrame(FrameLocator frameLocator, String element, String key);

    /**
     * Asserts that the text of a web element on the main page matches the expected text.
     *
     * @param page         The Playwright Page instance representing the current web page.
     * @param element      The name or identifier of the element for which the text is being asserted.
     * @param key          The specific key or locator used to identify the element.
     * @param expectedText The expected text that should match the element's text.
     * @return true if the element's text matches the expected text, false otherwise.
     */
    boolean assertElementTextPage(Page page, String element, String key, String expectedText);

    /**
     * Asserts that the text of a web element within a frame matches the expected text.
     *
     * @param frameLocator  The Playwright FrameLocator instance used to identify the frame.
     * @param element      The name or identifier of the element for which the text is being asserted.
     * @param key          The specific key or locator used to identify the element.
     * @param expectedText The expected text that should match the element's text.
     * @return true if the element's text matches the expected text, false otherwise.
     */
    boolean assertElementTextFrame(FrameLocator frameLocator, String element, String key, String expectedText);

    List<ElementHandle> getElementHandleList(Page page, String element, String key, FrameLocator frameLocator);
}