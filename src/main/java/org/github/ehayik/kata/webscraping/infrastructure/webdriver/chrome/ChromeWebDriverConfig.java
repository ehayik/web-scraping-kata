package org.github.ehayik.kata.webscraping.infrastructure.webdriver.chrome;

import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChromeWebDriverConfig {

    @Bean
    @ConditionalOnProperty(prefix = "web-driver", name = "browser", havingValue = "CHROME")
    WebDriverFactory webDriverFactory(WebDriverProperties properties) {
        var remote = properties.getRemote();

        if (remote.isEnabled()) {
            return new RemoteChromeWebDriverFactory(remote.getAddress(), remote.isTracingEnabled());
        }

        return new ChromeWebDriverFactory();
    }
}
