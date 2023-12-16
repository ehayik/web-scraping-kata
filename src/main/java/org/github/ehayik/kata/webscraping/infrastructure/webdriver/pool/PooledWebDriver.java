package org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * Represents a Pooled WebDriver instance.
 *
 * <p>
 * This class provides a wrapper around a WebDriver instance that is borrowed from a WebDriverPool.
 *
 * @apiNote It implements the AutoCloseable interface to facilitate the automatic return of the WebDriver instance
 * to the pool when it is closed.
 */
@Slf4j
@RequiredArgsConstructor
public class PooledWebDriver implements AutoCloseable {

    private final Duration timeout;
    private final WebDriver delegate;
    private final PoolingWebDriverManager driverPool;

    /**
     * Constructs a new PooledWebDriver object with the specified timeout and driver pool.
     *
     * @param timeout the timeout duration for waiting operations
     * @param driverPool the pooling web driver manager
     */
    PooledWebDriver(Duration timeout, PoolingWebDriverManager driverPool) {
        this(timeout, driverPool.borrowDriver(), driverPool);
    }

    /**
     * Returns the underlying {@code WebDriver} instance of this {@code PooledWebDriver} object.
     *
     * @apiNote This method allows access to the underlying {@code WebDriver} instance for performing advanced operations,
     * such as using driver-specific functionality that is not provided by the library.
     *
     * @return the underlying {@code WebDriver} instance
     */
    public WebDriver unwrap() {
        return delegate;
    }

    public String getTitle() {
        return delegate.getTitle();
    }

    public String getCurrentUrl() {
        return delegate.getCurrentUrl();
    }

    public void get(String url) {
        delegate.get(url);
    }

    public <V> V waitUntil(Function<WebDriver, V> isTrue) {
        return new WebDriverWait(delegate, timeout).until(isTrue);
    }

    /**
     * Closes the resource and returns the driver to the driver pool.
     *
     * @apiNote This method is called to release any resources held by the object and
     * to return the driver back to the driver pool.
     */
    @Override
    public void close() {
        try {
            delegate.manage().deleteAllCookies();
            driverPool.returnDriver(delegate);
        } catch (Exception ex) {
            log.error("Could not return driver to the pool.", ex);
        }
    }
}
