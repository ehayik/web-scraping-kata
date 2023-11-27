package org.github.ehayik.kata.webscrapping.scrapemeshop;

import lombok.RequiredArgsConstructor;
import org.github.ehayik.kata.webscrapping.infrastructure.driverpool.PoolingWebDriverManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScrapemeShopPageFactory {

    @Value("${web-scrapping.scrapeme-shop.url}")
    private final String url;

    private final PoolingWebDriverManager driverPool;

    public ScrapemeShopPage create() {
        return new ScrapemeShopPage(url, driverPool.getPooledWebDriver());
    }
}
