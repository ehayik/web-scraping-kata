package org.github.ehayik.kata.webscraping.infrastructure.webdriver.firefox;

import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * A factory class for creating Firefox{@link RemoteWebDriver} instances using
 * {@link FirefoxOptions}.
 *
 * @see WebDriverFactory
 */
@Slf4j
@RequiredArgsConstructor
public class RemoteFirefoxWebDriverFactory implements WebDriverFactory {

    private final URL remoteAddress;
    private final boolean tracingEnabled;

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
     * @return a RemoteWebDriver object representing the newly created RemoteWebDriver instance.
     */
    @Override
    public WebDriver create() {
        log.info("Creating new RemoteWebDriver instance with address {}.", remoteAddress);
        var options = FirefoxOptionsFactory.create();
        return new RemoteWebDriver(remoteAddress, options, tracingEnabled);
    }
}
