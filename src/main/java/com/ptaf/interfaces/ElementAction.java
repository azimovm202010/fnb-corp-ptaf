package com.ptaf.interfaces;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public interface ElementAction {

    boolean performActionPage(Page page, String action, String element, String key, String value);

    boolean performActionFrame(FrameLocator frameLocator, String action, String element, String key, String value);

    boolean assertElementTextPage(Page page, String element, String key, String expectedText);


    boolean assertElementTextFrame(FrameLocator frameLocator, String element, String key, String expectedText);
}
