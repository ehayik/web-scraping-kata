package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URL;

import static org.github.ehayik.kata.webscraping.infrastructure.driverpool.WebDriverProperties.Browser.CHROME;

@Data
@ConfigurationProperties("web-driver")
public class WebDriverProperties {

    private Browser browser = CHROME;
    private boolean remoteEnabled;
    private URL remoteAddress;

    public enum Browser {
        CHROME, FIREFOX
    }
}
