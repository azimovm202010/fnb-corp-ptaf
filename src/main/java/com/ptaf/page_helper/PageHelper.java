package com.ptaf.page_helper;

import com.microsoft.playwright.Page;
import com.ptaf.handlers.LocatorHandler;

public class PageHelper {
    public Page page;
    private final LocatorHandler locatorHandler;

    public PageHelper(Page page) {
        this.page = page;
        this.locatorHandler = new LocatorHandler();
    }
}
