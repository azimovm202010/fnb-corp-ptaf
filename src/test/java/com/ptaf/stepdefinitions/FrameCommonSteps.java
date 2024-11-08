package com.ptaf.stepdefinitions;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.ptaf.hooks.Hooks;
import com.ptaf.pages.FrameCommonMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class FrameCommonSteps {
    private static Page page = Hooks.getPage();
    private final FrameLocator frameLocator;
    private final FrameCommonMethods frameCommonMethods;

    // Add a setter for the Page object
    public static void setPage(Page page) {
        FrameCommonSteps.page = page;
    }

    // Constructor
    public FrameCommonSteps() {
        this.frameLocator = page.frameLocator("iframe[name='iframeApplicationContent']");
        this.frameCommonMethods = new FrameCommonMethods(frameLocator);
    }

    @Then("^we click on frame (.*?) locator (.*?)$")
    public void weClickActionOnFrame(String element, String locator) {
        frameCommonMethods.click(frameLocator, element, locator);
    }

    @Then("^we double click on frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnFrame(String element, String locator) {
        frameCommonMethods.dblclick(frameLocator, element, locator);
    }

    @Then("^we enter value on frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnFrame(String element, String locator, String value) {
        frameCommonMethods.fill(frameLocator, element, locator, value);
    }

    @Then("^we select on frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnFrame(String element, String locator, String value) {
        frameCommonMethods.select(page.frameLocator("iframe[name='iframeApplicationContent']"), element, locator, value);
    }

    @Then("^we check on frame (.*?) locator (.*?)$")
    public void weCheckActionOnFrame(String element, String locator) {
        frameCommonMethods.check(frameLocator, element, locator);
    }

    @Then("^we uncheck on frame (.*?) locator (.*?)$")
    public void weUncheckActionOnFrame(String element, String locator) {
        frameCommonMethods.check(frameLocator, element, locator);
    }

    @Then("^we hover on frame (.*?) locator (.*?)$")
    public void weHoverActionOnFrame(String element, String locator) {
        frameCommonMethods.hover(frameLocator, element, locator);
    }

    @Then("^we type on frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnFrame(String element, String locator, String value) {
        frameCommonMethods.type(frameLocator, element, locator, value);
    }

    @Then("^we scroll on frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnFrame(String element, String locator) {
        frameCommonMethods.scroll(frameLocator, element, locator);
    }

    @Then("^we clear value on frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnFrame(String element, String locator) {
        frameCommonMethods.clear(frameLocator, element, locator);
    }

    @Then("^we verify on frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(frameLocator, element, locator);
    }

    @Then("^we verify on frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(frameLocator, element, locator);
    }

    @Then("^we verify on frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(frameLocator, element, locator);
    }

    @Then("^we verify on frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(frameLocator, element, locator);
    }

    @Then("^we contain on frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(frameLocator, element, locator, value);
    }

    @Then("^we get text on frame (.*?) locator (.*?)$")
    public void weGetTextOnFrame(String element, String locator) {
        frameCommonMethods.gettext(frameLocator, element, locator);
    }

    @And("^we capture screenshot on frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(frameLocator, element, locator, filePath);
    }

    @And("^we press on frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(frameLocator, element, locator, value);
    }


}