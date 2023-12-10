package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.github.ehayik.kata.webscraping.infrastructure.WebScrapingProperties;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PoolingWebDriverManager;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class TechnicalReviewPageFactory {

    private final PoolingWebDriverManager driverPool;
    private final WebScrapingProperties webScrapingProperties;

    public TechnicalReviewPage create() {
        var pageConfig = webScrapingProperties.getPage("technical-review");
        var pooledWebDriver = driverPool.getPooledWebDriver();
        var form = createTechnicalReviewForm(pooledWebDriver.unwrap());
        return new TechnicalReviewPage(pageConfig, pooledWebDriver, form);
    }

    private static TechnicalReviewForm createTechnicalReviewForm(WebDriver webDriver) {
        var captchaWidget = new CaptchaWidget(webDriver, createTesseract());
        return new TechnicalReviewForm(webDriver, captchaWidget);
    }

    private static ITesseract createTesseract() {
        var trainedDataURL =
                requireNonNull(TechnicalReviewPageFactory.class.getResource("/tesseract-ocr/tessdata/eng.traineddata"));
        var tesseract = new Tesseract();
        tesseract.setDatapath(trainedDataURL.getPath());
        tesseract.setLanguage("eng");
        return tesseract;
    }
}
