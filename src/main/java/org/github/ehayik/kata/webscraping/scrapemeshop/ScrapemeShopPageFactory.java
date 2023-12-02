package org.github.ehayik.kata.webscraping.scrapemeshop;

import lombok.RequiredArgsConstructor;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PoolingWebDriverManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScrapemeShopPageFactory {

    @Value("${web-scraping.scrapeme-shop.url}")
    private final String url;

    private final PoolingWebDriverManager driverPool;

    public ScrapemeShopPage create() {
        return new ScrapemeShopPage(url, driverPool.getPooledWebDriver());
    }
}
