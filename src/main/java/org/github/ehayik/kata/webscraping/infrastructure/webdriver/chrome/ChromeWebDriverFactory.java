package org.github.ehayik.kata.webscraping.infrastructure.webdriver.chrome;

import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * A factory class for creating {@link ChromeDriver} using
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
        var options = ChromeOptionsFactory.create();
        return new ChromeDriver(options);
    }
}
