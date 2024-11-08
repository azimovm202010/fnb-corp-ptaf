package com.ptaf.stepdefinitions;

import com.microsoft.playwright.Page;
import com.ptaf.hooks.Hooks;
import com.ptaf.pages.PageCommonMethods;
import com.ptaf.utils.ConfigurationProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageCommonSteps {
    private final Page page = Hooks.getPage();
    private final PageCommonMethods pageCommonMethods = new PageCommonMethods(page);
    private static final Logger logger = LoggerFactory.getLogger(PageCommonSteps.class);

    @Given("^we navigate to (.*?) url$")
    public void weNavigateToURL(String URL) {
        page.navigate(ConfigurationProperties.getBaseUrl(URL));
//        page.setViewportSize(1920, 1080);
    }

    @Then("^we click on page (.*?) locator (.*?)$")
    public void weClickActionOnPage(String element, String locator) {
        pageCommonMethods.click(page, element, locator);
    }

    @Then("^we double click on page (.*?) locator (.*?)$")
    public void weDoubleClickActionOnPage(String element, String locator) {
        pageCommonMethods.dblclick(page, element, locator);
    }

    @Then("^we enter value on page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnPage(String element, String locator, String value) {
        pageCommonMethods.fill(page, element, locator, value);
    }

    @Then("^we select on page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnPage(String element, String locator, String value) {
        pageCommonMethods.select(page, element, locator, value);
    }

    @Then("^we check on page (.*?) locator (.*?)$")
    public void weCheckActionOnPage(String element, String locator) {
        pageCommonMethods.check(page, element, locator);
    }

    @Then("^we uncheck on page (.*?) locator (.*?)$")
    public void weUncheckActionOnPage(String element, String locator) {
        pageCommonMethods.check(page, element, locator);
    }

    @Then("^we hover on page (.*?) locator (.*?)$")
    public void weHoverActionOnPage(String element, String locator) {
        pageCommonMethods.hover(page, element, locator);
    }

    @Then("^we type on page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnPage(String element, String locator, String value) {
        pageCommonMethods.type(page, element, locator, value);
    }

    @Then("^we scroll on page (.*?) locator (.*?)$")
    public void weScrollToLocatorOnPage(String element, String locator) {
        pageCommonMethods.scroll(page, element, locator);
    }

    @Then("^we clear value on page (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnPage(String element, String locator) {
        pageCommonMethods.clear(page, element, locator);
    }

    @Then("^we verify on page (.*?) of locator (.*?) is visible$")
    public void weVerifyOnPageLocatorIsVisible(String element, String locator) {
        pageCommonMethods.isvisible(page, element, locator);
    }

    @Then("^we verify on page (.*?) of locator (.*?) is checked$")
    public void weVerifyOnPageLocatorIsChecked(String element, String locator) {
        pageCommonMethods.ischecked(page, element, locator);
    }

    @Then("^we verify on page (.*?) of locator (.*?) is enabled")
    public void weVerifyOnPageLocatorIsEnabled(String element, String locator) {
        pageCommonMethods.isenabled(page, element, locator);
    }

    @Then("^we verify on page (.*?) of locator (.*?) is existed")
    public void weVerifyOnPageLocatorIsExisted(String element, String locator) {
        pageCommonMethods.exists(page, element, locator);
    }

    @Then("^we contain on page (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnPageLocatorValue(String element, String locator, String value) {
        pageCommonMethods.contain(page, element, locator, value);
    }

    @Then("^we get text on page (.*?) locator (.*?)$")
    public void weGetTextOnPage(String element, String locator) {
        pageCommonMethods.gettext(page, element, locator);
    }

    @And("^we capture screenshot on page (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnPage(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        pageCommonMethods.screenshot(page, element, locator, filePath);
    }

    @And("^we press on page (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnPageKey(String element, String locator, String value) {
        pageCommonMethods.press(page, element, locator, value);
    }

    @Given("^get title of page$")
    public void getTitleOfPage() {
        String title = page.title();
        logger.info("Page title: {}", title);
    }

    @And("^we wait for some time$")
    public void weWaitForSomeTime() throws InterruptedException {
        Thread.sleep(5000);
    }
}