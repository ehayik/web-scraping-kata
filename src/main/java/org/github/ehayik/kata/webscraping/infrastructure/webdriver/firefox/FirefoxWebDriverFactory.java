package org.github.ehayik.kata.webscraping.infrastructure.webdriver.firefox;

import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * A factory class for creating {@link FirefoxDriver} instances using
 * {@link FirefoxOptions}.
 *
 * @see WebDriverFactory
 */
@Slf4j
class FirefoxWebDriverFactory implements WebDriverFactory {

    /**
     * Creates a new instance of FirefoxDriver.
     *
     * <p>
     * The method initializes a new FirefoxOptions object and configures it with the following arguments:
     *  <li>
     *      <ul>"--headless" - Runs the FirefoxDriver in headless mode, which means it runs without a GUI.</ul>
     *      <ul>"--window-size=1280,720" - set initial window size. Particularly relevant when running in headless mode.</ul>
     *  </li>
     *
     * @return a WebDriver object representing the newly created FirefoxDriver instance.
     */
    @Override
    public WebDriver create() {
        log.info("Creating new FirefoxDriver instance.");
        var options = FirefoxOptionsFactory.create();
        return new FirefoxDriver(options);
    }
}
