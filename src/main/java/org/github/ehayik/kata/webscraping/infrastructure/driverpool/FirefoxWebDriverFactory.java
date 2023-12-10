package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import java.net.URL;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * A factory class for creating {@link FirefoxDriver} and {@link RemoteWebDriver} instances using
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
        return new FirefoxDriver(withOptions());
    }

    private static FirefoxOptions withOptions() {
        var options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1280,720");
        return options;
    }

    /**
     * Creates a new instance of {@link RemoteWebDriver} connected to the specified remote address.
     *
     * <p>
     * The method initializes a new {@link FirefoxOptions} object and configures it with the following arguments:
     *  <li>
     *      <ul>"--headless" - Runs the FirefoxDriver in headless mode, which means it runs without a GUI.</ul>
     *      <ul>"--window-size=1280,720" - set initial window size. Particularly relevant when running in headless mode.</ul>
     *  </li>
     *
     * @param remoteAddress the URL of the remote address to connect to
     * @return a RemoteWebDriver object representing the newly created RemoteWebDriver instance.
     */
    @Override
    public RemoteWebDriver create(@NonNull URL remoteAddress) {
        log.info("Creating new RemoteWebDriver instance with address {}.", remoteAddress);
        return new RemoteWebDriver(remoteAddress, withOptions(), true);
    }
}
