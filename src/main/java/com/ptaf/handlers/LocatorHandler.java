package com.ptaf.handlers;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

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
                return page.locator(locator);
            case "BUTTON": // Changed "Button" to "BUTTON"
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator));
            case "LINKTEXT": // Changed "LinkText" to "LINKTEXT"
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator));
            case "OPTION": // Changed "Option" to "OPTION"
                return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(locator).setExact(true));
            case "TEXTBOX": // Changed "Textbox" to "TEXTBOX"
                return page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(locator));
            case "CHECKBOX": // Changed "Checkbox" to "CHECKBOX"
                return page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(locator));
            case "RADIOBUTTON": // Changed "RadioButton" to "RADIOBUTTON"
                return page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(locator));
            case "DROPDOWN": // Changed "Dropdown" to "DROPDOWN"
                return page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName(locator));
            case "IMAGE": // Changed "Image" to "IMAGE"
                return page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(locator));
            case "HEADING": // Changed "Heading" to "HEADING"
                return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(locator));
            case "TAB": // Changed "Tab" to "TAB"
                return page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(locator));
            case "LIST": // Changed "List" to "LIST"
                return page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName(locator));
            case "LISTITEM": // Changed "ListItem" to "LISTITEM"
                return page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName(locator));
            case "TABLE": // Changed "Table" to "TABLE"
                return page.getByRole(AriaRole.TABLE, new Page.GetByRoleOptions().setName(locator));
            case "ROW": // Changed "Row" to "ROW"
                return page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(locator));
            case "CELL": // Changed "Cell" to "CELL"
                return page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(locator));
            case "BUTTONSUBMIT": // Changed "ButtonSubmit" to "BUTTONSUBMIT"
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator).setPressed(true));
            case "SLIDER": // Changed "Slider" to "SLIDER"
                return page.getByRole(AriaRole.SLIDER, new Page.GetByRoleOptions().setName(locator));
            case "SPINBUTTON": // Changed "SpinButton" to "SPINBUTTON"
                return page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName(locator));
            case "PROGRESSBAR": // Changed "ProgressBar" to "PROGRESSBAR"
                return page.getByRole(AriaRole.PROGRESSBAR, new Page.GetByRoleOptions().setName(locator));
            case "ALERT": // Changed "Alert" to "ALERT"
                return page.getByRole(AriaRole.ALERT, new Page.GetByRoleOptions().setName(locator));
            case "ALERTDIALOG": // Changed "AlertDialog" to "ALERTDIALOG"
                return page.getByRole(AriaRole.ALERTDIALOG, new Page.GetByRoleOptions().setName(locator));
            case "DIALOG": // Changed "Dialog" to "DIALOG"
                return page.getByRole(AriaRole.DIALOG, new Page.GetByRoleOptions().setName(locator));
            case "NAVIGATION": // Changed "Navigation" to "NAVIGATION"
                return page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName(locator));
            case "MENU": // Changed "Menu" to "MENU"
                return page.getByRole(AriaRole.MENU, new Page.GetByRoleOptions().setName(locator));
            case "MENUITEM": // Changed "MenuItem" to "MENUITEM"
                return page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(locator));
            case "MENUITEMCHECKBOX": // Changed "MenuItemCheckbox" to "MENUITEMCHECKBOX"
                return page.getByRole(AriaRole.MENUITEMCHECKBOX, new Page.GetByRoleOptions().setName(locator));
            case "MENUITEMRADIO": // Changed "MenuItemRadio" to "MENUITEMRADIO"
                return page.getByRole(AriaRole.MENUITEMRADIO, new Page.GetByRoleOptions().setName(locator));
            case "TREE": // Changed "Tree" to "TREE"
                return page.getByRole(AriaRole.TREE, new Page.GetByRoleOptions().setName(locator));
            case "TREEITEM": // Changed "TreeItem" to "TREEITEM"
                return page.getByRole(AriaRole.TREEITEM, new Page.GetByRoleOptions().setName(locator));
            case "GRID": // Changed "Grid" to "GRID"
                return page.getByRole(AriaRole.GRID, new Page.GetByRoleOptions().setName(locator));
            case "GRIDCELL": // Changed "GridCell" to "GRIDCELL"
                return page.getByRole(AriaRole.GRIDCELL, new Page.GetByRoleOptions().setName(locator));
            case "SEPARATOR": // Changed "Separator" to "SEPARATOR"
                return page.getByRole(AriaRole.SEPARATOR, new Page.GetByRoleOptions().setName(locator));
            case "SWITCH": // Changed "Switch" to "SWITCH"
                return page.getByRole(AriaRole.SWITCH, new Page.GetByRoleOptions().setName(locator));
            case "STATUS": // Changed "Status" to "STATUS"
                return page.getByRole(AriaRole.STATUS, new Page.GetByRoleOptions().setName(locator));
            case "BANNER": // Changed "Banner" to "BANNER"
                return page.getByRole(AriaRole.BANNER, new Page.GetByRoleOptions().setName(locator));
            case "FOOTER": // Changed "Footer" to "FOOTER"
                return page.getByRole(AriaRole.CONTENTINFO, new Page.GetByRoleOptions().setName(locator));
            case "MAIN": // Changed "Main" to "MAIN"
                return page.getByRole(AriaRole.MAIN, new Page.GetByRoleOptions().setName(locator));
            case "COMPLEMENTARY": // Changed "Complementary" to "COMPLEMENTARY"
                return page.getByRole(AriaRole.COMPLEMENTARY, new Page.GetByRoleOptions().setName(locator));
            case "REGION": // Changed "Region" to "REGION"
                return page.getByRole(AriaRole.REGION, new Page.GetByRoleOptions().setName(locator));
            case "ARTICLE": // Changed "Article" to "ARTICLE"
                return page.getByRole(AriaRole.ARTICLE, new Page.GetByRoleOptions().setName(locator));
            case "FORM": // Changed "Form" to "FORM"
                return page.getByRole(AriaRole.FORM, new Page.GetByRoleOptions().setName(locator));
            case "LOG": // Changed "Log" to "LOG"
                return page.getByRole(AriaRole.LOG, new Page.GetByRoleOptions().setName(locator));
            case "MARQUEE": // Changed "Marquee" to "MARQUEE"
                return page.getByRole(AriaRole.MARQUEE, new Page.GetByRoleOptions().setName(locator));
            case "TIMER": // Changed "Timer" to "TIMER"
                return page.getByRole(AriaRole.TIMER, new Page.GetByRoleOptions().setName(locator));
            case "TOOLTIP": // Changed "Tooltip" to "TOOLTIP"
                return page.getByRole(AriaRole.TOOLTIP, new Page.GetByRoleOptions().setName(locator));
            case "TOOLBAR": // Changed "Toolbar" to "TOOLBAR"
                return page.getByRole(AriaRole.TOOLBAR, new Page.GetByRoleOptions().setName(locator));
            case "PRESENTATION": // Changed "Presentation" to "PRESENTATION"
                return page.getByRole(AriaRole.PRESENTATION, new Page.GetByRoleOptions().setName(locator));
            case "FIGURE": // Changed "Figure" to "FIGURE"
                return page.getByRole(AriaRole.FIGURE, new Page.GetByRoleOptions().setName(locator));
            case "CONTENTINFO": // Changed "ContentInfo" to "CONTENTINFO"
                return page.getByRole(AriaRole.CONTENTINFO, new Page.GetByRoleOptions().setName(locator));
            case "TEXT": // Changed "Text" to "TEXT"
                return page.getByText(locator);
            case "ROLE": // Changed "Role" to "ROLE"
                return page.getByRole(AriaRole.valueOf(locator));
            case "ALTTEXT": // Changed "AltText" to "ALTTEXT"
                return page.getByAltText(locator);
            case "TITLE": // Changed "Title" to "TITLE"
                return page.getByTitle(locator);
            case "PLACEHOLDER": // Changed "Placeholder" to "PLACEHOLDER"
                return page.getByPlaceholder(locator);
            case "LABEL": // Changed "Label" to "LABEL"
                return page.getByLabel(locator);
            case "TESTID": // Changed "TestId" to "TESTID"
                return page.getByTestId(locator);
            case "ID": // Changed "Id" to "ID"
                return page.locator("#" + locator);
            case "NAME": // Changed "Name" to "NAME"
                return page.locator("[name='" + locator + "']");
            case "CLASS": // Changed "Class" to "CLASS"
                return page.locator("." + locator);
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType);
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
                return frame.locator(locator);
            case "BUTTON": // Changed "Button" to "BUTTON"
                return frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName(locator));
            case "LINKTEXT": // Changed "LinkText" to "LINKTEXT"
                return frame.getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName(locator)).first();
            case "OPTION": // Changed "Option" to "OPTION"
                return frame.getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(locator).setExact(true));
            case "TEXTBOX": // Changed "Textbox" to "TEXTBOX"
                return frame.getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName(locator));
            case "CHECKBOX": // Changed "Checkbox" to "CHECKBOX"
                return frame.getByRole(AriaRole.CHECKBOX, new FrameLocator.GetByRoleOptions().setName(locator));
            case "RADIOBUTTON": // Changed "RadioButton" to "RADIOBUTTON"
                return frame.getByRole(AriaRole.RADIO, new FrameLocator.GetByRoleOptions().setName(locator));
            case "DROPDOWN": // Changed "Dropdown" to "DROPDOWN"
                return frame.getByRole(AriaRole.COMBOBOX, new FrameLocator.GetByRoleOptions().setName(locator));
            case "IMAGE": // Changed "Image" to "IMAGE"
                return frame.getByRole(AriaRole.IMG, new FrameLocator.GetByRoleOptions().setName(locator));
            case "HEADING": // Changed "Heading" to "HEADING"
                return frame.getByRole(AriaRole.HEADING, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TAB": // Changed "Tab" to "TAB"
                return frame.getByRole(AriaRole.TAB, new FrameLocator.GetByRoleOptions().setName(locator));
            case "LIST": // Changed "List" to "LIST"
                return frame.getByRole(AriaRole.LIST, new FrameLocator.GetByRoleOptions().setName(locator));
            case "LISTITEM": // Changed "ListItem" to "LISTITEM"
                return frame.getByRole(AriaRole.LISTITEM, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TABLE": // Changed "Table" to "TABLE"
                return frame.getByRole(AriaRole.TABLE, new FrameLocator.GetByRoleOptions().setName(locator));
            case "ROW": // Changed "Row" to "ROW"
                return frame.getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName(locator));
            case "CELL": // Changed "Cell" to "CELL"
                return frame.getByRole(AriaRole.CELL, new FrameLocator.GetByRoleOptions().setName(locator));
            case "BUTTONSUBMIT": // Changed "ButtonSubmit" to "BUTTONSUBMIT"
                return frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName(locator).setPressed(true));
            case "SLIDER": // Changed "Slider" to "SLIDER"
                return frame.getByRole(AriaRole.SLIDER, new FrameLocator.GetByRoleOptions().setName(locator));
            case "SPINBUTTON": // Changed "SpinButton" to "SPINBUTTON"
                return frame.getByRole(AriaRole.SPINBUTTON, new FrameLocator.GetByRoleOptions().setName(locator));
            case "PROGRESSBAR": // Changed "ProgressBar" to "PROGRESSBAR"
                return frame.getByRole(AriaRole.PROGRESSBAR, new FrameLocator.GetByRoleOptions().setName(locator));
            case "ALERT": // Changed "Alert" to "ALERT"
                return frame.getByRole(AriaRole.ALERT, new FrameLocator.GetByRoleOptions().setName(locator));
            case "ALERTDIALOG": // Changed "AlertDialog" to "ALERTDIALOG"
                return frame.getByRole(AriaRole.ALERTDIALOG, new FrameLocator.GetByRoleOptions().setName(locator));
            case "DIALOG": // Changed "Dialog" to "DIALOG"
                return frame.getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName(locator));
            case "NAVIGATION": // Changed "Navigation" to "NAVIGATION"
                return frame.getByRole(AriaRole.NAVIGATION, new FrameLocator.GetByRoleOptions().setName(locator));
            case "MENU": // Changed "Menu" to "MENU"
                return frame.getByRole(AriaRole.MENU, new FrameLocator.GetByRoleOptions().setName(locator));
            case "MENUITEM": // Changed "MenuItem" to "MENUITEM"
                return frame.getByRole(AriaRole.MENUITEM, new FrameLocator.GetByRoleOptions().setName(locator));
            case "MENUITEMCHECKBOX": // Changed "MenuItemCheckbox" to "MENUITEMCHECKBOX"
                return frame.getByRole(AriaRole.MENUITEMCHECKBOX, new FrameLocator.GetByRoleOptions().setName(locator));
            case "MENUITEMRADIO": // Changed "MenuItemRadio" to "MENUITEMRADIO"
                return frame.getByRole(AriaRole.MENUITEMRADIO, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TREE": // Changed "Tree" to "TREE"
                return frame.getByRole(AriaRole.TREE, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TREEITEM": // Changed "TreeItem" to "TREEITEM"
                return frame.getByRole(AriaRole.TREEITEM, new FrameLocator.GetByRoleOptions().setName(locator));
            case "GRID": // Changed "Grid" to "GRID"
                return frame.getByRole(AriaRole.GRID, new FrameLocator.GetByRoleOptions().setName(locator));
            case "GRIDCELL": // Changed "GridCell" to "GRIDCELL"
                return frame.getByRole(AriaRole.GRIDCELL, new FrameLocator.GetByRoleOptions().setName(locator));
            case "SEPARATOR": // Changed "Separator" to "SEPARATOR"
                return frame.getByRole(AriaRole.SEPARATOR, new FrameLocator.GetByRoleOptions().setName(locator));
            case "SWITCH": // Changed "Switch" to "SWITCH"
                return frame.getByRole(AriaRole.SWITCH, new FrameLocator.GetByRoleOptions().setName(locator));
            case "STATUS": // Changed "Status" to "STATUS"
                return frame.getByRole(AriaRole.STATUS, new FrameLocator.GetByRoleOptions().setName(locator));
            case "BANNER": // Changed "Banner" to "BANNER"
                return frame.getByRole(AriaRole.BANNER, new FrameLocator.GetByRoleOptions().setName(locator));
            case "FOOTER": // Changed "Footer" to "FOOTER"
                return frame.getByRole(AriaRole.CONTENTINFO, new FrameLocator.GetByRoleOptions().setName(locator));
            case "MAIN": // Changed "Main" to "MAIN"
                return frame.getByRole(AriaRole.MAIN, new FrameLocator.GetByRoleOptions().setName(locator));
            case "COMPLEMENTARY": // Changed "Complementary" to "COMPLEMENTARY"
                return frame.getByRole(AriaRole.COMPLEMENTARY, new FrameLocator.GetByRoleOptions().setName(locator));
            case "REGION": // Changed "Region" to "REGION"
                return frame.getByRole(AriaRole.REGION, new FrameLocator.GetByRoleOptions().setName(locator));
            case "ARTICLE": // Changed "Article" to "ARTICLE"
                return frame.getByRole(AriaRole.ARTICLE, new FrameLocator.GetByRoleOptions().setName(locator));
            case "FORM": // Changed "Form" to "FORM"
                return frame.getByRole(AriaRole.FORM, new FrameLocator.GetByRoleOptions().setName(locator));
            case "LOG": // Changed "Log" to "LOG"
                return frame.getByRole(AriaRole.LOG, new FrameLocator.GetByRoleOptions().setName(locator));
            case "MARQUEE": // Changed "Marquee" to "MARQUEE"
                return frame.getByRole(AriaRole.MARQUEE, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TIMER": // Changed "Timer" to "TIMER"
                return frame.getByRole(AriaRole.TIMER, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TOOLTIP": // Changed "Tooltip" to "TOOLTIP"
                return frame.getByRole(AriaRole.TOOLTIP, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TOOLBAR": // Changed "Toolbar" to "TOOLBAR"
                return frame.getByRole(AriaRole.TOOLBAR, new FrameLocator.GetByRoleOptions().setName(locator));
            case "PRESENTATION": // Changed "Presentation" to "PRESENTATION"
                return frame.getByRole(AriaRole.PRESENTATION, new FrameLocator.GetByRoleOptions().setName(locator));
            case "FIGURE": // Changed "Figure" to "FIGURE"
                return frame.getByRole(AriaRole.FIGURE, new FrameLocator.GetByRoleOptions().setName(locator));
            case "CONTENTINFO": // Changed "ContentInfo" to "CONTENTINFO"
                return frame.getByRole(AriaRole.CONTENTINFO, new FrameLocator.GetByRoleOptions().setName(locator));
            case "TEXT": // Changed "Text" to "TEXT"
                return frame.getByText(locator);
            case "ROLE": // Changed "Role" to "ROLE"
                return frame.getByRole(AriaRole.valueOf(locator));
            case "ALTTEXT": // Changed "AltText" to "ALTTEXT"
                return frame.getByAltText(locator);
            case "TITLE": // Changed "Title" to "TITLE"
                return frame.getByTitle(locator);
            case "PLACEHOLDER": // Changed "Placeholder" to "PLACEHOLDER"
                return frame.getByPlaceholder(locator);
            case "LABEL": // Changed "Label" to "LABEL"
                return frame.getByLabel(locator);
            case "TESTID": // Changed "TestId" to "TESTID"
                return frame.getByTestId(locator);
            case "ID": // Changed "Id" to "ID"
                return frame.locator("#" + locator);
            case "NAME": // Changed "Name" to "NAME"
                return frame.locator("[name='" + locator + "']");
            case "CLASS": // Changed "Class" to "CLASS"
                return frame.locator("." + locator);
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType);
        }
    }
}
