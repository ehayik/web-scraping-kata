package org.github.ehayik.kata.webscraping.scrapemeshop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
class ScrapemeShopController {

    private final ScrapemeShopService scrapemeShopService;

    @GetMapping("/scrapeme-shop/products")
    List<Product> getProducts() {
        return scrapemeShopService.getProducts();
    }
}
