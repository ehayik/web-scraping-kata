package org.github.ehayik.kata.webscrapping.infrastructure.driverpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("web-driver.pool")
class WebDriverPoolProperties {

    private int maxTotal = 10;
    private int minIdle = 2;
    private int maxIdle = 4;
}
