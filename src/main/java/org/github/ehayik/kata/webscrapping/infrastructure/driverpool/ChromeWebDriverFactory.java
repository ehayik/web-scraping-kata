package org.github.ehayik.kata.webscrapping.infrastructure.driverpool;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * A factory class for creating ChromeWebDriver instances.
 *
 * <p>
 * It uses the Selenium WebDriverManager to setup and manage the Chrome WebDriver,
 * since it meant to be used in standalone mode
 *
 * @see WebDriverFactory
 */
@Slf4j
class ChromeWebDriverFactory implements WebDriverFactory {

    /**
     * Sets up the Chrome WebDriver for automated management.
     *
     * @apiNote The WebDriverManager library is used to automatically download and set up the appropriate WebDriver executable.
     * This can save time and effort in managing WebDriver versions and dependencies.This method assumes that the WebDriverManager
     * library is already configured and available as a dependency in the project.
     *
     * @throws WebDriverManagerException if there is an error while setting up the Chrome WebDriver
     */
    @PostConstruct
    void setupWebDriver() {
        log.debug("Initializing Chrome WebDriver automated management.");
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Creates a new instance of the ChromeDriver.
     *
     * <p>
     * The method initializes a new ChromeOptions object and configures it with the following arguments:
     *  <ul>
     *      <li>"--headless" - Runs the ChromeDriver in headless mode, which means it runs without a GUI.</li>
     *      <li>"--window-size=1920,1080" - set initial window size. Particularly relevant when running in headless mode.</li>
     *      <li>"--disable-gpu" - Disables hardware acceleration. This may improve overall execution speed for your headless tests</li>
     *      <li>"--no-sandbox" - Disables the sandbox mode in the ChromeDriver for security purposes.</li>
     *      <li>"--disable-dev-shm-usage" - Disables the use of /dev/shm for temporary files. It can help if you are running into memory issues.</li>
     *  </ul>
     *
     * @return a new instance of the ChromeDriver configured with the specified options
     */
    @Override
    public WebDriver create() {
        log.info("Creating new ChromeDriver instance.");
        var options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}
