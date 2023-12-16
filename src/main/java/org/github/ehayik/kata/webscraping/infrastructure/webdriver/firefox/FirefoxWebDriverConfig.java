package org.github.ehayik.kata.webscraping.infrastructure.webdriver.firefox;

import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FirefoxWebDriverConfig {

    @Bean
    @ConditionalOnProperty(prefix = "web-driver", name = "browser", havingValue = "FIREFOX", matchIfMissing = true)
    WebDriverFactory webDriverFactory(WebDriverProperties properties) {
        var remote = properties.getRemote();

        if (remote.isEnabled()) {
            return new RemoteFirefoxWebDriverFactory(remote.getAddress(), remote.isTracingEnabled());
        }

        return new FirefoxWebDriverFactory();
    }
}
