package org.github.ehayik.kata.webscraping.infrastructure.webdriver;

import static org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverProperties.Browser.CHROME;

import java.net.URL;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("web-driver")
public class WebDriverProperties {

    private Browser browser = CHROME;
    private Remote remote = new Remote();

    /**
     * The timeout duration for waiting operations.
     */
    private Duration timeout;

    public enum Browser {
        CHROME,
        FIREFOX
    }

    @Data
    public static class Remote {

        private boolean enabled;
        private URL address;
        private boolean tracingEnabled;
    }
}
