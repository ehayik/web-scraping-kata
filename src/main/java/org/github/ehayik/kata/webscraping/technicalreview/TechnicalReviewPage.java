package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.Getter;
import org.github.ehayik.kata.webscraping.infrastructure.PageConfig;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PooledWebDriver;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PooledWebDriverPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

// page_url = https://rta.government.bg/services/check-inspection
public class TechnicalReviewPage extends PooledWebDriverPage {

    @FindBy(css = "div[class='langSwitch'] a")
    private List<WebElement> languages;

    @Getter
    private final TechnicalReviewForm form;

    public TechnicalReviewPage(PageConfig pageConfig, PooledWebDriver pooledWebDriver, TechnicalReviewForm form) {
        super(pageConfig, pooledWebDriver);
        this.form = form;
    }

    @Override
    public TechnicalReviewPage goTo() {
        return (TechnicalReviewPage) super.goTo();
    }

    @Override
    protected void waitUntilPageIsLoaded() {
        getPooledWebDriver().waitUntil(ofSeconds(5), presenceOfElementLocated(By.cssSelector(".submit")));
    }

    public TechnicalReviewPage switchLanguageToEnglish() {
        languages.stream()
                .filter(anchor -> "English".equals(anchor.getText()))
                .findFirst()
                .ifPresentOrElse(WebElement::click, () -> {
                    throw new IllegalStateException("Could not change language to English. WebElement is not found.");
                });
        return this;
    }



}