package org.github.ehayik.kata.webscraping.infrastructure.webdriver.chrome;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import org.openqa.selenium.chrome.ChromeOptions;

@NoArgsConstructor(access = PRIVATE)
class ChromeOptionsFactory {

    static ChromeOptions create() {
        var options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1280,720");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }
}
