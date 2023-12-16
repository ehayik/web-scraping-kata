package org.github.ehayik.kata.webscraping.infrastructure.webdriver.chrome;

import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * A factory class for creating Chrome{@link RemoteWebDriver} using
 * {@link  ChromeOptions}.
 *
 * @see WebDriverFactory
 */
@Slf4j
@RequiredArgsConstructor
class RemoteChromeWebDriverFactory implements WebDriverFactory {

    private final URL remoteAddress;
    private final boolean tracingEnabled;

    /**
     * Creates a new instance of the {@link RemoteWebDriver} connected to the specified remote address.
     *
     * <p>
     * The method initializes a new {@link ChromeOptions} object and configures it with the following arguments::
     *  <ul>
     *      <li>"--headless" - Runs the ChromeDriver in headless mode, which means it runs without a GUI.</li>
     *      <li>"--window-size=1920,1080" - set initial window size. Particularly relevant when running in headless mode.</li>
     *      <li>"--disable-gpu" - Disables hardware acceleration. This may improve overall execution speed for your headless tests</li>
     *      <li>"--no-sandbox" - Disables the sandbox mode in the ChromeDriver for security purposes.</li>
     *      <li>"--disable-dev-shm-usage" - Disables the use of /dev/shm for temporary files. It can help if you are running into memory issues.</li>
     *  </ul>
     *
     * @return a new instance of the RemoteWebDriver configured with the specified remote address and options
     */
    @Override
    public WebDriver create() {
        log.info("Creating new RemoteWebDriver instance with address {}.", remoteAddress);
        var options = ChromeOptionsFactory.create();
        return new RemoteWebDriver(remoteAddress, options, tracingEnabled);
    }
}
