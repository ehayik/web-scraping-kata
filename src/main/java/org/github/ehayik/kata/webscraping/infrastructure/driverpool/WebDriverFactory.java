package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    /**
     * Creates and returns a new instance of RemoteWebDriver.
     *
     * @param remoteAddress the URL of the remote address to connect to
     * @return A RemoteWebDriver object representing the newly created instance.
     */
    RemoteWebDriver create(URL remoteAddress);
}
