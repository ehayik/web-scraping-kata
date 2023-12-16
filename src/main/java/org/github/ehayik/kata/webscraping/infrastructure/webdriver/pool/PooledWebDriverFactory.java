package org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;

/**
 * Responsible for creating and managing a pool of WebDriver instances.
 * <p>
 * The PooledWebDriverFactory class has the following responsibilities:
 * <ul>
 *     <li>Creating a WebDriver instance using the delegate WebDriverFactory.</li>
 *     <li>Wrapping the created WebDriver instance in a PooledObject.</li>
 *     <li>Destroying a PooledObject by quitting the WebDriver instance.</li>
 * </ul>
 *
 * @apiNote It is recommended to use the PooledWebDriverFactory class with a PoolingWebDriverManager to create and
 * manage a pool of WebDriver instances.
 */
@Slf4j
@RequiredArgsConstructor
class PooledWebDriverFactory extends BasePooledObjectFactory<WebDriver> {

    private final WebDriverFactory delegate;

    @Override
    public WebDriver create() {
        return delegate.create();
    }

    @Override
    public PooledObject<WebDriver> wrap(WebDriver driver) {
        return new DefaultPooledObject<>(driver);
    }

    @Override
    public void destroyObject(PooledObject<WebDriver> p) {
        log.info(
                "Destroying WebDriver instance created for {} ms, active for {} ms. Current url {}",
                p.getFullDuration().toMillis(),
                p.getActiveDuration().toMillis(),
                p.getObject().getCurrentUrl());
        p.getObject().quit();
    }
}
