package com.ptaf.handlers;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * LocatorHandler provides methods for retrieving Locators based on various locator types
 * from a given Playwright Page or FrameLocator. It serves as a utility class for abstraction
 * around how different types of elements are located within the UI.
 *
 * Usage:
 * - Use the getLocatorForType method to obtain a Locator based on its type and context (Page or Frame).
 */
public class LocatorHandler {

    /**
     * Gets the locator for a specific type from a Page.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS, BUTTON, etc.).
     * @param page        The Page object where the locator will be searched.
     * @param locator     The locator string or value.
     * @return The located element as a Locator object.
     */
    public Locator getLocatorForType(String locatorType, Page page, String locator) {
        switch (locatorType.toUpperCase()) {
            case "CSS":
            case "TAG":
            case "XPATH":
                return page.locator(locator); // Return Locator using CSS, TAG, or XPATH
            case "BUTTON":
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator)); // Get button by name
            case "LINKTEXT":
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator)); // Get link by visible text
            case "OPTION":
                return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(locator).setExact(true)); // Select option with exact name matching
            case "TEXTBOX":
                return page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(locator)); // Get textbox by name
            case "CHECKBOX":
                return page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(locator)); // Get checkbox by name
            case "RADIOBUTTON":
                return page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(locator)); // Get radio button by name
            case "DROPDOWN":
                return page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName(locator)); // Get dropdown (combo box) by name
            case "IMAGE":
                return page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(locator)); // Get image by name
            case "HEADING":
                return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(locator)); // Get heading by name
            case "TAB":
                return page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(locator)); // Get tab by name
            case "LIST":
                return page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName(locator)); // Get list by name
            case "LISTITEM":
                return page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName(locator)); // Get list item by name
            case "TABLE":
                return page.getByRole(AriaRole.TABLE, new Page.GetByRoleOptions().setName(locator)); // Get table by name
            case "ROW":
                return page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(locator)); // Get row by name
            case "CELL":
                return page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(locator)); // Get cell by name
            case "BUTTONSUBMIT":
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator).setPressed(true)); // Get pressed submit button
            case "SLIDER":
                return page.getByRole(AriaRole.SLIDER, new Page.GetByRoleOptions().setName(locator)); // Get slider by name
            case "SPINBUTTON":
                return page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName(locator)); // Get spin button by name
            case "PROGRESSBAR":
                return page.getByRole(AriaRole.PROGRESSBAR, new Page.GetByRoleOptions().setName(locator)); // Get progress bar by name
            case "ALERT":
                return page.getByRole(AriaRole.ALERT, new Page.GetByRoleOptions().setName(locator)); // Get alert by name
            case "ALERTDIALOG":
                return page.getByRole(AriaRole.ALERTDIALOG, new Page.GetByRoleOptions().setName(locator)); // Get alert dialog by name
            case "DIALOG":
                return page.getByRole(AriaRole.DIALOG, new Page.GetByRoleOptions().setName(locator)); // Get dialog by name
            case "NAVIGATION":
                return page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName(locator)); // Get navigation by name
            case "MENU":
                return page.getByRole(AriaRole.MENU, new Page.GetByRoleOptions().setName(locator)); // Get menu by name
            case "MENUITEM":
                return page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(locator)); // Get menu item by name
            case "MENUITEMCHECKBOX":
                return page.getByRole(AriaRole.MENUITEMCHECKBOX, new Page.GetByRoleOptions().setName(locator)); // Get menu item with checkbox by name
            case "MENUITEMRADIO":
                return page.getByRole(AriaRole.MENUITEMRADIO, new Page.GetByRoleOptions().setName(locator)); // Get menu item with radio button by name
            case "TREE":
                return page.getByRole(AriaRole.TREE, new Page.GetByRoleOptions().setName(locator)); // Get tree structure by name
            case "TREEITEM":
                return page.getByRole(AriaRole.TREEITEM, new Page.GetByRoleOptions().setName(locator)); // Get item in tree by name
            case "GRID":
                return page.getByRole(AriaRole.GRID, new Page.GetByRoleOptions().setName(locator)); // Get grid by name
            case "GRIDCELL":
                return page.getByRole(AriaRole.GRIDCELL, new Page.GetByRoleOptions().setName(locator)); // Get cell in grid by name
            case "SEPARATOR":
                return page.getByRole(AriaRole.SEPARATOR, new Page.GetByRoleOptions().setName(locator)); // Get separator by name
            case "SWITCH":
                return page.getByRole(AriaRole.SWITCH, new Page.GetByRoleOptions().setName(locator)); // Get switch by name
            case "STATUS":
                return page.getByRole(AriaRole.STATUS, new Page.GetByRoleOptions().setName(locator)); // Get status by name
            case "BANNER":
                return page.getByRole(AriaRole.BANNER, new Page.GetByRoleOptions().setName(locator)); // Get banner by name
            case "FOOTER":
                return page.getByRole(AriaRole.CONTENTINFO, new Page.GetByRoleOptions().setName(locator)); // Get footer content by name
            case "MAIN":
                return page.getByRole(AriaRole.MAIN, new Page.GetByRoleOptions().setName(locator)); // Get main content area by name
            case "COMPLEMENTARY":
                return page.getByRole(AriaRole.COMPLEMENTARY, new Page.GetByRoleOptions().setName(locator)); // Get complementary content
            case "REGION":
                return page.getByRole(AriaRole.REGION, new Page.GetByRoleOptions().setName(locator)); // Get region by name
            case "ARTICLE":
                return page.getByRole(AriaRole.ARTICLE, new Page.GetByRoleOptions().setName(locator)); // Get article section by name
            case "FORM":
                return page.getByRole(AriaRole.FORM, new Page.GetByRoleOptions().setName(locator)); // Get form by name
            case "LOG":
                return page.getByRole(AriaRole.LOG, new Page.GetByRoleOptions().setName(locator)); // Get log section by name
            case "MARQUEE":
                return page.getByRole(AriaRole.MARQUEE, new Page.GetByRoleOptions().setName(locator)); // Get marquee element by name
            case "TIMER":
                return page.getByRole(AriaRole.TIMER, new Page.GetByRoleOptions().setName(locator)); // Get timer by name
            case "TOOLTIP":
                return page.getByRole(AriaRole.TOOLTIP, new Page.GetByRoleOptions().setName(locator)); // Get tooltip by name
            case "TOOLBAR":
                return page.getByRole(AriaRole.TOOLBAR, new Page.GetByRoleOptions().setName(locator)); // Get toolbar by name
            case "PRESENTATION":
                return page.getByRole(AriaRole.PRESENTATION, new Page.GetByRoleOptions().setName(locator)); // Get presentation content
            case "FIGURE":
                return page.getByRole(AriaRole.FIGURE, new Page.GetByRoleOptions().setName(locator)); // Get figure content
            case "CONTENTINFO":
                return page.getByRole(AriaRole.CONTENTINFO, new Page.GetByRoleOptions().setName(locator)); // Get content information by name
            case "TEXT":
                return page.getByText(locator); // Get element by visible text
            case "ROLE":
                return page.getByRole(AriaRole.valueOf(locator)); // Get element by ARIA role
            case "ALTTEXT":
                return page.getByAltText(locator); // Get element by alt text
            case "TITLE":
                return page.getByTitle(locator); // Get element by title attribute
            case "PLACEHOLDER":
                return page.getByPlaceholder(locator); // Get element by placeholder attribute
            case "LABEL":
                return page.getByLabel(locator); // Get element by label
            case "TESTID":
                return page.getByTestId(locator); // Get element by data-testid attribute
            case "ID":
                return page.locator("#" + locator); // Get element by ID
            case "NAME":
                return page.locator("[name='" + locator + "']"); // Get element by NAME attribute
            case "CLASS":
                return page.locator("." + locator); // Get element by CLASS
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType); // Handle unknown locator types
        }
    }

    /**
     * Gets the locator for a specific type from a FrameLocator.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS, BUTTON, etc.).
     * @param frame       The FrameLocator object where the locator will be searched.
     * @param locator     The locator string or value.
     * @return The located element as a Locator object.
     */
    public Locator getLocatorForType(String locatorType, FrameLocator frame, String locator) {
        switch (locatorType.toUpperCase()) {
            case "XPATH":
            case "CSS":
            case "TAG":
                return frame.locator(locator); // Return Locator using CSS, TAG, or XPATH
            case "BUTTON":
                return frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName(locator)); // Get button by name in frame
            case "LINKTEXT":
                return frame.getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName(locator)).first(); // Get link by visible text in frame
            case "OPTION":
                return frame.getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(locator).setExact(true)); // Select option with exact name match
            case "TEXTBOX":
                return frame.getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName(locator)); // Get textbox by name in frame
            case "CHECKBOX":
                return frame.getByRole(AriaRole.CHECKBOX, new FrameLocator.GetByRoleOptions().setName(locator)); // Get checkbox by name in frame
            case "RADIOBUTTON":
                return frame.getByRole(AriaRole.RADIO, new FrameLocator.GetByRoleOptions().setName(locator)); // Get radio button by name in frame
            case "DROPDOWN":
                return frame.getByRole(AriaRole.COMBOBOX, new FrameLocator.GetByRoleOptions().setName(locator)); // Get dropdown (combo box) by name in frame
            case "IMAGE":
                return frame.getByRole(AriaRole.IMG, new FrameLocator.GetByRoleOptions().setName(locator)); // Get image by name in frame
            case "HEADING":
                return frame.getByRole(AriaRole.HEADING, new FrameLocator.GetByRoleOptions().setName(locator)); // Get heading by name in frame
            case "TAB":
                return frame.getByRole(AriaRole.TAB, new FrameLocator.GetByRoleOptions().setName(locator)); // Get tab by name in frame
            case "LIST":
                return frame.getByRole(AriaRole.LIST, new FrameLocator.GetByRoleOptions().setName(locator)); // Get list by name in frame
            case "LISTITEM":
                return frame.getByRole(AriaRole.LISTITEM, new FrameLocator.GetByRoleOptions().setName(locator)); // Get list item by name in frame
            case "TABLE":
                return frame.getByRole(AriaRole.TABLE, new FrameLocator.GetByRoleOptions().setName(locator)); // Get table by name in frame
            case "ROW":
                return frame.getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName(locator)); // Get row by name in frame
            case "CELL":
                return frame.getByRole(AriaRole.CELL, new FrameLocator.GetByRoleOptions().setName(locator)); // Get cell by name in frame
            case "BUTTONSUBMIT":
                return frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName(locator).setPressed(true)); // Get pressed submit button in frame
            case "SLIDER":
                return frame.getByRole(AriaRole.SLIDER, new FrameLocator.GetByRoleOptions().setName(locator)); // Get slider by name in frame
            case "SPINBUTTON":
                return frame.getByRole(AriaRole.SPINBUTTON, new FrameLocator.GetByRoleOptions().setName(locator)); // Get spin button by name in frame
            case "PROGRESSBAR":
                return frame.getByRole(AriaRole.PROGRESSBAR, new FrameLocator.GetByRoleOptions().setName(locator)); // Get progress bar by name in frame
            case "ALERT":
                return frame.getByRole(AriaRole.ALERT, new FrameLocator.GetByRoleOptions().setName(locator)); // Get alert by name in frame
            case "ALERTDIALOG":
                return frame.getByRole(AriaRole.ALERTDIALOG, new FrameLocator.GetByRoleOptions().setName(locator)); // Get alert dialog by name in frame
            case "DIALOG":
                return frame.getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName(locator)); // Get dialog by name in frame
            case "NAVIGATION":
                return frame.getByRole(AriaRole.NAVIGATION, new FrameLocator.GetByRoleOptions().setName(locator)); // Get navigation by name in frame
            case "MENU":
                return frame.getByRole(AriaRole.MENU, new FrameLocator.GetByRoleOptions().setName(locator)); // Get menu by name in frame
            case "MENUITEM":
                return frame.getByRole(AriaRole.MENUITEM, new FrameLocator.GetByRoleOptions().setName(locator)); // Get menu item by name in frame
            case "MENUITEMCHECKBOX":
                return frame.getByRole(AriaRole.MENUITEMCHECKBOX, new FrameLocator.GetByRoleOptions().setName(locator)); // Get menu item with checkbox by name in frame
            case "MENUITEMRADIO":
                return frame.getByRole(AriaRole.MENUITEMRADIO, new FrameLocator.GetByRoleOptions().setName(locator)); // Get menu item with radio button by name in frame
            case "TREE":
                return frame.getByRole(AriaRole.TREE, new FrameLocator.GetByRoleOptions().setName(locator)); // Get tree structure by name in frame
            case "TREEITEM":
                return frame.getByRole(AriaRole.TREEITEM, new FrameLocator.GetByRoleOptions().setName(locator)); // Get item in tree by name in frame
            case "GRID":
                return frame.getByRole(AriaRole.GRID, new FrameLocator.GetByRoleOptions().setName(locator)); // Get grid by name in frame
            case "GRIDCELL":
                return frame.getByRole(AriaRole.GRIDCELL, new FrameLocator.GetByRoleOptions().setName(locator)); // Get cell in grid by name in frame
            case "SEPARATOR":
                return frame.getByRole(AriaRole.SEPARATOR, new FrameLocator.GetByRoleOptions().setName(locator)); // Get separator by name in frame
            case "SWITCH":
                return frame.getByRole(AriaRole.SWITCH, new FrameLocator.GetByRoleOptions().setName(locator)); // Get switch by name in frame
            case "STATUS":
                return frame.getByRole(AriaRole.STATUS, new FrameLocator.GetByRoleOptions().setName(locator)); // Get status by name in frame
            case "BANNER":
                return frame.getByRole(AriaRole.BANNER, new FrameLocator.GetByRoleOptions().setName(locator)); // Get banner by name in frame
            case "FOOTER":
                return frame.getByRole(AriaRole.CONTENTINFO, new FrameLocator.GetByRoleOptions().setName(locator)); // Get footer content by name in frame
            case "MAIN":
                return frame.getByRole(AriaRole.MAIN, new FrameLocator.GetByRoleOptions().setName(locator)); // Get main content area by name in frame
            case "COMPLEMENTARY":
                return frame.getByRole(AriaRole.COMPLEMENTARY, new FrameLocator.GetByRoleOptions().setName(locator)); // Get complementary content in frame
            case "REGION":
                return frame.getByRole(AriaRole.REGION, new FrameLocator.GetByRoleOptions().setName(locator)); // Get region by name in frame
            case "ARTICLE":
                return frame.getByRole(AriaRole.ARTICLE, new FrameLocator.GetByRoleOptions().setName(locator)); // Get article section by name in frame
            case "FORM":
                return frame.getByRole(AriaRole.FORM, new FrameLocator.GetByRoleOptions().setName(locator)); // Get form by name in frame
            case "LOG":
                return frame.getByRole(AriaRole.LOG, new FrameLocator.GetByRoleOptions().setName(locator)); // Get log section by name in frame
            case "MARQUEE":
                return frame.getByRole(AriaRole.MARQUEE, new FrameLocator.GetByRoleOptions().setName(locator)); // Get marquee element by name in frame
            case "TIMER":
                return frame.getByRole(AriaRole.TIMER, new FrameLocator.GetByRoleOptions().setName(locator)); // Get timer by name in frame
            case "TOOLTIP":
                return frame.getByRole(AriaRole.TOOLTIP, new FrameLocator.GetByRoleOptions().setName(locator)); // Get tooltip by name in frame
            case "TOOLBAR":
                return frame.getByRole(AriaRole.TOOLBAR, new FrameLocator.GetByRoleOptions().setName(locator)); // Get toolbar by name in frame
            case "PRESENTATION":
                return frame.getByRole(AriaRole.PRESENTATION, new FrameLocator.GetByRoleOptions().setName(locator)); // Get presentation content in frame
            case "FIGURE":
                return frame.getByRole(AriaRole.FIGURE, new FrameLocator.GetByRoleOptions().setName(locator)); // Get figure content in frame
            case "CONTENTINFO":
                return frame.getByRole(AriaRole.CONTENTINFO, new FrameLocator.GetByRoleOptions().setName(locator)); // Get content information by name in frame
            case "TEXT":
                return frame.getByText(locator); // Get element by visible text in frame
            case "ROLE":
                return frame.getByRole(AriaRole.valueOf(locator)); // Get element by ARIA role in frame
            case "ALTTEXT":
                return frame.getByAltText(locator); // Get element by alt text in frame
            case "TITLE":
                return frame.getByTitle(locator); // Get element by title attribute in frame
            case "PLACEHOLDER":
                return frame.getByPlaceholder(locator); // Get element by placeholder attribute in frame
            case "LABEL":
                return frame.getByLabel(locator); // Get element by label in frame
            case "TESTID":
                return frame.getByTestId(locator); // Get element by data-testid attribute in frame
            case "ID":
                return frame.locator("#" + locator); // Get element by ID in frame
            case "NAME":
                return frame.locator("[name='" + locator + "']"); // Get element by NAME attribute in frame
            case "CLASS":
                return frame.locator("." + locator); // Get element by CLASS in frame
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType); // Handle unknown locator types in frame
        }
    }
}