package com.ptaf.stepdefinitions;

// Importing necessary classes from Playwright and Cucumber for handling web pages and implementing behavior-driven development.

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

// This class defines the step definitions related to interaction with elements in different frames of a web application.
public class FrameCommonSteps {

    private final Page page; // A variable to hold the Playwright Page instance for interacting with the web page.
    private static Page iframePage; // A variable that holds the iframe Page instance for operations inside an iframe.

    // String constants defining locators for different iframes and UI components
    private final String iFrame = "iframe[name='iframeApplicationContent']";
    private final String iFrame_2 = "iframe[name='iframeTellerContent']";
    private final String iFrame_3 = "iframe[name='SearchResultsiFrame']";
    private final String pop_up = "iframe[frameborder='0px']"; // Locator for a popup iframe.
    private final String second_pop_up = "(//iframe[@frameborder='0px'])[2]"; // Locator for a second popup iframe.

    // Instance of the FrameCommonMethods class which contains common methods for interacting with iframes.
    private final FrameCommonMethods frameCommonMethods;

    // Constructor for initializing the FrameCommonSteps class.
    public FrameCommonSteps() {
        this.page = Hooks.getPage(); // Retrieve the Page instance from Hooks (a utility for handling Playwright page instantiation).
        this.frameCommonMethods = new FrameCommonMethods(iframePage); // Initialize frame common methods with the iframe page.
    }

    // This method switches control to the iframe after clicking a "Continue" button.
    public void switchToIframe() {
        iframePage = page.waitForPopup(() -> {
            // Wait for a new popup and click the "Continue" button within the iframe.
            page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continue")).click();
        });
    }

    /*
     * Given step to navigate to a specified URL and switch to the iframe.
     * @param URL The relative URL to navigate to, which is fetched from configuration properties.
     */
    @Given("^we navigate to (.*?) url$")
    public void weNavigateToURL(String URL) {
        page.navigate(ConfigurationProperties.getBaseUrl(URL)); // Navigate to the base URL specified in configuration.
        // page.setViewportSize(1920, 1080); // Uncomment to set the viewport size if required.
        switchToIframe(); // Switch to the iframe after navigation.
    }

    /*
     * Then step to perform a click action on an element within the main iframe.
     * @param element Identifier for the action (e.g., button type).
     * @param locator The specific locator for the element to click on.
     */
    @Then("^we click on main frame (.*?) locator (.*?)$")
    public void weClickActionOnMainFrame(String element, String locator) {
        frameCommonMethods.click(iframePage, iFrame, null, null, element, locator); // Call the click method from FrameCommonMethods.
    }

    /*
     * Then step to perform a double click action on an element within the main iframe.
     * @param element Identifier for the action.
     * @param locator The specific locator for the element to double-click on.
     */
    @Then("^we double click on main frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnMainFrame(String element, String locator) {
        frameCommonMethods.dblclick(iframePage, iFrame, null, null, element, locator); // Call the double click method.
    }

    /*
     * Then step to fill an input field in the main iframe with a specified value.
     * @param element Identifier for the input field.
     * @param locator The locator to find the input element.
     * @param value The value to enter into the input field.
     */
    @Then("^we enter value on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnMainFrame(String element, String locator, String value) {
        frameCommonMethods.fill(iframePage, iFrame, null, null, element, locator, value); // Fill the input field with a value.
    }

    /*
     * Then step to select a value from a dropdown element in the main iframe.
     * @param element Identifier for the select element.
     * @param locator The locator for the dropdown.
     * @param value The value to select from the dropdown.
     */
    @Then("^we select on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnMainFrame(String element, String locator, String value) {
        frameCommonMethods.select(iframePage, iFrame, null, null, element, locator, value); // Select the specified value.
    }

    /*
     * Then step to check a checkbox in the main iframe.
     * @param element Identifier for the checkbox.
     * @param locator The locator for the checkbox element to check.
     */
    @Then("^we check on main frame (.*?) locator (.*?)$")
    public void weCheckActionOnMainFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, null, null, element, locator); // Check the checkbox.
    }

    /*
     * Then step to uncheck a checkbox in the main iframe.
     * @param element Identifier for the checkbox.
     * @param locator The locator for the checkbox element to uncheck.
     */
    @Then("^we uncheck on main frame (.*?) locator (.*?)$")
    public void weUncheckActionOnMainFrame(String element, String locator) {
        frameCommonMethods.check(iframePage, iFrame, null, null, element, locator); // Uncheck the checkbox.
    }

    /*
     * Then step to hover over an element in the main iframe.
     * @param element Identifier for the element to hover.
     * @param locator The locator for the element to hover over.
     */
    @Then("^we hover on main frame (.*?) locator (.*?)$")
    public void weHoverActionOnMainFrame(String element, String locator) {
        frameCommonMethods.hover(iframePage, iFrame, null, null, element, locator); // Perform hover action.
    }

    /*
     * Then step to type a specified value into an input field in the main iframe.
     * @param element Identifier for the input field.
     * @param locator The locator for the input element.
     * @param value The value to be typed.
     */
    @Then("^we type on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnMainFrame(String element, String locator, String value) {
        frameCommonMethods.type(iframePage, iFrame, null, null, element, locator, value); // Type the specified value.
    }

    /*
     * Then step to scroll to a specific element in the main iframe.
     * @param element Identifier for the element to scroll to.
     * @param locator The locator for the element to scroll to.
     */
    @Then("^we scroll on main frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnMainFrame(String element, String locator) {
        frameCommonMethods.scroll(iframePage, iFrame, null, null, element, locator); // Scroll to the element.
    }

    /*
     * Then step to clear value in a specified input field in the main iframe.
     * @param element Identifier for the input field.
     * @param locator The locator for the input element to be cleared.
     */
    @Then("^we clear value on main frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnMainFrame(String element, String locator) {
        frameCommonMethods.clear(iframePage, iFrame, null, null, element, locator); // Clear the input field.
    }

    /*
     * Then step to verify if an element in the main iframe is visible.
     * @param element Identifier for the element.
     * @param locator The locator for the element to check visibility.
     */
    @Then("^we verify on main frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnMainFrameLocatorIsVisible(String element, String locator) {
        frameCommonMethods.isvisible(iframePage, iFrame, null, null, element, locator); // Verify visibility of the element.
    }

    /*
     * Then step to verify if a checkbox in the main iframe is checked.
     * @param element Identifier for the checkbox.
     * @param locator The locator for the checkbox to verify.
     */
    @Then("^we verify on main frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnMainFrameLocatorIsChecked(String element, String locator) {
        frameCommonMethods.ischecked(iframePage, iFrame, null, null, element, locator); // Verify if the checkbox is checked.
    }

    /*
     * Then step to verify if an element in the main iframe is enabled.
     * @param element Identifier for the element.
     * @param locator The locator for the element to verify if it is enabled.
     */
    @Then("^we verify on main frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnMainFrameLocatorIsEnabled(String element, String locator) {
        frameCommonMethods.isenabled(iframePage, iFrame, null, null, element, locator); // Verify if the element is enabled.
    }

    /*
     * Then step to verify if an element exists in the main iframe.
     * @param element Identifier for the element.
     * @param locator The locator for the element to verify existence.
     */
    @Then("^we verify on main frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnMainFrameLocatorIsExisted(String element, String locator) {
        frameCommonMethods.exists(iframePage, iFrame, null, null, element, locator); // Verify if the element exists.
    }

    /*
     * Then step to ensure that a specific value is contained within an element in the main iframe.
     * @param element Identifier for the element.
     * @param locator The locator for the element to check the value.
     * @param value The expected value to be checked.
     */
    @Then("^we contain on main frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnMainFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.contain(iframePage, iFrame, element, locator, value); // Check if the value is contained within the specified element.
    }

    /*
     * Then step to retrieve and log the text content of an element in the main iframe.
     * @param element Identifier for the element.
     * @param locator The locator for the element to retrieve the text.
     */
    @Then("^we get text on main frame(.*?) locator (.*?)$")
    public void weGetTextOnMainFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, null, null, element, locator); // Retrieve the text of the specified element.
    }

    /*
     * Then step to verify that an input field in the main iframe contains a specific value.
     * @param element Identifier for the input field.
     * @param locator The locator for the element to check the value.
     * @param value The value to verify against.
     */
    @Then("^we has value on main frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnMainFrameLocatorValue(String element, String locator, String value) {
        frameCommonMethods.hasvalue(iframePage, iFrame, null, null, element, locator, value); // Verify that the input field has the specified value.
    }

    /*
     * Then step to get a list of elements in the main iframe based on a specific locator.
     * @param element Identifier for the elements.
     * @param locator The locator for the elements to retrieve.
     */
    @Then("^we get list of elements on main frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnMainFrame(String element, String locator) {
        frameCommonMethods.gettext(iframePage, iFrame, null, null, element, locator); // Retrieve a list of elements based on the locator.
    }

    /*
     * When step to click on a radio button in the main iframe from a list of options.
     * @param element Identifier for the radio button.
     * @param locator The locator for the radio buttons list.
     */
    @When("we click radio on main frame (.*?) list locator (.*?)$")
    public void clickRadioOnMainFrame(String element, String locator) {
        frameCommonMethods.clickRadioButton(iframePage, iFrame, element, locator); // Click on the specified radio button.
    }

    /*
     * And step to capture a screenshot of an element in the main iframe.
     * @param element Identifier for the element.
     * @param locator The locator of the element to capture.
     * @param name The name to save the screenshot file as.
     */
    @And("^we capture screenshot on main frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnMainFrame(String element, String locator, String name) {
        String filePath = "test-output/screenshots/" + name + ".png"; // Define the file path for the screenshot.
        frameCommonMethods.screenshot(iframePage, iFrame, null, null, element, locator, filePath); // Capture screenshot and save it.
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the main iframe).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the iframe.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on main frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnMainFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the main iframe context, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, iFrame, null, null, element, locator, value);
    }

