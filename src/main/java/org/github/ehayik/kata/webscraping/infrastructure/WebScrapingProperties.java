package org.github.ehayik.kata.webscraping.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

import static java.util.Objects.requireNonNull;

@Data
@ConfigurationProperties("web-scraping")
public class WebScrapingProperties {

    private Map<String, PageConfig> page;

    public PageConfig getPage(String pageName) {
        return requireNonNull(page.get(pageName), "Configuration for page %s is not found".formatted(pageName));
    }

}
