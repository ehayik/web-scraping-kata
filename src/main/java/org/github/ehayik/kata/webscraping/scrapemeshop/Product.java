package org.github.ehayik.kata.webscraping.scrapemeshop;

import lombok.Builder;

@Builder
public record Product(String url, String image, String name, String price) {}
