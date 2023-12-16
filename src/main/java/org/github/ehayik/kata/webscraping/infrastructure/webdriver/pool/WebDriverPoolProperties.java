package org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for configuring the WebDriver pool.
 *
 * <p>
 * It is annotated with @Data and @ConfigurationProperties to enable automatic
 * mapping of properties from the application configuration file.
 */
@Data
@ConfigurationProperties("web-driver.pool")
class WebDriverPoolProperties {

    private int maxTotal = 10;
    private int minIdle = 2;
    private int maxIdle = 4;
}
