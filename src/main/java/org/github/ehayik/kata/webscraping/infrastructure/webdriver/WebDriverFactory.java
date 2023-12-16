package org.github.ehayik.kata.webscraping.infrastructure.webdriver;

import org.openqa.selenium.WebDriver;

/**
 * Interface representing a factory for creating instances of WebDriver.
 */
public interface WebDriverFactory {

    /**
     * Creates and returns a new instance of WebDriver.
     *
     * @return A WebDriver object representing the newly created instance.
     */
    WebDriver create();
}
