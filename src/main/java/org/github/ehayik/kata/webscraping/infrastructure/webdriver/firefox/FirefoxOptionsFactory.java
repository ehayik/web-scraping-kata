package org.github.ehayik.kata.webscraping.infrastructure.webdriver.firefox;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import org.openqa.selenium.firefox.FirefoxOptions;

@NoArgsConstructor(access = PRIVATE)
class FirefoxOptionsFactory {

    static FirefoxOptions create() {
        var options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1280,720");
        return options;
    }
}
