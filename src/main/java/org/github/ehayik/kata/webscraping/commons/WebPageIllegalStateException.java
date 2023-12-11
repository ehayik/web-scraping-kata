package org.github.ehayik.kata.webscraping.commons;

public class WebPageIllegalStateException extends IllegalStateException {

    public WebPageIllegalStateException(String message) {
        super(message);
    }

    public WebPageIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
