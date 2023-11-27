package org.github.ehayik.kata.webscrapping.scrapemeshop;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscrapping.infrastructure.driverpool.PooledWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

// page_url = https://scrapeme.live/shop/
@Slf4j
public class ScrapemeShopPage implements AutoCloseable {

    private static final String PAGE_TITLE = "Products – ScrapeMe";

    private final String url;

    private final PooledWebDriver pooledWebDriver;

    @FindBy(css = "li.product")
    private List<WebElement> products;

    public ScrapemeShopPage(String url, @NonNull PooledWebDriver pooledWebDriver) {
        this.url = url;
        this.pooledWebDriver = pooledWebDriver;
        PageFactory.initElements(pooledWebDriver.unwrap(), this);
    }

    public ScrapemeShopPage goTo() {
        pooledWebDriver.get(url);
        ensureThatPageIsLocated();
        waitUntilPageIsLoaded();
        return this;
    }

    private void ensureThatPageIsLocated() {

        if (!PAGE_TITLE.equals(pooledWebDriver.getTitle())) {
            throw new IllegalStateException(
                    "This is not ScrapeMe Shop page," + " current page is: " + pooledWebDriver.getCurrentUrl());
        }
    }

    private void waitUntilPageIsLoaded() {
        pooledWebDriver.waitUntil(ofSeconds(5), presenceOfElementLocated(By.cssSelector("ul.products")));
    }

    public List<Product> getProducts() {
        return products.stream().map(this::toProduct).toList();
    }

    private Product toProduct(WebElement source) {
        return Product.builder()
                .url(source.findElement(By.tagName("a")).getAttribute("href"))
                .image(source.findElement(By.tagName("img")).getAttribute("src"))
                .name(source.findElement(By.tagName("h2")).getText())
                .price(source.findElement(By.tagName("span")).getText())
                .build();
    }

    @Override
    public void close() {
        pooledWebDriver.close();
    }
}
