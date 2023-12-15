package org.github.ehayik.kata.webscraping.technicalreview;

import java.io.File;
import java.nio.file.Files;
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
        File screenshotFile = null;

        try {
            screenshotFile = captchaImg.getScreenshotAs(OutputType.FILE);
            log.info("Captcha image is capture in file {}", screenshotFile.getAbsolutePath());
            log.info("Recognizing captcha screenshot characters.");
            return tesseract.doOCR(screenshotFile);
        } catch (Exception ex) {
            throw new SecurityCodeCrackingException("Could not crack security code.", ex);
        } finally {
            deleteScreenshot(screenshotFile);
        }
    }

    private static void deleteScreenshot(File screenshotFile) {
        if (screenshotFile == null) return;

        try {
            Files.deleteIfExists(screenshotFile.toPath());
        } catch (Exception ex) {
            log.error("Error deleting captcha screenshot.", ex);
        }
    }
}
