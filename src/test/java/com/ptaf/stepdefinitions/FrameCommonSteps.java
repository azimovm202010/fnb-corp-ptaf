package com.ptaf.stepdefinitions;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.ptaf.hooks.Hooks;
import com.ptaf.pages.FrameCommonMethods;
import com.ptaf.utils.ConfigurationProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FrameCommonSteps {
    private final Page page;
    private static Page iframePage;
    private final String iFrame = "iframe[name='iframeApplicationContent']";
    private final String iFrame_2 = "iframe[name='iframeTellerContent']";
    private final String iFrame_3 = "iframe[name='SearchResultsiFrame']";
    private final String warning_box = null;
    private final String header = null;
    private final String pop_up = "iframe[frameborder='0px']";
    private final String second_pop_up = "(//iframe[@frameborder='0px'])[2]";

    private final FrameCommonMethods frameCommonMethods;

    public FrameCommonSteps() {
        this.page = Hooks.getPage(); // Retrieve the Page instance from Hooks
        this.frameCommonMethods = new FrameCommonMethods(iframePage); // Initialize with the Page instance
    }

    public void switchToIframe() {
        iframePage = page.waitForPopup(() -> {
            page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continue")).click();
        });
    }

    @Given("^we navigate to (.*?) url$")
    public void weNavigateToURL(String URL) {
        page.navigate(ConfigurationProperties.getBaseUrl(URL));
//        page.setViewportSize(1920, 1080);
        switchToIframe();
    }

    @Then("^we click on main frame (.*?) locator (.*?)$")
    public void weClickActionOnMainFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we double click on main frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnMainFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we enter value on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnMainFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, iFrame, null, null, element, locator, value);
    }

    @Then("^we select on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnMainFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, iFrame, null, null, element, locator, value);
    }

    @Then("^we check on main frame (.*?) locator (.*?)$")
    public void weCheckActionOnMainFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we uncheck on main frame (.*?) locator (.*?)$")
    public void weUncheckActionOnMainFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we hover on main frame (.*?) locator (.*?)$")
    public void weHoverActionOnMainFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we type on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnMainFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, iFrame, null, null, element, locator, value);
    }

    @Then("^we scroll on main frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnMainFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we clear value on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnMainFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we verify on main frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnMainFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we verify on main frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnMainFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we verify on main frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnMainFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we verify on main frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnMainFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we contain on main frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnMainFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, iFrame,  element, locator, value);
    }

    @Then("^we get text on main frame(.*?) locator (.*?)$")
    public void weGetTextOnMainFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, null, null, element, locator);
    }

    @Then("^we has value on main frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnMainFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, iFrame, null, null, element, locator, value);
    }

    @Then("^we get list of elements on main frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnMainFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, null, null, element, locator);
    }

    @When("we click radio on main frame (.*?) list locator (.*?)$")
    public void clickRadioOnMainFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, iFrame,  element, locator);
    }

    @And("^we capture screenshot on main frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnMainFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, iFrame, null, null, element, locator, filePath);
    }

    @And("^we press on main frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnMainFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, iFrame, null, null, element, locator, value);
    }

