package org.github.ehayik.kata.webscrapping.scrapemeshop;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Slf4j
public class ScrapemeShopPage implements AutoCloseable {

    private  static final String PAGE_TITLE = "Products – ScrapeMe";

    private final String url;
    private final WebDriver driver;

    @FindBy(css = "li.product")
    private List<WebElement> products;

    public ScrapemeShopPage(String url, @NonNull WebDriver driver) {
        this.url = url;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ScrapemeShopPage goTo() {
        driver.get(url);
        ensureThatPageIsLocated();
        ensureThatPageIsLoaded();
        return this;
    }

    private void ensureThatPageIsLocated() {

        if (!PAGE_TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not ScrapeMe Shop page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    private void ensureThatPageIsLoaded() {
        var wait = new WebDriverWait(driver, ofSeconds(5));
        wait.until(presenceOfElementLocated(By.cssSelector("ul.products")));
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
        driver.quit();
    }
}
