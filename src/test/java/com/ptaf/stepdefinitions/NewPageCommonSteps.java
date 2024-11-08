package com.ptaf.stepdefinitions;

import com.microsoft.playwright.Page;
import com.ptaf.hooks.Hooks;
import com.ptaf.pages.PageCommonMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class NewPageCommonSteps {
    private final Page page = Hooks.getPage();
    private final Page new_page = page.waitForPopup(() -> page.getByLabel("Please complete the previous").click());
    private final PageCommonMethods pageCommonMethods = new PageCommonMethods(new_page);

    @Then("^we click on new page (.*?) locator (.*?)$")
    public void weClickActionOnPage(String element, String locator) {
        pageCommonMethods.click(new_page, element, locator);
    }

    @Then("^we double click on new page (.*?) locator (.*?)$")
    public void weDoubleClickActionOnPage(String element, String locator) {
        pageCommonMethods.dblclick(new_page, element, locator);
    }

    @Then("^we enter value on new page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnPage(String element, String locator, String value) {
        pageCommonMethods.fill(new_page, element, locator, value);
    }

    @Then("^we select on new page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnPage(String element, String locator, String value) {
        pageCommonMethods.select(new_page, element, locator, value);
    }

    @Then("^we check on new page (.*?) locator (.*?)$")
    public void weCheckActionOnPage(String element, String locator) {
        pageCommonMethods.check(new_page, element, locator);
    }

    @Then("^we uncheck on new page (.*?) locator (.*?)$")
    public void weUncheckActionOnPage(String element, String locator) {
        pageCommonMethods.check(new_page, element, locator);
    }

    @Then("^we hover on new page (.*?) locator (.*?)$")
    public void weHoverActionOnPage(String element, String locator) {
        pageCommonMethods.hover(new_page, element, locator);
    }

    @Then("^we type on new page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnPage(String element, String locator, String value) {
        pageCommonMethods.type(new_page, element, locator, value);
    }

    @Then("^we scroll on new page (.*?) locator (.*?)$")
    public void weScrollToLocatorOnPage(String element, String locator) {
        pageCommonMethods.scroll(new_page, element, locator);
    }

    @Then("^we clear value on new page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnPage(String element, String locator) {
        pageCommonMethods.clear(new_page, element, locator);
    }

    @Then("^we verify on new page (.*?) of locator (.*?) is visible$")
    public void weVerifyOnPageLocatorIsVisible(String element, String locator) {
        pageCommonMethods.isvisible(new_page, element, locator);
    }

    @Then("^we verify on new page (.*?) of locator (.*?) is checked$")
    public void weVerifyOnPageLocatorIsChecked(String element, String locator) {
        pageCommonMethods.ischecked(new_page, element, locator);
    }

    @Then("^we verify on new page (.*?) of locator (.*?) is enabled")
    public void weVerifyOnPageLocatorIsEnabled(String element, String locator) {
        pageCommonMethods.isenabled(new_page, element, locator);
    }

    @Then("^we verify on new page (.*?) of locator (.*?) is existed")
    public void weVerifyOnPageLocatorIsExisted(String element, String locator) {
        pageCommonMethods.exists(new_page, element, locator);
    }

    @Then("^we contain on new page (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnPageLocatorValue(String element, String locator, String value) {
        pageCommonMethods.contain(new_page, element, locator, value);
    }

    @Then("^we get text on new page(.*?) locator (.*?)$")
    public void weGetTextOnPage(String element, String locator) {
        pageCommonMethods.gettext(new_page, element, locator);
    }

    @And("^we capture screenshot on new page (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnPage(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        pageCommonMethods.screenshot(new_page, element, locator, filePath);
    }

    @And("^we press on new page (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnPageKey(String element, String locator, String value) {
        pageCommonMethods.press(new_page, element, locator, value);
    }
}