//      ________________________________________________________________________________________________________________

    @Then("^we click on second frame (.*?) locator (.*?)$")
    public void weClickActionOnSecondFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we double click on second frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnSecondFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we enter value on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnSecondFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    @Then("^we select on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnSecondFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    @Then("^we check on second frame (.*?) locator (.*?)$")
    public void weCheckActionOnSecondFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we uncheck on second frame (.*?) locator (.*?)$")
    public void weUncheckActionOnSecondFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we hover on second frame (.*?) locator (.*?)$")
    public void weHoverActionOnSecondFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we type on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnSecondFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    @Then("^we scroll on second frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnSecondFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we clear value on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnSecondFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we verify on second frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnSecondFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we verify on second frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnSecondFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we verify on second frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnSecondFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we verify on second frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnSecondFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we contain on second frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnSecondFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, iFrame,  element, locator, value);
    }

    @Then("^we get text on second frame(.*?) locator (.*?)$")
    public void weGetTextOnSecondFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @Then("^we has value on second frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnSecondFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    @Then("^we get list of elements on second frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnSecondFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    @When("we click radio on second frame (.*?) list locator (.*?)$")
    public void clickRadioOnSecondFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, iFrame,  element, locator);
    }

    @And("^we capture screenshot on second frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnSecondFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, iFrame, iFrame_2, null, element, locator, filePath);
    }

    @And("^we press on second frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnSecondFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }
    
//   ___________________________________________________________________________________________________________________

    @Then("^we click on warning-box frame (.*?) locator (.*?)$")
    public void weClickActionOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, null, null, null, element, locator);
    }

    @Then("^we double click on warning-box frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, null, null, null, element, locator);
    }

    @Then("^we enter value on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnWarning_BoxFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we select on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnWarning_BoxFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we check on warning-box frame (.*?) locator (.*?)$")
    public void weCheckActionOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    @Then("^we uncheck on warning-box frame (.*?) locator (.*?)$")
    public void weUncheckActionOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    @Then("^we hover on warning-box frame (.*?) locator (.*?)$")
    public void weHoverActionOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, null, null, null, element, locator);
    }

    @Then("^we type on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnWarning_BoxFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we scroll on warning-box frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, null, null, null, element, locator);
    }

    @Then("^we clear value on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnWarning_BoxFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnWarning_BoxFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnWarning_BoxFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnWarning_BoxFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, null, null, null, element, locator);
    }

    @Then("^we contain on warning-box frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnWarning_BoxFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, null,  element, locator, value);
    }

    @Then("^we get text on warning-box frame(.*?) locator (.*?)$")
    public void weGetTextOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    @Then("^we has value on warning-box frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnWarning_BoxFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we get list of elements on warning-box frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    @When("we click radio on warning-box frame (.*?) list locator (.*?)$")
    public void clickRadioOnWarning_BoxFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, null,  element, locator);
    }

    @And("^we capture screenshot on warning-box frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnWarning_BoxFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, null, null, null, element, locator, filePath);
    }

    @And("^we press on warning-box frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnWarning_BoxFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, null, null, null, element, locator, value);
    }
    
//______________________________________________________________________________________________________________________

    @Then("^we click on pop-up frame (.*?) locator (.*?)$")
    public void weClickActionOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we double click on pop-up frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we enter value on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnPop_UpFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, pop_up, null, null, element, locator, value);
    }

    @Then("^we select on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnPop_UpFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, pop_up, null, null, element, locator, value);
    }

    @Then("^we check on pop-up frame (.*?) locator (.*?)$")
    public void weCheckActionOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we uncheck on pop-up frame (.*?) locator (.*?)$")
    public void weUncheckActionOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we hover on pop-up frame (.*?) locator (.*?)$")
    public void weHoverActionOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we type on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnPop_UpFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, pop_up, null, null, element, locator, value);
    }

    @Then("^we scroll on pop-up frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we clear value on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnPop_UpFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnPop_UpFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnPop_UpFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnPop_UpFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we contain on pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnPop_UpFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, pop_up,  element, locator, value);
    }

    @Then("^we get text on pop-up frame(.*?) locator (.*?)$")
    public void weGetTextOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, pop_up, null, null, element, locator);
    }

    @Then("^we has value on pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnPop_UpFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, pop_up, null, null, element, locator, value);
    }

    @Then("^we get list of elements on pop-up frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, pop_up, null, null, element, locator);
    }

    @When("we click radio on pop-up frame (.*?) list locator (.*?)$")
    public void clickRadioOnPop_UpFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, pop_up,  element, locator);
    }

    @And("^we capture screenshot on pop-up frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnPop_UpFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, pop_up, null, null, element, locator, filePath);
    }

    @And("^we press on pop-up frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnPop_UpFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, pop_up, null, null, element, locator, value);
    }
