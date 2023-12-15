package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.commons.WebPageIllegalStateException;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PooledWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
public class TechnicalReviewWebForm {

    @FindBy(css = "input[placeholder='License plate']")
    private WebElement licensePlateInput;

    @FindBy(css = "input[placeholder='Security code']")
    private WebElement securityCodeInput;

    @FindBy(css = "a[class='submit']")
    private WebElement submitButton;

    private String licensePlate;
    private String securityCode;

    private final PooledWebDriver pooledWebDriver;
    private final CaptchaWidget captchaWidget;

    public TechnicalReviewWebForm(PooledWebDriver pooledWebDriver, CaptchaWidget captchaWidget) {
        this.pooledWebDriver = pooledWebDriver;
        this.captchaWidget = captchaWidget;
        PageFactory.initElements(pooledWebDriver.unwrap(), this);
    }

    public TechnicalReviewWebForm enterLicensePlate(String licensePlate) {
        log.info("Entering license plate {}", licensePlate);
        licensePlateInput.sendKeys(licensePlate);
        this.licensePlate = licensePlate;
        return this;
    }

    public TechnicalReviewWebForm enterSecurityCode() {
        securityCode = captchaWidget.getSecurityCode();

        if (isBlank(securityCode) || securityCode.length() < 4) {
            throw new WebPageIllegalStateException("Security code '%s' is not valid.".formatted(securityCode));
        }

        log.info("Entering security code {}.", securityCode);
        securityCodeInput.sendKeys(securityCode);
        return this;
    }

    public TechnicalReviewResultPage submit() {

        if (isBlank(licensePlate)) {
            throw new IllegalArgumentException("License plate cannot be null or blank");
        }

        if (isBlank(securityCode)) {
            throw new IllegalArgumentException("Security code cannot be null or blank");
        }

        log.info(
                "Submitting technical review web form, licence plate: {}, security code: {}.",
                licensePlate,
                securityCode);
        submitButton.click();

        return new TechnicalReviewResultPage(licensePlate, pooledWebDriver);
    }
}
