package org.github.ehayik.kata.webscrapping.scrapemeshop;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrapeme-shop/products")
class ScrapemeShopController {

    private final ScrapemeShopPageFactory scrapemeShopPageFactory;

    @GetMapping
    List<Product> getProducts() {
        try (var page = scrapemeShopPageFactory.create()) {
            return page.goTo().getProducts();
        }
    }
}
