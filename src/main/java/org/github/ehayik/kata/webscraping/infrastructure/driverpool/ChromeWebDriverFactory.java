package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * A factory class for creating {@link ChromeDriver} and {@link RemoteWebDriver} instances using
 * {@link  ChromeOptions}.
 *
 * @see WebDriverFactory
 */
@Slf4j
class ChromeWebDriverFactory implements WebDriverFactory {

    /**
     * Creates a new instance of the {@link ChromeDriver}.
     *
     * <p>
     * The method initializes a new {@link ChromeOptions} object and configures it with the following arguments:
     *  <ul>
     *      <li>"--headless" - Runs the ChromeDriver in headless mode, which means it runs without a GUI.</li>
     *      <li>"--window-size=1280,720" - set initial window size. Particularly relevant when running in headless mode.</li>
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
        return new ChromeDriver(withOptions());
    }

    private static ChromeOptions withOptions() {
        var options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1280,720");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

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
     * @param remoteAddress the URL of the remote address to connect to
     * @return a new instance of the RemoteWebDriver configured with the specified remote address and options
     */
    @Override
    public RemoteWebDriver create(@NonNull URL remoteAddress) {
        log.info("Creating new RemoteWebDriver instance with address {}.", remoteAddress);
        return new RemoteWebDriver(remoteAddress, withOptions(), true);
    }
}
