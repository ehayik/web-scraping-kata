package org.github.ehayik.kata.webscraping.technicalreview;

import static java.util.Objects.requireNonNull;

import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.github.ehayik.kata.webscraping.infrastructure.WebScrapingProperties;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PooledWebDriver;
import org.github.ehayik.kata.webscraping.infrastructure.driverpool.PoolingWebDriverManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechnicalReviewPageFactory {

    private static final String LANGUAGE = "eng";
    private static final String WHITELIST_VAR = "tessedit_char_whitelist";
    private static final String WHITELISTED_CHARS = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final PoolingWebDriverManager driverPool;
    private final WebScrapingProperties webScrapingProperties;

    public TechnicalReviewPage create() {
        var pageConfig = webScrapingProperties.getPage("technical-review");
        var pooledWebDriver = driverPool.getPooledWebDriver();
        var webForm = createTechnicalReviewForm(pooledWebDriver);
        return new TechnicalReviewPage(pageConfig, pooledWebDriver, webForm);
    }

    private static TechnicalReviewWebForm createTechnicalReviewForm(PooledWebDriver pooledWebDriver) {
        var captchaWidget = new CaptchaWidget(pooledWebDriver.unwrap(), createTesseract());
        return new TechnicalReviewWebForm(pooledWebDriver, captchaWidget);
    }

    @SneakyThrows
    private static ITesseract createTesseract() {
        var trainedDataURL = requireNonNull(TechnicalReviewPageFactory.class.getResource("/tesseract/eng.traineddata"));
        var dataPath = Paths.get(trainedDataURL.toURI()).getParent().toString();
        var tesseract = new Tesseract();
        tesseract.setDatapath(dataPath);
        tesseract.setLanguage(LANGUAGE);
        tesseract.setVariable(WHITELIST_VAR, WHITELISTED_CHARS);
        return tesseract;
    }
}
