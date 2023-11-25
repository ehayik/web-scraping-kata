package org.github.ehayik.kata.webscrapping.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class ChromeWebDriverFactory implements WebDriverFactory {

    @PostConstruct
    void setupWebDriver() {
        log.debug("Initializing WebDriver automated management.");
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public WebDriver create() {
        var options = new ChromeOptions();
        options.addArguments("--headless");

        var driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        return driver;
    }
}
