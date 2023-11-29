package org.github.ehayik.kata.webscrapping.infrastructure.driverpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URL;

@Data
@ConfigurationProperties("web-driver")
public class WebDriverProperties {

    private boolean remoteEnabled;
    private URL remoteAddress;
}
