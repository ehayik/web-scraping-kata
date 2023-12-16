package org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool;

import lombok.RequiredArgsConstructor;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * Manages a pool of WebDriver instances using Apache Commons Pool library.
 *
 * <p>
 * It provides methods to borrow and return WebDriver instances from the pool.
 *
 */
@RequiredArgsConstructor
public class PoolingWebDriverManager {

    private final Duration timeout;
    private final GenericObjectPool<WebDriver> webDriverPool;

    /**
     * Returns a PooledWebDriver instance.
     *
     * @return a PooledWebDriver instance.
     */
    public PooledWebDriver getPooledWebDriver() {
        try {
            return new PooledWebDriver(timeout, this);
        } catch (Exception ex) {
            throw new WebDriverPoolException("Could not borrow WebDriver from the pool", ex);
        }
    }

    /**
     * Returns a WebDriver instance from the webDriverPool.
     *
     * @return a WebDriver instance.
     */
    WebDriver borrowDriver() {
        try {
            return webDriverPool.borrowObject();
        } catch (Exception ex) {
            throw new WebDriverPoolException("Could not borrow WebDriver from the pool", ex);
        }
    }

    /**
     * Returns a WebDriver instance to the webDriverPool.
     *
     * @param driver the WebDriver instance to be returned to the pool.
     */
    void returnDriver(WebDriver driver) {
        if (driver != null) {
            webDriverPool.returnObject(driver);
        }
    }
}
