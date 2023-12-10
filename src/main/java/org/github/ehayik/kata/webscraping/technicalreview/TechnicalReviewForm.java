package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class TechnicalReviewForm {

    @FindBy(css = "input[placeholder='License plate']")
    private WebElement licensePlateInput;

    @FindBy(css = "input[placeholder='Security code']")
    private WebElement securityCodeInput;

    @FindBy(css = "a[class='submit']")
    private WebElement submitButton;

    private final WebDriver webDriver;
    private final CaptchaWidget captchaWidget;

    public TechnicalReviewForm(@NonNull WebDriver webDriver, @NonNull CaptchaWidget captchaWidget) {
        this.webDriver = webDriver;
        this.captchaWidget = captchaWidget;
        PageFactory.initElements(webDriver, this);
    }

    public TechnicalReviewForm enterLicensePlate(String licensePlate) {
        log.info("Entering license plate {}", licensePlate);
        licensePlateInput.sendKeys(licensePlate);
        return this;
    }

    public TechnicalReviewForm enterSecurityCode() {
        var securityCode = captchaWidget.getSecurityCode();
        log.info("Entering security code {}.", securityCode);
        securityCodeInput.sendKeys(securityCode);
        return this;
    }

    public TechnicalReviewResultPage submit() {
        log.info("Submitting form");
        submitButton.click();
        return new TechnicalReviewResultPage(webDriver);
    }
}
