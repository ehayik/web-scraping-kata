package org.github.ehayik.kata.webscrapping.infrastructure.driverpool;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * A factory class for creating FirefoxWebDriver instances.
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
     *      <ul>"--window-size=1920,1080" - set initial window size. Particularly relevant when running in headless mode.</ul>
     *      <ul>"--disable-gpu" - Disables hardware acceleration. This may improve overall execution speed for your headless tests</ul>
     *      <ul>"--no-sandbox" - Disables the sandbox mode in the FirefoxDriver for security purposes.</ul>
     *  </li>
     *
     * @return a WebDriver object representing the newly created FirefoxDriver instance.
     */
    @Override
    public WebDriver create() {
        log.info("Creating new FirefoxDriver instance.");
        var options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        return new FirefoxDriver(options);
    }
}

