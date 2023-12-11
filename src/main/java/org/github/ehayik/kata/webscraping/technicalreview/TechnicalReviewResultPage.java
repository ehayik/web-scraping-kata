package org.github.ehayik.kata.webscraping.technicalreview;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.commons.WebPageIllegalStateException;
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

    @FindBy(
            css =
                    "html > body > div:nth-of-type(2) > div > div:nth-of-type(6) > div > div:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(2) > i:nth-of-type(7)")
    private WebElement validTo;

    private final String licensePlate;

    public TechnicalReviewResultPage(String licensePlate, WebDriver webDriver) {
        this.licensePlate = licensePlate;
        PageFactory.initElements(webDriver, this);
    }

    public Optional<LocalDate> getValidityDate() {

        if (validDataFound.isDisplayed()) {
            log.info("{} validTo date is displayed.", licensePlate);
            return Optional.of(validTo.getText()).map(text -> LocalDate.parse(text, FORMATTER));
        }

        if (expiredDataFound.isDisplayed()) {
            log.info("{} expiredOn date is displayed.", licensePlate);
            return Optional.of(expiredOnDate.getText()).map(text -> LocalDate.parse(text, FORMATTER));
        }

        if (dataNotFound.isDisplayed()) {
            log.info("{} technical review data is not found. {}", licensePlate, dataNotFoundMessage.getText());
            return Optional.empty();
        }

        throw new WebPageIllegalStateException(
                "%s technical review data is not loaded. Please check whether form inputs valid."
                        .formatted(licensePlate));
    }
}
