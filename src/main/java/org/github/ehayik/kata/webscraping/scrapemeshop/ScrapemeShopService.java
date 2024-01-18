package org.github.ehayik.kata.webscraping.scrapemeshop;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapemeShopService {

    private final ScrapemeShopPageFactory scrapemeShopPageFactory;

    public List<Product> getProducts() {
        var stopwatch = Stopwatch.createStarted();

        try (var page = scrapemeShopPageFactory.create()) {
            var products = page.goTo().getProducts();
            log.info(
                    "Scrapeme Shop scrapped successfully after {}s. Products found {}",
                    stopwatch.elapsed().toSeconds(),
                    products.size());
            return products;
        } catch (Exception ex) {
            log.error(
                    "Scrapeme Shop scraping failed after {}s.",
                    stopwatch.elapsed().toSeconds(),
                    ex);
            throw ex;
        }
    }
}
