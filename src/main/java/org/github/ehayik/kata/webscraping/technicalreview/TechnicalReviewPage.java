package org.github.ehayik.kata.webscraping.technicalreview;

import org.github.ehayik.kata.webscraping.commons.PageConfig;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool.PooledWebDriver;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool.PooledWebDriverPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

// page_url = https://rta.government.bg/services/check-inspection
public class TechnicalReviewPage extends PooledWebDriverPage {

    @FindBy(css = "div[class='langSwitch'] a")
    private List<WebElement> languages;

    private final TechnicalReviewWebForm webForm;

    public TechnicalReviewPage(PageConfig pageConfig, PooledWebDriver pooledWebDriver, TechnicalReviewWebForm webForm) {
        super(pageConfig, pooledWebDriver);
        this.webForm = webForm;
    }

    @Override
    public TechnicalReviewPage goTo() {
        return (TechnicalReviewPage) super.goTo();
    }

    @Override
    protected void waitUntilPageIsLoaded() {
        getPooledWebDriver().waitUntil(presenceOfElementLocated(By.cssSelector(".submit")));
    }

    public TechnicalReviewWebForm switchLanguageToEnglish() {
        languages.stream()
                .filter(anchor -> "English".equals(anchor.getText()))
                .findFirst()
                .ifPresentOrElse(WebElement::click, () -> {
                    throw new IllegalStateException("Could not change language to English. WebElement is not found.");
                });
        return webForm;
    }
}
