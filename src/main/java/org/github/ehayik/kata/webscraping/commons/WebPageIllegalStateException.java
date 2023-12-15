package org.github.ehayik.kata.webscraping.commons;

/**
 * Custom exception class for handling illegal state of a web page.
 */
public class WebPageIllegalStateException extends IllegalStateException {

    public WebPageIllegalStateException(String message) {
        super(message);
    }

    public WebPageIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
