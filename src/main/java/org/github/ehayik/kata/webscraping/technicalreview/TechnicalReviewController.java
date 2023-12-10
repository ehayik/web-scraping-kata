package org.github.ehayik.kata.webscraping.technicalreview;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TechnicalReviewController {

    private final TechnicalReviewService technicalReviewService;

    @GetMapping("/technical-review/{licensePlate}")
    public ResponseEntity<TechnicalReview> getTechnicalReview(@PathVariable String licensePlate) {
        return technicalReviewService
                .getTechnicalReview(licensePlate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
