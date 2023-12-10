package org.github.ehayik.kata.webscraping.infrastructure;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("web-scraping")
public class WebScrapingProperties {

    private Map<String, PageConfig> page;

    public PageConfig getPage(String pageName) {
        return requireNonNull(page.get(pageName), "Configuration for page %s is not found".formatted(pageName));
    }
}
