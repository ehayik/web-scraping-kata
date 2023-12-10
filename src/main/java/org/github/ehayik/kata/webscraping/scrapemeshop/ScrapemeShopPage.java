package org.github.ehayik.kata.webscraping.scrapemeshop;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;
import org.github.ehayik.kata.webscraping.infrastructure.PageConfig;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PooledWebDriver;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PooledWebDriverPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// page_url = https://scrapeme.live/shop/
public class ScrapemeShopPage extends PooledWebDriverPage {

    @FindBy(css = "li.product")
    private List<WebElement> products;

    public ScrapemeShopPage(PageConfig pageConfig, PooledWebDriver pooledWebDriver) {
        super(pageConfig, pooledWebDriver);
    }

    @Override
    public ScrapemeShopPage goTo() {
        return (ScrapemeShopPage) super.goTo();
    }

    @Override
    protected void waitUntilPageIsLoaded() {
        getPooledWebDriver().waitUntil(ofSeconds(5), presenceOfElementLocated(By.cssSelector("ul.products")));
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
}