//      ________________________________________________________________________________________________________________

    /*
     * Represents a step to perform a click action on a specific element
     * within the second iframe.
     *
     * @param element Identifier for the element that should be clicked.
     * @param locator The locator used to find the clickable element within the iframe.
     */
    @Then("^we click on second frame (.*?) locator (.*?)$")
    public void weClickActionOnSecondFrame(String element, String locator) {
        // Calls the click method from frameCommonMethods to perform a click action on the designated element
        // found by the specified locator within the context of the second iframe.
        frameCommonMethods.click(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to perform a double click action on a specific element
     * within the second iframe.
     *
     * @param element Identifier for the element to be double-clicked.
     * @param locator The locator used to identify the element to be double-clicked within the iframe.
     */
    @Then("^we double click on second frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnSecondFrame(String element, String locator) {
        // Calls the dblclick method from frameCommonMethods to execute a double click action on the specified element
        // within the second iframe, as identified by the given locator.
        frameCommonMethods.dblclick(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to enter a value into a specific input field
     * within the second iframe.
     *
     * @param element Identifier for the input field where value should be entered.
     * @param locator The locator used to find the input field within the iframe.
     * @param value The value to be entered into the input field.
     */
    @Then("^we enter value on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnSecondFrame(String element, String locator, String value) {
        // Calls the fill method from frameCommonMethods to enter the specified value into the input field
        // within the second iframe, targeting the correct element identified by the given locator.
        frameCommonMethods.fill(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    /*
     * Represents a step to select a value from a dropdown or list
     * in the second iframe.
     *
     * @param element Identifier for the dropdown or select element.
     * @param locator The locator used to find the select element within the iframe.
     * @param value The value that needs to be selected from the dropdown.
     */
    @Then("^we select on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnSecondFrame(String element, String locator, String value) {
        // Calls the select method from frameCommonMethods to select the specified value from the dropdown
        // within the second iframe, using the provided locator to locate the element.
        frameCommonMethods.select(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    /*
     * Represents a step to check a checkbox on a specific element
     * within the second iframe.
     *
     * @param element Identifier for the checkbox element to be checked.
     * @param locator The locator used to find the checkbox within the iframe.
     */
    @Then("^we check on second frame (.*?) locator (.*?)$")
    public void weCheckActionOnSecondFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to check the specified checkbox
        // located within the second iframe using the provided locator.
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to uncheck a checkbox on a specific element
     * within the second iframe.
     *
     * @param element Identifier for the checkbox element to be unchecked.
     * @param locator The locator used to find the checkbox within the iframe.
     */
    @Then("^we uncheck on second frame (.*?) locator (.*?)$")
    public void weUncheckActionOnSecondFrame(String element, String locator) {
        // Calls the check method again as it may also serve to uncheck if there are conditions
        // in the method making it dynamic. This checks the specified checkbox state in the second iframe
        // and unchecks it using the provided locator.
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to hover over a specific element
     * in the second iframe.
     *
     * @param element Identifier for the element to hover over.
     * @param locator The locator used to find the hoverable element within the iframe.
     */
    @Then("^we hover on second frame (.*?) locator (.*?)$")
    public void weHoverActionOnSecondFrame(String element, String locator) {
        // Calls the hover method from frameCommonMethods to hover over the specified element
        // within the second iframe, locating it by the provided locator.
        frameCommonMethods.hover(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to type text into a specific input field
     * within the second iframe.
     *
     * @param element Identifier for the input field where text should be typed.
     * @param locator The locator used to find the input field within the iframe.
     * @param value The text to be typed into the input field.
     */
    @Then("^we type on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnSecondFrame(String element, String locator, String value) {
        // Calls the type method from frameCommonMethods to enter the specified text into the input field
        // within the context of the second iframe, targeting the identified element using the locator.
        frameCommonMethods.type(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    /*
     * Represents a step to scroll to a specific element
     * within the second iframe.
     *
     * @param element Identifier for the element to which the page should scroll.
     * @param locator The locator used to find the scrollable element within the iframe.
     */
    @Then("^we scroll on second frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnSecondFrame(String element, String locator) {
        // Calls the scroll method from frameCommonMethods to scroll to the specified element
        // within the second iframe, ensuring that it becomes visible in the viewport.
        frameCommonMethods.scroll(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to clear a value from an input field
     * in the second iframe.
     *
     * @param element Identifier for the input field from which the value needs to be cleared.
     * @param locator The locator used to find the input field within the iframe.
     */
    @Then("^we clear value on second frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnSecondFrame(String element, String locator) {
        // Calls the clear method from frameCommonMethods to remove any existing value from the input field
        // located within the second iframe, identified by the given locator.
        frameCommonMethods.clear(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element is visible
     * within the second iframe.
     *
     * @param element Identifier for the element to check visibility.
     * @param locator The locator used to identify the element within the iframe.
     */
    @Then("^we verify on second frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnSecondFrameLocatorIsVisible(String element, String locator) {
        // Calls the isvisible method from frameCommonMethods to confirm whether the specified element
        // is currently visible in the second iframe, using the locator to target the element.
        frameCommonMethods.isvisible(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to verify if a checkbox is checked
     * within the second iframe.
     *
     * @param element Identifier for the checkbox to check its state.
     * @param locator The locator used to find the checkbox within the iframe.
     */
    @Then("^we verify on second frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnSecondFrameLocatorIsChecked(String element, String locator) {
        // Calls the ischecked method from frameCommonMethods to check the status of the specified checkbox
        // in the second iframe and confirm if it is checked, using the locator for identification.
        frameCommonMethods.ischecked(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to verify if an element is enabled
     * within the second iframe.
     *
     * @param element Identifier for the element to check if it is enabled.
     * @param locator The locator used to find the element within the iframe.
     */
    @Then("^we verify on second frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnSecondFrameLocatorIsEnabled(String element, String locator) {
        // Calls the isenabled method from frameCommonMethods to validate if the specified element
        // is enabled and interactive in the second iframe, utilizing the locator to pinpoint the element.
        frameCommonMethods.isenabled(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to verify if an element exists
     * within the second iframe.
     *
     * @param element Identifier for the element to check existence.
     * @param locator The locator used to find the element within the iframe.
     */
    @Then("^we verify on second frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnSecondFrameLocatorIsExisted(String element, String locator) {
        // Calls the exists method from frameCommonMethods to confirm whether the specified element
        // is present in the second iframe, using the locator to locate it.
        frameCommonMethods.exists(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element contains a certain value
     * within the second iframe.
     *
     * @param element Identifier for the element to check.
     * @param locator The locator used to identify the element within the iframe.
     * @param value The expected value to be contained within the element.
     */
    @Then("^we contain on second frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnSecondFrameLocatorValue(String element, String locator, String value) {
        // Calls the contain method from frameCommonMethods to check if the specified element
        // contains the expected value in its text or attributes, as identified by the locator in the second iframe.
        frameCommonMethods.contain(iframePage, iFrame, element, locator, value);
    }

    /*
     * Represents a step to get the text content from a specific element
     * within the second iframe.
     *
     * @param element Identifier for the element whose text is to be retrieved.
     * @param locator The locator used to find the element within the iframe.
     */
    @Then("^we get text on second frame (.*?) locator (.*?)$")
    public void weGetTextOnSecondFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text content from the specified element
        // within the second iframe, using the provided locator to locate the element.
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to verify if an input field has a specific value
     * within the second iframe.
     *
     * @param element Identifier for the input field to check its value.
     * @param locator The locator used to find the input field within the iframe.
     * @param value The expected value that the input field should contain.
     */
    @Then("^we has value on second frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnSecondFrameLocatorValue(String element, String locator, String value) {
        // Calls the hasvalue method from frameCommonMethods to verify if the specified input field contains
        // the expected value, identifying the field via the given locator in the second iframe.
        frameCommonMethods.hasvalue(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

    /*
     * Represents a step to get a list of elements based on a locator
     * within the second iframe.
     *
     * @param element Identifier for the element to be checked.
     * @param locator The locator used to identify the list of elements within the iframe.
     */
    @Then("^we get list of elements on second frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnSecondFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text or details of the specified list of elements
        // located in the second iframe, using the locator for identification.
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, null, element, locator);
    }

    /*
     * Represents a step to click a radio button from a list of options
     * within the second iframe.
     *
     * @param element Identifier for the radio button list.
     * @param locator The locator used to find the list of radio buttons within the iframe.
     */
    @When("^we click radio on second frame (.*?) list locator (.*?)$")
    public void clickRadioOnSecondFrame(String element, String locator) {
        // Calls the clickRadioButton method from frameCommonMethods to click on the radio button
        // identified by the specified locator in the second iframe.
        frameCommonMethods.clickRadioButton(iframePage, iFrame, element, locator);
    }

    /*
     * Represents a step to capture a screenshot of a specific element
     * in the second iframe.
     *
     * @param element Identifier for the element for which the screenshot will be taken.
     * @param locator The locator used to find the element within the iframe.
     * @param name The name used to save the screenshot file.
     */
    @And("^we capture screenshot on second frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnSecondFrame(String element, String locator, String name) {
        // Constructs the file path for the screenshot using the provided name
        // and calls the screenshot method from frameCommonMethods to capture and save the screenshot
        // of the specified element in the second iframe, identified by the provided locator.
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, iFrame, iFrame_2, null, element, locator, filePath);
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the second iframe).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the iframe.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on second frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnSecondFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the context of the second iframe, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, iFrame, iFrame_2, null, element, locator, value);
    }

//   ___________________________________________________________________________________________________________________

    /*
     * Represents a step to perform a click action on a specific element
     * within the warning-box frame.
     *
     * @param element Identifier for the element that should be clicked.
     * @param locator The locator used to find the clickable element within the warning-box frame.
     */
    @Then("^we click on warning-box frame (.*?) locator (.*?)$")
    public void weClickActionOnWarning_BoxFrame(String element, String locator) {
        // Calls the click method from frameCommonMethods to execute a click on the specified element
        // in the warning-box frame, identified by the locator.
        frameCommonMethods.click(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to perform a double click action on a specific element
     * within the warning-box frame.
     *
     * @param element Identifier for the element to be double-clicked.
     * @param locator The locator used to identify the element to be double-clicked within the warning-box frame.
     */
    @Then("^we double click on warning-box frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnWarning_BoxFrame(String element, String locator) {
        // Calls the dblclick method from frameCommonMethods to execute a double click action
        // on the specified element within the warning-box frame, using the provided locator.
        frameCommonMethods.dblclick(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to enter a value into an input field
     * within the warning-box frame.
     *
     * @param element Identifier for the input field where the value should be entered.
     * @param locator The locator used to find the input field within the warning-box frame.
     * @param value The value to be entered into the input field.
     */
    @Then("^we enter value on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnWarning_BoxFrame(String element, String locator, String value) {
        // Calls the fill method from frameCommonMethods to enter the specified value
        // into the input field within the warning-box frame, targeting the identified element using the locator.
        frameCommonMethods.fill(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to select a value from a dropdown or list
     * in the warning-box frame.
     *
     * @param element Identifier for the dropdown or select element.
     * @param locator The locator used to find the select element within the warning-box frame.
     * @param value The value that needs to be selected from the dropdown.
     */
    @Then("^we select on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnWarning_BoxFrame(String element, String locator, String value) {
        // Calls the select method from frameCommonMethods to select the specified value
        // from the dropdown within the warning-box frame, identified by the given locator.
        frameCommonMethods.select(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to check a checkbox on a specific element
     * within the warning-box frame.
     *
     * @param element Identifier for the checkbox element to be checked.
     * @param locator The locator used to find the checkbox within the warning-box frame.
     */
    @Then("^we check on warning-box frame (.*?) locator (.*?)$")
    public void weCheckActionOnWarning_BoxFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to check the specified checkbox
        // located within the warning-box frame using the given locator.
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to uncheck a checkbox on a specific element
     * within the warning-box frame.
     *
     * @param element Identifier for the checkbox element to be unchecked.
     * @param locator The locator used to find the checkbox within the warning-box frame.
     */
    @Then("^we uncheck on warning-box frame (.*?) locator (.*?)$")
    public void weUncheckActionOnWarning_BoxFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to uncheck the specified checkbox
        // located within the warning-box frame using the given locator. In this context,
        // the method may perform a check or uncheck operation depending on its implementation.
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to hover over a specific element
     * in the warning-box frame.
     *
     * @param element Identifier for the element to hover over.
     * @param locator The locator used to find the hoverable element within the warning-box frame.
     */
    @Then("^we hover on warning-box frame (.*?) locator (.*?)$")
    public void weHoverActionOnWarning_BoxFrame(String element, String locator) {
        // Calls the hover method from frameCommonMethods to trigger a hover action over the specified element
        // within the warning-box frame, utilizing the locator for identification.
        frameCommonMethods.hover(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to type text into a specific input field
     * within the warning-box frame.
     *
     * @param element Identifier for the input field where text should be typed.
     * @param locator The locator used to find the input field within the warning-box frame.
     * @param value The text to be typed into the input field.
     */
    @Then("^we type on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnWarning_BoxFrame(String element, String locator, String value) {
        // Calls the type method from frameCommonMethods to input the specified text
        // into the input field located within the warning-box frame, identified by the locator.
        frameCommonMethods.type(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to scroll to a specific element
     * within the warning-box frame.
     *
     * @param element Identifier for the element to which the page should scroll.
     * @param locator The locator used to find the scrollable element within the warning-box frame.
     */
    @Then("^we scroll on warning-box frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnWarning_BoxFrame(String element, String locator) {
        // Calls the scroll method from frameCommonMethods to scroll the view to the specified element
        // within the warning-box frame, ensuring it is visible on the screen.
        frameCommonMethods.scroll(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to clear a value from an input field
     * in the warning-box frame.
     *
     * @param element Identifier for the input field from which the value needs to be cleared.
     * @param locator The locator used to find the input field within the warning-box frame.
     */
    @Then("^we clear value on warning-box frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnWarning_BoxFrame(String element, String locator) {
        // Calls the clear method from frameCommonMethods to remove any existing value from the input field
        // located within the warning-box frame, identified by the locator.
        frameCommonMethods.clear(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element is visible
     * within the warning-box frame.
     *
     * @param element Identifier for the element to check its visibility.
     * @param locator The locator used to identify the element within the warning-box frame.
     */
    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnWarning_BoxFrameLocatorIsVisible(String element, String locator) {
        // Calls the isvisible method from frameCommonMethods to confirm whether the specified element
        // is currently visible in the warning-box frame, utilizing the locator for identification.
        frameCommonMethods.isvisible(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a checkbox is checked
     * within the warning-box frame.
     *
     * @param element Identifier for the checkbox to check its state.
     * @param locator The locator used to find the checkbox within the warning-box frame.
     */
    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnWarning_BoxFrameLocatorIsChecked(String element, String locator) {
        // Calls the ischecked method from frameCommonMethods to check the status of the specified checkbox
        // within the warning-box frame, using the locator for identification.
        frameCommonMethods.ischecked(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element is enabled
     * within the warning-box frame.
     *
     * @param element Identifier for the element to check if it is enabled.
     * @param locator The locator used to find the element within the warning-box frame.
     */
    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnWarning_BoxFrameLocatorIsEnabled(String element, String locator) {
        // Calls the isenabled method from frameCommonMethods to validate if the specified element
        // is enabled and interactive in the warning-box frame, utilizing the locator to pinpoint the element.
        frameCommonMethods.isenabled(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element exists
     * within the warning-box frame.
     *
     * @param element Identifier for the element to check existence.
     * @param locator The locator used to find the element within the warning-box frame.
     */
    @Then("^we verify on warning-box frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnWarning_BoxFrameLocatorIsExisted(String element, String locator) {
        // Calls the exists method from frameCommonMethods to confirm whether the specified element
        // is present in the warning-box frame, using the locator for location.
        frameCommonMethods.exists(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element contains a certain value
     * within the warning-box frame.
     *
     * @param element Identifier for the element to check.
     * @param locator The locator used to identify the element within the warning-box frame.
     * @param value The expected value to be contained within the element.
     */
    @Then("^we contain on warning-box frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnWarning_BoxFrameLocatorValue(String element, String locator, String value) {
        // Calls the contain method from frameCommonMethods to check if the specified element
        // contains the expected value in its text or attributes, identified by the locator in the warning-box frame.
        frameCommonMethods.contain(iframePage, null, element, locator, value);
    }

    /*
     * Represents a step to get the text content from a specific element
     * within the warning-box frame.
     *
     * @param element Identifier for the element whose text is to be retrieved.
     * @param locator The locator used to find the element within the warning-box frame.
     */
    @Then("^we get text on warning-box frame(.*?) locator (.*?)$")
    public void weGetTextOnWarning_BoxFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text content from the specified element
        // within the warning-box frame, identified by the provided locator.
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an input field has a specific value
     * within the warning-box frame.
     *
     * @param element Identifier for the input field to check its value.
     * @param locator The locator used to find the input field within the warning-box frame.
     * @param value The expected value that the input field should contain.
     */
    @Then("^we has value on warning-box frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnWarning_BoxFrameLocatorValue(String element, String locator, String value) {
        // Calls the hasvalue method from frameCommonMethods to verify if the specified input field contains
        // the expected value, identified by the given locator in the warning-box frame.
        frameCommonMethods.hasvalue(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to get a list of elements based on a locator
     * within the warning-box frame.
     *
     * @param element Identifier for the element to be checked.
     * @param locator The locator used to identify the list of elements within the warning-box frame.
     */
    @Then("^we get list of elements on warning-box frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnWarning_BoxFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text or details of the specified list of elements
        // located in the warning-box frame, using the locator for identification.
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to click a radio button from a list of options
     * within the warning-box frame.
     *
     * @param element Identifier for the list of radio buttons.
     * @param locator The locator used to find the list of radio buttons within the warning-box frame.
     */
    @When("we click radio on warning-box frame (.*?) list locator (.*?)$")
    public void clickRadioOnWarning_BoxFrame(String element, String locator) {
        // Calls clickRadioButton method from frameCommonMethods to click on the specified radio button
        // identified by the locator in the warning-box frame.
        frameCommonMethods.clickRadioButton(iframePage, null, element, locator);
    }

    /*
     * Represents a step to capture a screenshot of a specific element
     * in the warning-box frame.
     *
     * @param element Identifier for the element for which the screenshot will be taken.
     * @param locator The locator used to find the element within the warning-box frame.
     * @param name The name used to save the screenshot file.
     */
    @And("^we capture screenshot on warning-box frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnWarning_BoxFrame(String element, String locator, String name) {
        // Constructs the file path for the screenshot using the provided name
        // and calls the screenshot method from frameCommonMethods to capture and save the screenshot
        // of the specified element in the warning-box frame, identified by the provided locator.
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, null, null, null, element, locator, filePath);
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the warning-box frame).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the warning-box frame.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on warning-box frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnWarning_BoxFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the context of the warning-box frame, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, null, null, null, element, locator, value);
    }

//______________________________________________________________________________________________________________________

    /*
     * Represents a step to perform a click action on a specific element
     * within the pop-up frame.
     *
     * @param element Identifier for the element that should be clicked.
     * @param locator The locator used to find the clickable element within the pop-up frame.
     */
    @Then("^we click on pop-up frame (.*?) locator (.*?)$")
    public void weClickActionOnPop_UpFrame(String element, String locator) {
        // Calls the click method from frameCommonMethods to execute a click on the specified element
        // in the pop-up frame, identified by the locator.
        frameCommonMethods.click(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to perform a double click action on a specific element
     * within the pop-up frame.
     *
     * @param element Identifier for the element to be double-clicked.
     * @param locator The locator used to identify the element to be double-clicked within the pop-up frame.
     */
    @Then("^we double click on pop-up frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnPop_UpFrame(String element, String locator) {
        // Calls the dblclick method from frameCommonMethods to execute a double click action
        // on the specified element within the pop-up frame, using the provided locator.
        frameCommonMethods.dblclick(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to enter a value into an input field
     * within the pop-up frame.
     *
     * @param element Identifier for the input field where the value should be entered.
     * @param locator The locator used to find the input field within the pop-up frame.
     * @param value The value to be entered into the input field.
     */
    @Then("^we enter value on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnPop_UpFrame(String element, String locator, String value) {
        // Calls the fill method from frameCommonMethods to enter the specified value
        // into the input field within the pop-up frame, targeting the identified element using the locator.
        frameCommonMethods.fill(iframePage, pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to select a value from a dropdown or list
     * in the pop-up frame.
     *
     * @param element Identifier for the dropdown or select element.
     * @param locator The locator used to find the select element within the pop-up frame.
     * @param value The value that needs to be selected from the dropdown.
     */
    @Then("^we select on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnPop_UpFrame(String element, String locator, String value) {
        // Calls the select method from frameCommonMethods to select the specified value
        // from the dropdown within the pop-up frame, identified by the given locator.
        frameCommonMethods.select(iframePage, pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to check a checkbox on a specific element
     * within the pop-up frame.
     *
     * @param element Identifier for the checkbox element to be checked.
     * @param locator The locator used to find the checkbox within the pop-up frame.
     */
    @Then("^we check on pop-up frame (.*?) locator (.*?)$")
    public void weCheckActionOnPop_UpFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to check the specified checkbox
        // located within the pop-up frame using the given locator.
        frameCommonMethods.check(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to uncheck a checkbox on a specific element
     * within the pop-up frame.
     *
     * @param element Identifier for the checkbox element to be unchecked.
     * @param locator The locator used to find the checkbox within the pop-up frame.
     */
    @Then("^we uncheck on pop-up frame (.*?) locator (.*?)$")
    public void weUncheckActionOnPop_UpFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to uncheck the specified checkbox
        // located within the pop-up frame using the given locator. This method may handle
        // the uncheck condition depending on its internal logic.
        frameCommonMethods.check(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to hover over a specific element
     * in the pop-up frame.
     *
     * @param element Identifier for the element to hover over.
     * @param locator The locator used to find the hoverable element within the pop-up frame.
     */
    @Then("^we hover on pop-up frame (.*?) locator (.*?)$")
    public void weHoverActionOnPop_UpFrame(String element, String locator) {
        // Calls the hover method from frameCommonMethods to trigger a hover action over the specified element
        // within the pop-up frame, utilizing the locator for identification.
        frameCommonMethods.hover(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to type text into a specific input field
     * within the pop-up frame.
     *
     * @param element Identifier for the input field where text should be typed.
     * @param locator The locator used to find the input field within the pop-up frame.
     * @param value The text to be typed into the input field.
     */
    @Then("^we type on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnPop_UpFrame(String element, String locator, String value) {
        // Calls the type method from frameCommonMethods to input the specified text
        // into the input field located within the pop-up frame, identified by the locator.
        frameCommonMethods.type(iframePage, pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to scroll to a specific element
     * within the pop-up frame.
     *
     * @param element Identifier for the element to which the page should scroll.
     * @param locator The locator used to find the scrollable element within the pop-up frame.
     */
    @Then("^we scroll on pop-up frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnPop_UpFrame(String element, String locator) {
        // Calls the scroll method from frameCommonMethods to scroll the view to the specified element
        // within the pop-up frame, ensuring it is visible on the screen.
        frameCommonMethods.scroll(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to clear a value from an input field
     * in the pop-up frame.
     *
     * @param element Identifier for the input field from which the value needs to be cleared.
     * @param locator The locator used to find the input field within the pop-up frame.
     */
    @Then("^we clear value on pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnPop_UpFrame(String element, String locator) {
        // Calls the clear method from frameCommonMethods to remove any existing value from the input field
        // located within the pop-up frame, identified by the locator.
        frameCommonMethods.clear(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element is visible
     * within the pop-up frame.
     *
     * @param element Identifier for the element to check its visibility.
     * @param locator The locator used to identify the element within the pop-up frame.
     */
    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnPop_UpFrameLocatorIsVisible(String element, String locator) {
        // Calls the isvisible method from frameCommonMethods to confirm whether the specified element
        // is currently visible in the pop-up frame, utilizing the locator for identification.
        frameCommonMethods.isvisible(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a checkbox is checked
     * within the pop-up frame.
     *
     * @param element Identifier for the checkbox to check its state.
     * @param locator The locator used to find the checkbox within the pop-up frame.
     */
    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnPop_UpFrameLocatorIsChecked(String element, String locator) {
        // Calls the ischecked method from frameCommonMethods to check the status of the specified checkbox
        // within the pop-up frame, using the locator for identification.
        frameCommonMethods.ischecked(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element is enabled
     * within the pop-up frame.
     *
     * @param element Identifier for the element to check if it is enabled.
     * @param locator The locator used to find the element within the pop-up frame.
     */
    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnPop_UpFrameLocatorIsEnabled(String element, String locator) {
        // Calls the isenabled method from frameCommonMethods to validate if the specified element
        // is enabled and interactive in the pop-up frame, utilizing the locator to pinpoint the element.
        frameCommonMethods.isenabled(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element exists
     * within the pop-up frame.
     *
     * @param element Identifier for the element to check existence.
     * @param locator The locator used to find the element within the pop-up frame.
     */
    @Then("^we verify on pop-up frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnPop_UpFrameLocatorIsExisted(String element, String locator) {
        // Calls the exists method from frameCommonMethods to confirm whether the specified element
        // is present in the pop-up frame, using the locator for location.
        frameCommonMethods.exists(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element contains a certain value
     * within the pop-up frame.
     *
     * @param element Identifier for the element to check.
     * @param locator The locator used to identify the element within the pop-up frame.
     * @param value The expected value to be contained within the element.
     */
    @Then("^we contain on pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnPop_UpFrameLocatorValue(String element, String locator, String value) {
        // Calls the contain method from frameCommonMethods to check if the specified element
        // contains the expected value in its text or attributes, identified by the locator in the pop-up frame.
        frameCommonMethods.contain(iframePage, pop_up, element, locator, value);
    }

    /*
     * Represents a step to get the text content from a specific element
     * within the pop-up frame.
     *
     * @param element Identifier for the element whose text is to be retrieved.
     * @param locator The locator used to find the element within the pop-up frame.
     */
    @Then("^we get text on pop-up frame(.*?) locator (.*?)$")
    public void weGetTextOnPop_UpFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text content from the specified element
        // within the pop-up frame, identified by the provided locator.
        frameCommonMethods.gettext(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an input field has a specific value
     * within the pop-up frame.
     *
     * @param element Identifier for the input field to check its value.
     * @param locator The locator used to find the input field within the pop-up frame.
     * @param value The expected value that the input field should contain.
     */
    @Then("^we has value on pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnPop_UpFrameLocatorValue(String element, String locator, String value) {
        // Calls the hasvalue method from frameCommonMethods to verify if the specified input field contains
        // the expected value, identified by the given locator in the pop-up frame.
        frameCommonMethods.hasvalue(iframePage, pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to get a list of elements based on a locator
     * within the pop-up frame.
     *
     * @param element Identifier for the element to be checked.
     * @param locator The locator used to identify the list of elements within the pop-up frame.
     */
    @Then("^we get list of elements on pop-up frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnPop_UpFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text or details of the specified list of elements
        // located in the pop-up frame, using the locator for identification.
        frameCommonMethods.gettext(iframePage, pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to click a radio button from a list of options
     * within the pop-up frame.
     *
     * @param element Identifier for the list of radio buttons.
     * @param locator The locator used to find the list of radio buttons within the pop-up frame.
     */
    @When("we click radio on pop-up frame (.*?) list locator (.*?)$")
    public void clickRadioOnPop_UpFrame(String element, String locator) {
        // Calls clickRadioButton method from frameCommonMethods to click on the specified radio button
        // identified by the locator in the pop-up frame.
        frameCommonMethods.clickRadioButton(iframePage, pop_up, element, locator);
    }

    /*
     * Represents a step to capture a screenshot of a specific element
     * in the pop-up frame.
     *
     * @param element Identifier for the element for which the screenshot will be taken.
     * @param locator The locator used to find the element within the pop-up frame.
     * @param name The name used to save the screenshot file.
     */
    @And("^we capture screenshot on pop-up frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnPop_UpFrame(String element, String locator, String name) {
        // Constructs the file path for the screenshot using the provided name
        // and calls the screenshot method from frameCommonMethods to capture and save the screenshot
        // of the specified element in the pop-up frame, identified by the provided locator.
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, pop_up, null, null, element, locator, filePath);
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the pop-up frame).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the pop-up frame.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on pop-up frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnPop_UpFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the context of the pop-up frame, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, pop_up, null, null, element, locator, value);
    }

    //    __________________________________________________________________________________________________________________
    /*
     * Represents a step to perform a click action on a specific element
     * within the third frame.
     *
     * @param element Identifier for the element that should be clicked.
     * @param locator The locator used to find the clickable element within the third frame.
     */
    @Then("^we click on third frame (.*?) locator (.*?)$")
    public void weClickActionOnThirdFrame(String element, String locator) {
        // Calls the click method from frameCommonMethods to execute a click on the specified element
        // in the third frame, identified by the locator.
        frameCommonMethods.click(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to perform a double click action on a specific element
     * within the third frame.
     *
     * @param element Identifier for the element to be double-clicked.
     * @param locator The locator used to identify the element to be double-clicked within the third frame.
     */
    @Then("^we double click on third frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnThirdFrame(String element, String locator) {
        // Calls the dblclick method from frameCommonMethods to execute a double click action
        // on the specified element within the third frame, using the provided locator.
        frameCommonMethods.dblclick(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to enter a value into an input field
     * within the third frame.
     *
     * @param element Identifier for the input field where the value should be entered.
     * @param locator The locator used to find the input field within the third frame.
     * @param value The value to be entered into the input field.
     */
    @Then("^we enter value on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnThirdFrame(String element, String locator, String value) {
        // Calls the fill method from frameCommonMethods to enter the specified value
        // into the input field within the third frame, targeting the identified element using the locator.
        frameCommonMethods.fill(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    /*
     * Represents a step to select a value from a dropdown or list
     * in the third frame.
     *
     * @param element Identifier for the dropdown or select element.
     * @param locator The locator used to find the select element within the third frame.
     * @param value The value that needs to be selected from the dropdown.
     */
    @Then("^we select on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnThirdFrame(String element, String locator, String value) {
        // Calls the select method from frameCommonMethods to select the specified value
        // from the dropdown within the third frame, identified by the given locator.
        frameCommonMethods.select(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    /*
     * Represents a step to check a checkbox on a specific element
     * within the third frame.
     *
     * @param element Identifier for the checkbox element to be checked.
     * @param locator The locator used to find the checkbox within the third frame.
     */
    @Then("^we check on third frame (.*?) locator (.*?)$")
    public void weCheckActionOnThirdFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to check the specified checkbox
        // located within the third frame using the provided locator.
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to uncheck a checkbox on a specific element
     * within the third frame.
     *
     * @param element Identifier for the checkbox element to be unchecked.
     * @param locator The locator used to find the checkbox within the third frame.
     */
    @Then("^we uncheck on third frame (.*?) locator (.*?)$")
    public void weUncheckActionOnThirdFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to uncheck the specified checkbox
        // located within the third frame using the provided locator. This method may handle
        // the uncheck condition depending on its internal logic.
        frameCommonMethods.check(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to hover over a specific element
     * in the third frame.
     *
     * @param element Identifier for the element to hover over.
     * @param locator The locator used to find the hoverable element within the third frame.
     */
    @Then("^we hover on third frame (.*?) locator (.*?)$")
    public void weHoverActionOnThirdFrame(String element, String locator) {
        // Calls the hover method from frameCommonMethods to trigger a hover action over the specified element
        // within the third frame, utilizing the locator for identification.
        frameCommonMethods.hover(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to type text into a specific input field
     * within the third frame.
     *
     * @param element Identifier for the input field where text should be typed.
     * @param locator The locator used to find the input field within the third frame.
     * @param value The text to be typed into the input field.
     */
    @Then("^we type on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnThirdFrame(String element, String locator, String value) {
        // Calls the type method from frameCommonMethods to input the specified text
        // into the input field located within the third frame, identified by the locator.
        frameCommonMethods.type(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    /*
     * Represents a step to scroll to a specific element
     * within the third frame.
     *
     * @param element Identifier for the element to which the page should scroll.
     * @param locator The locator used to find the scrollable element within the third frame.
     */
    @Then("^we scroll on third frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnThirdFrame(String element, String locator) {
        // Calls the scroll method from frameCommonMethods to scroll the view to the specified element
        // within the third frame, ensuring it is visible on the screen.
        frameCommonMethods.scroll(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to clear a value from an input field
     * in the third frame.
     *
     * @param element Identifier for the input field from which the value needs to be cleared.
     * @param locator The locator used to find the input field within the third frame.
     */
    @Then("^we clear value on third frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnThirdFrame(String element, String locator) {
        // Calls the clear method from frameCommonMethods to remove any existing value from the input field
        // located within the third frame, identified by the locator.
        frameCommonMethods.clear(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to verify if a specific element is visible
     * within the third frame.
     *
     * @param element Identifier for the element to check its visibility.
     * @param locator The locator used to identify the element within the third frame.
     */
    @Then("^we verify on third frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnThirdFrameLocatorIsVisible(String element, String locator) {
        // Calls the isvisible method from frameCommonMethods to confirm whether the specified element
        // is currently visible in the third frame, utilizing the locator for identification.
        frameCommonMethods.isvisible(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to verify if a checkbox is checked
     * within the third frame.
     *
     * @param element Identifier for the checkbox to check its state.
     * @param locator The locator used to find the checkbox within the third frame.
     */
    @Then("^we verify on third frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnThirdFrameLocatorIsChecked(String element, String locator) {
        // Calls the ischecked method from frameCommonMethods to check the status of the specified checkbox
        // within the third frame, using the locator for identification.
        frameCommonMethods.ischecked(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to verify if an element is enabled
     * within the third frame.
     *
     * @param element Identifier for the element to check if it is enabled.
     * @param locator The locator used to find the element within the third frame.
     */
    @Then("^we verify on third frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnThirdFrameLocatorIsEnabled(String element, String locator) {
        // Calls the isenabled method from frameCommonMethods to validate if the specified element
        // is enabled and interactive in the third frame, utilizing the locator to pinpoint the element.
        frameCommonMethods.isenabled(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to verify if an element exists
     * within the third frame.
     *
     * @param element Identifier for the element to check existence.
     * @param locator The locator used to find the element within the third frame.
     */
    @Then("^we verify on third frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnThirdFrameLocatorIsExisted(String element, String locator) {
        // Calls the exists method from frameCommonMethods to confirm whether the specified element
        // is present in the third frame, using the locator for identification.
        frameCommonMethods.exists(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to verify if a specific element contains a certain value
     * within the third frame.
     *
     * @param element Identifier for the element to check.
     * @param locator The locator used to identify the element within the third frame.
     * @param value The expected value to be contained within the element.
     */
    @Then("^we contain on third frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnThirdFrameLocatorValue(String element, String locator, String value) {
        // Calls the contain method from frameCommonMethods to check if the specified element
        // contains the expected value in its text or attributes, identified by the locator in the third frame.
        frameCommonMethods.contain(iframePage, iFrame, element, locator, value);
    }

    /*
     * Represents a step to get the text content from a specific element
     * within the third frame.
     *
     * @param element Identifier for the element whose text is to be retrieved.
     * @param locator The locator used to find the element within the third frame.
     */
    @Then("^we get text on third frame(.*?) locator (.*?)$")
    public void weGetTextOnThirdFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text content from the specified element
        // within the third frame, identified by the provided locator.
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to verify if an input field has a specific value
     * within the third frame.
     *
     * @param element Identifier for the input field to check its value.
     * @param locator The locator used to find the input field within the third frame.
     * @param value The expected value that the input field should contain.
     */
    @Then("^we has value on third frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnThirdFrameLocatorValue(String element, String locator, String value) {
        // Calls the hasvalue method from frameCommonMethods to verify if the specified input field contains
        // the expected value, identified by the given locator in the third frame.
        frameCommonMethods.hasvalue(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

    /*
     * Represents a step to get a list of elements based on a locator
     * within the third frame.
     *
     * @param element Identifier for the element to be checked.
     * @param locator The locator used to identify the list of elements within the third frame.
     */
    @Then("^we get list of elements on third frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnThirdFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text or details of the specified list of elements
        // located in the third frame, using the locator for identification.
        frameCommonMethods.gettext(iframePage, iFrame, iFrame_2, iFrame_3, element, locator);
    }

    /*
     * Represents a step to click a radio button from a list of options
     * within the third frame.
     *
     * @param element Identifier for the list of radio buttons.
     * @param locator The locator used to find the list of radio buttons within the third frame.
     */
    @When("we click radio on third frame (.*?) list locator (.*?)$")
    public void clickRadioOnThirdFrame(String element, String locator) {
        // Calls clickRadioButton method from frameCommonMethods to click on the specified radio button
        // identified by the locator in the third frame.
        frameCommonMethods.clickRadioButton(iframePage, iFrame, element, locator);
    }

    /*
     * Represents a step to capture a screenshot of a specific element
     * in the third frame.
     *
     * @param element Identifier for the element for which the screenshot will be taken.
     * @param locator The locator used to find the element within the third frame.
     * @param name The name used to save the screenshot file.
     */
    @And("^we capture screenshot on third frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnThirdFrame(String element, String locator, String name) {
        // Constructs the file path for the screenshot using the provided name
        // and calls the screenshot method from frameCommonMethods to capture and save the screenshot
        // of the specified element in the third frame, identified by the provided locator.
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, filePath);
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the third frame).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the third frame.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on third frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnThirdFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the context of the third frame, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, iFrame, iFrame_2, iFrame_3, element, locator, value);
    }

//    __________________________________________________________________________________________________________________

    /*
     * Represents a step to perform a click action on a specific element
     * within the second pop-up frame.
     *
     * @param element Identifier for the element that should be clicked.
     * @param locator The locator used to find the clickable element within the second pop-up frame.
     */
    @Then("^we click on second pop-up frame (.*?) locator (.*?)$")
    public void weClickActionOnSecondPop_UpFrame(String element, String locator) {
        // Calls the click method from frameCommonMethods to execute a click on the specified element
        // in the second pop-up frame, identified by the locator.
        frameCommonMethods.click(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to perform a double click action on a specific element
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to be double-clicked.
     * @param locator The locator used to identify the element to be double-clicked within the second pop-up frame.
     */
    @Then("^we double click on second pop-up frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnSecondPop_UpFrame(String element, String locator) {
        // Calls the dblclick method from frameCommonMethods to execute a double click action
        // on the specified element within the second pop-up frame, using the provided locator.
        frameCommonMethods.dblclick(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to enter a value into an input field
     * within the second pop-up frame.
     *
     * @param element Identifier for the input field where the value should be entered.
     * @param locator The locator used to find the input field within the second pop-up frame.
     * @param value The value to be entered into the input field.
     */
    @Then("^we enter value on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnSecondPop_UpFrame(String element, String locator, String value) {
        // Calls the fill method from frameCommonMethods to enter the specified value
        // into the input field within the second pop-up frame, targeting the identified element using the locator.
        frameCommonMethods.fill(iframePage, second_pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to select a value from a dropdown or list
     * in the second pop-up frame.
     *
     * @param element Identifier for the dropdown or select element.
     * @param locator The locator used to find the select element within the second pop-up frame.
     * @param value The value that needs to be selected from the dropdown.
     */
    @Then("^we select on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnSecondPop_UpFrame(String element, String locator, String value) {
        // Calls the select method from frameCommonMethods to select the specified value
        // from the dropdown within the second pop-up frame, identified by the given locator.
        frameCommonMethods.select(iframePage, second_pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to check a checkbox on a specific element
     * within the second pop-up frame.
     *
     * @param element Identifier for the checkbox element to be checked.
     * @param locator The locator used to find the checkbox within the second pop-up frame.
     */
    @Then("^we check on second pop-up frame (.*?) locator (.*?)$")
    public void weCheckActionOnSecondPop_UpFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to check the specified checkbox
        // located within the second pop-up frame using the provided locator.
        frameCommonMethods.check(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to uncheck a checkbox on a specific element
     * within the second pop-up frame.
     *
     * @param element Identifier for the checkbox element to be unchecked.
     * @param locator The locator used to find the checkbox within the second pop-up frame.
     */
    @Then("^we uncheck on second pop-up frame (.*?) locator (.*?)$")
    public void weUncheckActionOnSecondPop_UpFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to uncheck the specified checkbox
        // located within the second pop-up frame using the provided locator. This method may handle
        // the uncheck condition depending on its internal logic.
        frameCommonMethods.check(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to hover over a specific element
     * in the second pop-up frame.
     *
     * @param element Identifier for the element to hover over.
     * @param locator The locator used to find the hoverable element within the second pop-up frame.
     */
    @Then("^we hover on second pop-up frame (.*?) locator (.*?)$")
    public void weHoverActionOnSecondPop_UpFrame(String element, String locator) {
        // Calls the hover method from frameCommonMethods to trigger a hover action over the specified element
        // within the second pop-up frame, utilizing the locator for identification.
        frameCommonMethods.hover(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to type text into a specific input field
     * within the second pop-up frame.
     *
     * @param element Identifier for the input field where text should be typed.
     * @param locator The locator used to find the input field within the second pop-up frame.
     * @param value The text to be typed into the input field.
     */
    @Then("^we type on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnSecondPop_UpFrame(String element, String locator, String value) {
        // Calls the type method from frameCommonMethods to input the specified text
        // into the input field located within the second pop-up frame, identified by the locator.
        frameCommonMethods.type(iframePage, second_pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to scroll to a specific element
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to which the page should scroll.
     * @param locator The locator used to find the scrollable element within the second pop-up frame.
     */
    @Then("^we scroll on second pop-up frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnSecondPop_UpFrame(String element, String locator) {
        // Calls the scroll method from frameCommonMethods to scroll the view to the specified element
        // within the second pop-up frame, ensuring it is visible on the screen.
        frameCommonMethods.scroll(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to clear a value from an input field
     * in the second pop-up frame.
     *
     * @param element Identifier for the input field from which the value needs to be cleared.
     * @param locator The locator used to find the input field within the second pop-up frame.
     */
    @Then("^we clear value on second pop-up frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnSecondPop_UpFrame(String element, String locator) {
        // Calls the clear method from frameCommonMethods to remove any existing value from the input field
        // located within the second pop-up frame, identified by the locator.
        frameCommonMethods.clear(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element is visible
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to check its visibility.
     * @param locator The locator used to identify the element within the second pop-up frame.
     */
    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnSecondPop_UpFrameLocatorIsVisible(String element, String locator) {
        // Calls the isvisible method from frameCommonMethods to confirm whether the specified element
        // is currently visible in the second pop-up frame, utilizing the locator for identification.
        frameCommonMethods.isvisible(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a checkbox is checked
     * within the second pop-up frame.
     *
     * @param element Identifier for the checkbox to check its state.
     * @param locator The locator used to find the checkbox within the second pop-up frame.
     */
    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnSecondPop_UpFrameLocatorIsChecked(String element, String locator) {
        // Calls the ischecked method from frameCommonMethods to check the status of the specified checkbox
        // within the second pop-up frame, using the locator for identification.
        frameCommonMethods.ischecked(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element is enabled
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to check if it is enabled.
     * @param locator The locator used to find the element within the second pop-up frame.
     */
    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnSecondPop_UpFrameLocatorIsEnabled(String element, String locator) {
        // Calls the isenabled method from frameCommonMethods to validate if the specified element
        // is enabled and interactive in the second pop-up frame, utilizing the locator to pinpoint the element.
        frameCommonMethods.isenabled(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element exists
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to check existence.
     * @param locator The locator used to find the element within the second pop-up frame.
     */
    @Then("^we verify on second pop-up frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnSecondPop_UpFrameLocatorIsExisted(String element, String locator) {
        // Calls the exists method from frameCommonMethods to confirm whether the specified element
        // is present in the second pop-up frame, using the locator for identification.
        frameCommonMethods.exists(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element contains a certain value
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to check.
     * @param locator The locator used to identify the element within the second pop-up frame.
     * @param value The expected value to be contained within the element.
     */
    @Then("^we contain on second pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnSecondPop_UpFrameLocatorValue(String element, String locator, String value) {
        // Calls the contain method from frameCommonMethods to check if the specified element
        // contains the expected value in its text or attributes, identified by the locator in the second pop-up frame.
        frameCommonMethods.contain(iframePage, second_pop_up, element, locator, value);
    }

    /*
     * Represents a step to get the text content from a specific element
     * within the second pop-up frame.
     *
     * @param element Identifier for the element whose text is to be retrieved.
     * @param locator The locator used to find the element within the second pop-up frame.
     */
    @Then("^we get text on second pop-up frame(.*?) locator (.*?)$")
    public void weGetTextOnSecondPop_UpFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text content from the specified element
        // within the second pop-up frame, identified by the provided locator.
        frameCommonMethods.gettext(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an input field has a specific value
     * within the second pop-up frame.
     *
     * @param element Identifier for the input field to check its value.
     * @param locator The locator used to find the input field within the second pop-up frame.
     * @param value The expected value that the input field should contain.
     */
    @Then("^we has value on second pop-up frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnSecondPop_UpFrameLocatorValue(String element, String locator, String value) {
        // Calls the hasvalue method from frameCommonMethods to verify if the specified input field contains
        // the expected value, identified by the given locator in the second pop-up frame.
        frameCommonMethods.hasvalue(iframePage, second_pop_up, null, null, element, locator, value);
    }

    /*
     * Represents a step to get a list of elements based on a locator
     * within the second pop-up frame.
     *
     * @param element Identifier for the element to be checked.
     * @param locator The locator used to identify the list of elements within the second pop-up frame.
     */
    @Then("^we get list of elements on second pop-up frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnSecondPop_UpFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text or details of the specified list of elements
        // located in the second pop-up frame, using the locator for identification.
        frameCommonMethods.gettext(iframePage, second_pop_up, null, null, element, locator);
    }

    /*
     * Represents a step to click a radio button from a list of options
     * within the second pop-up frame.
     *
     * @param element Identifier for the list of radio buttons.
     * @param locator The locator used to find the list of radio buttons within the second pop-up frame.
     */
    @When("we click radio on second pop-up frame (.*?) list locator (.*?)$")
    public void clickRadioOnSecondPop_UpFrame(String element, String locator) {
        // Calls clickRadioButton method from frameCommonMethods to click on the specified radio button
        // identified by the locator in the second pop-up frame.
        frameCommonMethods.clickRadioButton(iframePage, second_pop_up, element, locator);
    }

    /*
     * Represents a step to capture a screenshot of a specific element
     * in the second pop-up frame.
     *
     * @param element Identifier for the element for which the screenshot will be taken.
     * @param locator The locator used to find the element within the second pop-up frame.
     * @param name The name used to save the screenshot file.
     */
    @And("^we capture screenshot on second pop-up frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnSecondPop_UpFrame(String element, String locator, String name) {
        // Constructs the file path for the screenshot using the provided name
        // and calls the screenshot method from frameCommonMethods to capture and save the screenshot
        // of the specified element in the second pop-up frame, identified by the provided locator.
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, second_pop_up, null, null, element, locator, filePath);
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the second pop-up frame).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the second pop-up frame.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on second pop-up frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnSecondPop_UpFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the context of the second pop-up frame, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, second_pop_up, null, null, element, locator, value);
    }

//    __________________________________________________________________________________________________________________

    /*
     * Represents a step to perform a click action on a specific element
     * within the header frame.
     *
     * @param element Identifier for the element that should be clicked.
     * @param locator The locator used to find the clickable element within the header frame.
     */
    @Then("^we click on header frame (.*?) locator (.*?)$")
    public void weClickActionOnHeaderFrame(String element, String locator) {
        // Calls the click method from frameCommonMethods to execute a click on the specified element
        // in the header frame, identified by the locator.
        frameCommonMethods.click(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to perform a double click action on a specific element
     * within the header frame.
     *
     * @param element Identifier for the element to be double-clicked.
     * @param locator The locator used to identify the element to be double-clicked within the header frame.
     */
    @Then("^we double click on header frame (.*?) locator (.*?)$")
    public void weDoubleClickActionOnHeaderFrame(String element, String locator) {
        // Calls the dblclick method from frameCommonMethods to execute a double click action
        // on the specified element within the header frame, using the provided locator.
        frameCommonMethods.dblclick(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to enter a value into an input field
     * within the header frame.
     *
     * @param element Identifier for the input field where the value should be entered.
     * @param locator The locator used to find the input field within the header frame.
     * @param value The value to be entered into the input field.
     */
    @Then("^we enter value on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weEnterValueOnHeaderFrame(String element, String locator, String value) {
        // Calls the fill method from frameCommonMethods to enter the specified value
        // into the input field within the header frame, targeting the identified element using the locator.
        frameCommonMethods.fill(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to select a value from a dropdown or list
     * in the header frame.
     *
     * @param element Identifier for the dropdown or select element.
     * @param locator The locator used to find the select element within the header frame.
     * @param value The value that needs to be selected from the dropdown.
     */
    @Then("^we select on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weSelectValueOnHeaderFrame(String element, String locator, String value) {
        // Calls the select method from frameCommonMethods to select the specified value
        // from the dropdown within the header frame, identified by the given locator.
        frameCommonMethods.select(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to check a checkbox on a specific element
     * within the header frame.
     *
     * @param element Identifier for the checkbox element to be checked.
     * @param locator The locator used to find the checkbox within the header frame.
     */
    @Then("^we check on header frame (.*?) locator (.*?)$")
    public void weCheckActionOnHeaderFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to check the specified checkbox
        // located within the header frame using the provided locator.
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to uncheck a checkbox on a specific element
     * within the header frame.
     *
     * @param element Identifier for the checkbox element to be unchecked.
     * @param locator The locator used to find the checkbox within the header frame.
     */
    @Then("^we uncheck on header frame (.*?) locator (.*?)$")
    public void weUncheckActionOnHeaderFrame(String element, String locator) {
        // Calls the check method from frameCommonMethods to uncheck the specified checkbox
        // located within the header frame using the provided locator. This method may handle
        // the uncheck condition depending on its internal logic.
        frameCommonMethods.check(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to hover over a specific element
     * in the header frame.
     *
     * @param element Identifier for the element to hover over.
     * @param locator The locator used to find the hoverable element within the header frame.
     */
    @Then("^we hover on header frame (.*?) locator (.*?)$")
    public void weHoverActionOnHeaderFrame(String element, String locator) {
        // Calls the hover method from frameCommonMethods to trigger a hover action over the specified element
        // within the header frame, utilizing the locator for identification.
        frameCommonMethods.hover(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to type text into a specific input field
     * within the header frame.
     *
     * @param element Identifier for the input field where text should be typed.
     * @param locator The locator used to find the input field within the header frame.
     * @param value The text to be typed into the input field.
     */
    @Then("^we type on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weTypeValueOnHeaderFrame(String element, String locator, String value) {
        // Calls the type method from frameCommonMethods to input the specified text
        // into the input field located within the header frame, identified by the locator.
        frameCommonMethods.type(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to scroll to a specific element
     * within the header frame.
     *
     * @param element Identifier for the element to which the page should scroll.
     * @param locator The locator used to find the scrollable element within the header frame.
     */
    @Then("^we scroll on header frame (.*?) locator (.*?)$")
    public void weScrollToLocatorOnHeaderFrame(String element, String locator) {
        // Calls the scroll method from frameCommonMethods to scroll the view to the specified element
        // within the header frame, ensuring it is visible on the screen.
        frameCommonMethods.scroll(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to clear a value from an input field
     * in the header frame.
     *
     * @param element Identifier for the input field from which the value needs to be cleared.
     * @param locator The locator used to find the input field within the header frame.
     */
    @Then("^we clear value on header frame (.*?) locator (.*?) value \"(.*?)\"$")
    public void weClearValueOnHeaderFrame(String element, String locator) {
        // Calls the clear method from frameCommonMethods to remove any existing value from the input field
        // located within the header frame, identified by the locator.
        frameCommonMethods.clear(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element is visible
     * within the header frame.
     *
     * @param element Identifier for the element to check its visibility.
     * @param locator The locator used to identify the element within the header frame.
     */
    @Then("^we verify on header frame (.*?) of locator (.*?) is visible$")
    public void weVerifyOnHeaderFrameLocatorIsVisible(String element, String locator) {
        // Calls the isvisible method from frameCommonMethods to confirm whether the specified element
        // is currently visible in the header frame, utilizing the locator for identification.
        frameCommonMethods.isvisible(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a checkbox is checked
     * within the header frame.
     *
     * @param element Identifier for the checkbox to check its state.
     * @param locator The locator used to find the checkbox within the header frame.
     */
    @Then("^we verify on header frame (.*?) of locator (.*?) is checked$")
    public void weVerifyOnHeaderFrameLocatorIsChecked(String element, String locator) {
        // Calls the ischecked method from frameCommonMethods to check the status of the specified checkbox
        // within the header frame, using the locator for identification.
        frameCommonMethods.ischecked(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element is enabled
     * within the header frame.
     *
     * @param element Identifier for the element to check if it is enabled.
     * @param locator The locator used to find the element within the header frame.
     */
    @Then("^we verify on header frame (.*?) of locator (.*?) is enabled$")
    public void weVerifyOnHeaderFrameLocatorIsEnabled(String element, String locator) {
        // Calls the isenabled method from frameCommonMethods to validate if the specified element
        // is enabled and interactive in the header frame, utilizing the locator to pinpoint the element.
        frameCommonMethods.isenabled(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an element exists
     * within the header frame.
     *
     * @param element Identifier for the element to check existence.
     * @param locator The locator used to find the element within the header frame.
     */
    @Then("^we verify on header frame (.*?) of locator (.*?) is existed$")
    public void weVerifyOnHeaderFrameLocatorIsExisted(String element, String locator) {
        // Calls the exists method from frameCommonMethods to confirm whether the specified element
        // is present in the header frame, using the locator for identification.
        frameCommonMethods.exists(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if a specific element contains a certain value
     * within the header frame.
     *
     * @param element Identifier for the element to check.
     * @param locator The locator used to identify the element within the header frame.
     * @param value The expected value to be contained within the element.
     */
    @Then("^we contain on header frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weContainOnHeaderFrameLocatorValue(String element, String locator, String value) {
        // Calls the contain method from frameCommonMethods to check if the specified element
        // contains the expected value in its text or attributes, identified by the locator in the header frame.
        frameCommonMethods.contain(iframePage, null, element, locator, value);
    }

    /*
     * Represents a step to get the text content from a specific element
     * within the header frame.
     *
     * @param element Identifier for the element whose text is to be retrieved.
     * @param locator The locator used to find the element within the header frame.
     */
    @Then("^we get text on header frame(.*?) locator (.*?)$")
    public void weGetTextOnHeaderFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text content from the specified element
        // within the header frame, identified by the provided locator.
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to verify if an input field has a specific value
     * within the header frame.
     *
     * @param element Identifier for the input field to check its value.
     * @param locator The locator used to find the input field within the header frame.
     * @param value The expected value that the input field should contain.
     */
    @Then("^we has value on header frame (.*?) of locator (.*?) value \"(.*?)\"$")
    public void weHasValueOnHeaderFrameLocatorValue(String element, String locator, String value) {
        // Calls the hasvalue method from frameCommonMethods to verify if the specified input field contains
        // the expected value, identified by the given locator in the header frame.
        frameCommonMethods.hasvalue(iframePage, null, null, null, element, locator, value);
    }

    /*
     * Represents a step to get a list of elements based on a locator
     * within the header frame.
     *
     * @param element Identifier for the element to be checked.
     * @param locator The locator used to identify the list of elements within the header frame.
     */
    @Then("^we get list of elements on header frame (.*?) locator (.*?)$")
    public void weGetListOfElementsOnHeaderFrame(String element, String locator) {
        // Calls the gettext method from frameCommonMethods to retrieve the text or details of the specified list of elements
        // located in the header frame, using the locator for identification.
        frameCommonMethods.gettext(iframePage, null, null, null, element, locator);
    }

    /*
     * Represents a step to click a radio button from a list of options
     * within the header frame.
     *
     * @param element Identifier for the list of radio buttons.
     * @param locator The locator used to find the list of radio buttons within the header frame.
     */
    @When("we click radio on header frame (.*?) list locator (.*?)$")
    public void clickRadioOnHeaderFrame(String element, String locator) {
        // Calls clickRadioButton method from frameCommonMethods to click on the specified radio button
        // identified by the locator in the header frame.
        frameCommonMethods.clickRadioButton(iframePage, null, element, locator);
    }

    /*
     * Represents a step to capture a screenshot of a specific element
     * in the header frame.
     *
     * @param element Identifier for the element for which the screenshot will be taken.
     * @param locator The locator used to find the element within the header frame.
     * @param name The name used to save the screenshot file.
     */
    @And("^we capture screenshot on header frame (.*?) locator (.*?) name \"(.*?)\"$")
    public void weCaptureScreenshotOnHeaderFrame(String element, String locator, String name) {
        // Constructs the file path for the screenshot using the provided name
        // and calls the screenshot method from frameCommonMethods to capture and save the screenshot
        // of the specified element in the header frame, identified by the provided locator.
        String filePath = "test-output/screenshots/" + name + ".png";
        frameCommonMethods.screenshot(iframePage, null, null, null, element, locator, filePath);
    }

    /*
     * Represents a step to press a key on the keyboard within a specific context
     * (an element in the header frame).
     *
     * @param element Identifier for the element where the keyboard action will take place.
     * @param locator The locator that is used to find the element within the header frame.
     * @param value The key to be pressed, such as "Enter" or "Escape".
     */
    @And("^we press on header frame (.*?) locator (.*?) key \"(.*?)\" keyboard$")
    public void wePressOnHeaderFrameKey(String element, String locator, String value) {
        // Calls the press method from frameCommonMethods to trigger a key press on the specified element
        // within the context of the header frame, directing the action to the identified element using the locator.
        frameCommonMethods.press(iframePage, null, null, null, element, locator, value);
    }
}