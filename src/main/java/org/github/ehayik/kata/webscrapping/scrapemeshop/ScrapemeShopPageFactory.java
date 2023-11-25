package org.github.ehayik.kata.webscrapping.scrapemeshop;

import lombok.RequiredArgsConstructor;
import org.github.ehayik.kata.webscrapping.driver.WebDriverFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScrapemeShopPageFactory {

    @Value("${web-scrapping.scrapeme-shop.url}")
    private final String url;

    private final WebDriverFactory webDriverFactory;

    public ScrapemeShopPage create() {
        return new ScrapemeShopPage(url, webDriverFactory.create());
    }
}
