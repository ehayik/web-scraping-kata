package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


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
