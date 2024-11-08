package com.ptaf.helpers;

import com.ptaf.utils.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementLocatorHelper {

    private static final Logger logger = LoggerFactory.getLogger(ElementLocatorHelper.class);

    /**
     * Get the element locator value from the YAML configuration based on the element name and key.
     *
     * @param element The element name.
     * @param key The key to retrieve the locator.
     * @return The locator value.
     */
    public String getElement(String element, String key) {
        try {
            return (String) YamlReader.get("elements." + element + "." + key);
        } catch (Exception e) {
            logger.error("Failed to retrieve selector for element '{}'", element + key, e);
            throw e;
        }
    }

    /**
     * Extracts the locator type (e.g., XPATH, CSS) from the locator value.
     *
     * @param locatorValue The full locator value in the format "LOCATOR_TYPE_LOCATOR".
     * @return The locator type.
     */
    public String getLocatorType(String locatorValue) {
        return locatorValue.split("_")[0];  // Extract the locator type (e.g., XPATH, CSS)
    }

    /**
     * Extracts the actual locator from the locator value.
     *
     * @param locatorValue The full locator value in the format "LOCATOR_TYPE_LOCATOR".
     * @return The locator string.
     */
    public String getLocator(String locatorValue) {
        return locatorValue.split("_")[1];  // Extract the actual locator value
    }
}
