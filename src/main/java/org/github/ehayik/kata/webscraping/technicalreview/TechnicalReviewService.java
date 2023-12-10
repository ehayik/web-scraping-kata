package org.github.ehayik.kata.webscraping.technicalreview;

import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechnicalReviewService {

    private final TechnicalReviewPageFactory technicalReviewPageFactory;

    @Retry(name = "get-technical-review", fallbackMethod = "fallbackToEmpty")
    public Optional<TechnicalReview> getTechnicalReview(String licensePlate) {
        try (TechnicalReviewPage page = technicalReviewPageFactory.create()) {
            return page.goTo()
                    .switchLanguageToEnglish()
                    .enterLicensePlate(licensePlate)
                    .enterSecurityCode()
                    .submit()
                    .getValidityDate()
                    .map(validTo -> new TechnicalReview(licensePlate, validTo));
        }
    }

    @SuppressWarnings("unused")
    private Optional<TechnicalReview> fallbackToEmpty(String licensePlate, IllegalStateException ex) {
        log.info("Failed to get {} technical review information after 5 reties.", licensePlate, ex);
        return Optional.empty();
    }
}
