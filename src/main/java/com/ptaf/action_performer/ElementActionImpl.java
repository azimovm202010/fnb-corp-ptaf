package com.ptaf.action_performer;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ptaf.handlers.LocatorHandler;
import com.ptaf.helpers.ElementLocatorHelper;
import com.ptaf.interfaces.ElementAction;
import com.ptaf.page_helper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementActionImpl extends PageHelper implements ElementAction {
    private static final Logger logger = LoggerFactory.getLogger(ElementActionImpl.class);
    private final ActionPerformer actionPerformer = new ActionPerformer();
    private final ElementLocatorHelper elementLocatorHelper = new ElementLocatorHelper();
    private final LocatorHandler locatorHandler = new LocatorHandler();

    public ElementActionImpl(Page page) {
        super(page);
    }

    @Override
    public boolean performActionPage(Page page, String action, String element, String key, String value) {
        return performAction(page, action, element, key, value, null);
    }

    @Override
    public boolean performActionFrame(FrameLocator frameLocator, String action, String element, String key, String value) {
        return performAction(null, action, element, key, value, frameLocator);
    }

    private boolean performAction(Page page, String action, String element, String key, String value, FrameLocator frameLocator) {
        try {
            Locator targetLocator = (page != null)
                    ? getLocatorBasedOnPage(page, element, key)
                    : getLocatorBasedOnFrame(frameLocator, element, key);
            actionPerformer.waitForLocator(targetLocator);
            actionPerformer.performAction(action, targetLocator, value);
            return true;
        } catch (Exception e) {
            logger.error("Error while performing action '{}' on element '{}'", action, element, e);
            return false;
        }
    }

    @Override
    public boolean assertElementTextPage(Page page, String element, String key, String expectedText) {
        return assertElementText(page, element, key, expectedText, null);
    }

    @Override
    public boolean assertElementTextFrame(FrameLocator frameLocator, String element, String key, String expectedText) {
        return assertElementText(null, element, key, expectedText, frameLocator);
    }

    private boolean assertElementText(Page page, String element, String key, String expectedText, FrameLocator frameLocator) {
        try {
            Locator targetLocator = (page != null)
                    ? getLocatorBasedOnPage(page, element, key)
                    : getLocatorBasedOnFrame(frameLocator, element, key);
            String actualText = targetLocator.textContent();
            boolean isTextMatching = expectedText.equals(actualText);

            // Log the actual and expected text
            logger.info("Asserting text on element '{}': expected '{}', actual '{}'", element, expectedText, actualText);

            if (!isTextMatching) {
                logger.error("Text mismatch: expected '{}' but found '{}'", expectedText, actualText);
            }
            return isTextMatching;
        } catch (Exception e) {
            logger.error("Error while asserting text on element '{}'", element, e);
            return false;
        }
    }

    private Locator getLocatorBasedOnPage(Page page, String element, String key) {
        return getLocator(element, key, page, null);
    }

    private Locator getLocatorBasedOnFrame(FrameLocator frameLocator, String element, String key) {
        return getLocator(element, key, null, frameLocator);
    }

    private Locator getLocator(String element, String key, Page page, FrameLocator frameLocator) {
        String locatorValue = elementLocatorHelper.getElement(element, key);
        String locatorType = elementLocatorHelper.getLocatorType(locatorValue);
        String locator = elementLocatorHelper.getLocator(locatorValue);
        try {
            return (page != null)
                    ? locatorHandler.getLocatorForType(locatorType, page, locator)
                    : locatorHandler.getLocatorForType(locatorType, frameLocator, locator);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown context: " + (page != null ? page : frameLocator), e);
        }
    }
}