//    __________________________________________________________________________________________________________________

    @Then("^we click on third frame (.*?) locator (.*?)$")
    public void weClickActionOnThirdFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we double click on third frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnThirdFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we enter value on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnThirdFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    @Then("^we select on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnThirdFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    @Then("^we check on third frame (.*?) locator (.*?)$")
    public void weCheckActionOnThirdFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we uncheck on third frame (.*?) locator (.*?)$")
    public void weUncheckActionOnThirdFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we hover on third frame (.*?) locator (.*?)$")
    public void weHoverActionOnThirdFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we type on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnThirdFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    @Then("^we scroll on third frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnThirdFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we clear value on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnThirdFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we verify on third frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnThirdFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we verify on third frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnThirdFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we verify on third frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnThirdFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we verify on third frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnThirdFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we contain on third frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnThirdFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, iFrame,  element, locator, value);
    }

    @Then("^we get text on third frame(.*?) locator (.*?)$")
    public void weGetTextOnThirdFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @Then("^we has value on third frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnThirdFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    @Then("^we get list of elements on third frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnThirdFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    @When("we click radio on third frame (.*?) list locator (.*?)$")
    public void clickRadioOnThirdFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, iFrame,  element, locator);
    }

    @And("^we capture screenshot on third frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnThirdFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, filePath);
    }

    @And("^we press on third frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnThirdFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }
    
//    __________________________________________________________________________________________________________________
@Then("^we click on second pop-up frame (.*?) locator (.*?)$")
public void weClickActionOnSecondPop_UpFrame(String element, String locator) {
    frameCommonMethods.click(iframePage, second_pop_up, null, null, element, locator);
}

    @Then("^we double click on second pop-up frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we enter value on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnSecondPop_UpFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, second_pop_up, null, null, element, locator, value);
    }

    @Then("^we select on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnSecondPop_UpFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, second_pop_up, null, null, element, locator, value);
    }

    @Then("^we check on second pop-up frame (.*?) locator (.*?)$")
    public void weCheckActionOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we uncheck on second pop-up frame (.*?) locator (.*?)$")
    public void weUncheckActionOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we hover on second pop-up frame (.*?) locator (.*?)$")
    public void weHoverActionOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we type on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnSecondPop_UpFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, second_pop_up, null, null, element, locator, value);
    }

    @Then("^we scroll on second pop-up frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we clear value on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnSecondPop_UpFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnSecondPop_UpFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnSecondPop_UpFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnSecondPop_UpFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we contain on second pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnSecondPop_UpFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, second_pop_up,  element, locator, value);
    }

    @Then("^we get text on second pop-up frame(.*?) locator (.*?)$")
    public void weGetTextOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, second_pop_up, null, null, element, locator);
    }

    @Then("^we has value on second pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnSecondPop_UpFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, second_pop_up, null, null, element, locator, value);
    }

    @Then("^we get list of elements on second pop-up frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, second_pop_up, null, null, element, locator);
    }

    @When("we click radio on second pop-up frame (.*?) list locator (.*?)$")
    public void clickRadioOnSecondPop_UpFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, second_pop_up,  element, locator);
    }

    @And("^we capture screenshot on second pop-up frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnSecondPop_UpFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, second_pop_up, null, null, element, locator, filePath);
    }

    @And("^we press on second pop-up frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnSecondPop_UpFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, second_pop_up, null, null, element, locator, value);
    }
    
//    __________________________________________________________________________________________________________________

    @Then("^we click on header frame (.*?) locator (.*?)$")
    public void weClickActionOnHeaderFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, null, null, null, element, locator);
    }

    @Then("^we double click on header frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnHeaderFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, null, null, null, element, locator);
    }

    @Then("^we enter value on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnHeaderFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we select on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnHeaderFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we check on header frame (.*?) locator (.*?)$")
    public void weCheckActionOnHeaderFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    @Then("^we uncheck on header frame (.*?) locator (.*?)$")
    public void weUncheckActionOnHeaderFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    @Then("^we hover on header frame (.*?) locator (.*?)$")
    public void weHoverActionOnHeaderFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, null, null, null, element, locator);
    }

    @Then("^we type on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnHeaderFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we scroll on header frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnHeaderFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, null, null, null, element, locator);
    }

    @Then("^we clear value on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnHeaderFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on header frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnHeaderFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on header frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnHeaderFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on header frame (.*?) of locator (.*?) is enabled")
    public void weVerifyOnHeaderFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, null, null, null, element, locator);
    }

    @Then("^we verify on header frame (.*?) of locator (.*?) is existed")
    public void weVerifyOnHeaderFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, null, null, null, element, locator);
    }

    @Then("^we contain on header frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnHeaderFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, null,  element, locator, value);
    }

    @Then("^we get text on header frame(.*?) locator (.*?)$")
    public void weGetTextOnHeaderFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    @Then("^we has value on header frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnHeaderFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, null, null, null, element, locator, value);
    }

    @Then("^we get list of elements on header frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnHeaderFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    @When("we click radio on header frame (.*?) list locator (.*?)$")
    public void clickRadioOnHeaderFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, null,  element, locator);
    }

    @And("^we capture screenshot on header frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnHeaderFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, null, null, null, element, locator, filePath);
    }

    @And("^we press on header frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnHeaderFrameKey(String element, String locator, String value) {
        frameCommonMethods.press(iframePage, null, null, null, element, locator, value);
    }
}