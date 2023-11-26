package org.github.ehayik.kata.webscrapping.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("standalone")
public class ChromeWebDriverFactory implements WebDriverFactory {

    @PostConstruct
    void setupWebDriver() {
        log.debug("Initializing Chrome WebDriver automated management.");
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public WebDriver create() {
        var options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}
