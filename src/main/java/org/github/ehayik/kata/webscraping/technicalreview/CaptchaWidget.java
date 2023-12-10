package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class CaptchaWidget {

    @FindBy(css = "img[data-bind='attr: {src: captchaImageSource}']")
    private WebElement captchaImg;

    private final ITesseract tesseract;

    public CaptchaWidget(@NonNull WebDriver webDriver, @NonNull ITesseract tesseract) {
        this.tesseract = tesseract;
        PageFactory.initElements(webDriver, this);
    }

    public String getSecurityCode() {
        try {
            var imgFile = captchaImg.getScreenshotAs(OutputType.FILE);
            log.info("Captcha image is capture in file {}", imgFile.getAbsolutePath());
            log.info("Recognizing captcha image characters.");
            return tesseract.doOCR(imgFile);
        } catch (Exception ex) {
            throw new SecurityCodeCrackingException("Could not crack security code.", ex);
        }
    }
}
