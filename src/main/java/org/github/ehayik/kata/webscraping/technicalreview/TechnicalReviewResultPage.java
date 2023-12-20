package org.github.ehayik.kata.webscraping.technicalreview;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.commons.WebPageIllegalStateException;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool.PooledWebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class TechnicalReviewResultPage {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FindBy(css = "div[class='resultYes']")
    private WebElement validDataFound;

    @FindBy(css = "div[data-bind='visible: rvIdentNumber().length != 0 && !isValid()']")
    private WebElement expiredDataFound;

    @FindBy(
            css =
                    "html > body > div:nth-of-type(2) > div > div:nth-of-type(6) > div > div:nth-of-type(2) > div:nth-of-type(2) > div:nth-of-type(2) > i:nth-of-type(7)")
    private WebElement expiredOnDate;

    @FindBy(
            css =
                    "html > body > div:nth-of-type(2) > div > div:nth-of-type(6) > div > div:nth-of-type(2) > div:nth-of-type(3)")
    private WebElement dataNotFound;

    @FindBy(css = "p[data-bind*='no']")
    private WebElement dataNotFoundMessage;

    private final PooledWebDriver pooledWebDriver;

    @FindBy(
            css =
                    "html > body > div:nth-of-type(2) > div > div:nth-of-type(6) > div > div:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(2) > i:nth-of-type(7)")
    private WebElement validTo;

    private final String licensePlate;

    public TechnicalReviewResultPage(String licensePlate, PooledWebDriver pooledWebDriver) {
        this.licensePlate = licensePlate;
        this.pooledWebDriver = pooledWebDriver;
        PageFactory.initElements(pooledWebDriver.unwrap(), this);
    }

    public Optional<LocalDate> getValidityDate() {
        waitUntilPageIsDisplayed();

        if (validDataFound.isDisplayed()) {
            log.info("{} validTo date is displayed.", licensePlate);
            return Optional.of(getDate(validTo));
        }

        if (expiredDataFound.isDisplayed()) {
            log.info("{} expiredOn date is displayed.", licensePlate);
            return Optional.of(getDate(expiredOnDate));
        }

        log.info("{} technical review data is not found. {}", licensePlate, dataNotFoundMessage.getText());
        return Optional.empty();
    }

    private static LocalDate getDate(WebElement element) {
        return LocalDate.parse(element.getText(), FORMATTER);
    }

    private void waitUntilPageIsDisplayed() {
        try {
            Function<WebDriver, Boolean> isTrue =
                    x -> validDataFound.isDisplayed() || expiredDataFound.isDisplayed() || dataNotFound.isDisplayed();
            pooledWebDriver.waitUntil(isTrue);
        } catch (TimeoutException ex) {
            throw new WebPageIllegalStateException(
                    "%s technical review data is not loaded. Please check whether form inputs valid."
                            .formatted(licensePlate),
                    ex);
        }
    }
}
