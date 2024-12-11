package com.ptaf.helpers;

import com.ptaf.utils.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ElementLocatorHelper is a utility class responsible for retrieving element locators from a
 * YAML configuration file based on specified element names and keys. It also provides methods
 * for extracting locator types and actual locator strings from a formatted locator value.
 *
 * Usage:
 * - Use the getElement method to retrieve a locator value for a specific element name and key.
 * - Use the getLocatorType and getLocator methods to parse and retrieve specific parts
 *   of a locator string.
 */
public class ElementLocatorHelper {

    private static final Logger logger = LoggerFactory.getLogger(ElementLocatorHelper.class);

    /**
     * Get the element locator value from the YAML configuration based on the element name and key.
     *
     * @param element The element name for which the locator is to be retrieved.
     * @param key     The key used to retrieve the specific locator for the element.
     * @return The locator value associated with the specified element and key.
     */
    public String getElement(String element, String key) {
        try {
            // Retrieve the locator from the YAML configuration using the specified element and key
            return (String) YamlReader.get("elements." + element + "." + key);
        } catch (Exception e) {
            // Log the error and rethrow the exception if the retrieval fails
            logger.error("Failed to retrieve selector for element '{}'", element + key, e);
            throw e;
        }
    }

    /**
     * Extracts the locator type (e.g., XPATH, CSS) from the locator value.
     *
     * @param locatorValue The full locator value formatted as "LOCATOR_TYPE_LOCATOR".
     * @return The locator type extracted from the locator value.
     */

    public String getLocatorType(String locatorValue) {
        // Extract the locator type by splitting the locatorValue at the underscore
        return locatorValue.split("_")[0]; // Example: from "CSS_button" returns "CSS"
    }

    /**
     * Extracts the actual locator from the locator value.
     *
     * @param locatorValue The full locator value formatted as "LOCATOR_TYPE_LOCATOR".
     * @return The locator string, which is the part following the locator type.
     */
    public String getLocator(String locatorValue) {
        // Extract the actual locator by splitting the locatorValue at the underscore
        return locatorValue.split("_")[1]; // Example: from "CSS_button" returns "button"
    }
}