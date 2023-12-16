package org.github.ehayik.kata.webscraping.scrapemeshop;

import lombok.RequiredArgsConstructor;
import org.github.ehayik.kata.webscraping.infrastructure.WebScrapingProperties;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool.PoolingWebDriverManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScrapemeShopPageFactory {

    private final PoolingWebDriverManager driverPool;
    private final WebScrapingProperties webScrapingProperties;

    public ScrapemeShopPage create() {
        var pageConfig = webScrapingProperties.getPage("scrapeme-shop");
        return new ScrapemeShopPage(pageConfig, driverPool.getPooledWebDriver());
    }
}
