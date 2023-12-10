package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TechnicalReviewPageController {

    private final TechnicalReviewPageFactory technicalReviewPageFactory;

    @GetMapping("/technical-review/{licensePlate}")
    public void getTechnicalReview(@PathVariable String licensePlate) {
        try (TechnicalReviewPage page = technicalReviewPageFactory.create()) {
            page.goTo()
                    .switchLanguageToEnglish()
                    .getForm()
                    .enterLicensePlate(licensePlate)
                    .crackSecurityCode()
                    .submit();
        }
    }
}