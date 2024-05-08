package org.github.ehayik.kata.webscraping.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.kata.webscraping.commons.WebPageIllegalStateException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpHeaders.RETRY_AFTER;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebPageIllegalStateException.class)
    ResponseEntity<ProblemDetail> handleWebPageIllegalStateException(WebPageIllegalStateException ex) {
        logger.error("Web page scraping state is invalid.", ex);
        var errorResponse = ErrorResponse.create(ex, SERVICE_UNAVAILABLE, "Web page scraping state is invalid");
        var body = errorResponse.updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale());
        return ResponseEntity.status(SERVICE_UNAVAILABLE)
                .header(RETRY_AFTER, "5s")
                .body(body);
    }
}
