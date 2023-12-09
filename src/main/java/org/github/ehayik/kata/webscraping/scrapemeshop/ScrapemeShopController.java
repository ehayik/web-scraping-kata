package org.github.ehayik.kata.webscraping.scrapemeshop;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
class ScrapemeShopController {

    private final ScrapemeShopPageFactory scrapemeShopPageFactory;

    @GetMapping("/scrapeme-shop/products")
    List<Product> getProducts() {
        var stopwatch = Stopwatch.createStarted();

        try (var page = scrapemeShopPageFactory.create()) {
            var products =  page.goTo().getProducts();
            log.info("Scrapeme Shop scrapped successfully after {}s. Products found {}", stopwatch.elapsed().toSeconds(), products.size());
            return products;
        } catch (Exception ex) {
            log.error("Scrapeme Shop scraping failed after {}s.", stopwatch.elapsed().toSeconds(), ex);
            throw ex;
        }
    }
}
