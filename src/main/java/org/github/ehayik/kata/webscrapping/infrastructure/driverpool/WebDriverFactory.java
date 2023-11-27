package org.github.ehayik.kata.webscrapping.infrastructure.driverpool;

import org.openqa.selenium.WebDriver;


/**
 * Interface representing a factory for creating instances of WebDriver.
 */
interface WebDriverFactory {

    /**
     * Creates and returns a new instance of WebDriver.
     *
     * @return A WebDriver object representing the newly created instance.
     */
    WebDriver create();
}
