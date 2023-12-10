package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import static lombok.AccessLevel.PROTECTED;

import lombok.Getter;
import lombok.NonNull;
import org.github.ehayik.kata.webscraping.infrastructure.PageConfig;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents a page object that uses a PooledWebDriver to interact with a web page.
 */
@Getter(PROTECTED)
public abstract class PooledWebDriverPage implements AutoCloseable {

    private final PageConfig pageConfig;
    private final PooledWebDriver pooledWebDriver;

    protected PooledWebDriverPage(@NonNull PageConfig pageConfig, @NonNull PooledWebDriver pooledWebDriver) {
        this.pageConfig = pageConfig;
        this.pooledWebDriver = pooledWebDriver;
        PageFactory.initElements(pooledWebDriver.unwrap(), this);
    }

    /**
     * Navigates to the URL specified in the page configuration and waits until the page is loaded.
     *
     * @return the PooledWebDriverPage object after navigating to the URL
     * @throws IllegalStateException if the current page does not have the expected title
     */
    public PooledWebDriverPage goTo() {
        pooledWebDriver.get(pageConfig.url());
        ensureThatPageIsLocated();
        waitUntilPageIsLoaded();
        return this;
    }

    private void ensureThatPageIsLocated() {

        if (!pageConfig.hasTitle(pooledWebDriver.getTitle())) {
            throw new IllegalStateException(
                    "This is not ScrapeMe Shop page," + " current page is: " + pooledWebDriver.getCurrentUrl());
        }
    }

    /**
     * Waits until the page is loaded.
     * @apiNote This abstract method is designed to be implemented by sub-classes to provide custom waiting behavior
     * for determining if the page is loaded. For example, sub-classes can implement this method to wait until a
     * specific element is visible on the page, or until a certain condition is met.
     */
    protected abstract void waitUntilPageIsLoaded();

    @Override
    public void close() {
        pooledWebDriver.close();
    }
}
