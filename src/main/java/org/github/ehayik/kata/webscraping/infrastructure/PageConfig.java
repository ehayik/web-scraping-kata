package org.github.ehayik.kata.webscraping.infrastructure;

import java.util.Objects;

/**
 * Represents the configuration of a web page.
 *
 * <p>
 * The configuration is stored in a record that provides a concise and immutable way to define and store the page configuration.
 *
 *
 * @param url   the URL of the web page
 * @param title the title of the web page
 */
public record PageConfig(String url, String title) {

    /**
     * Checks if the given title is equal to the title of the web page.
     *
     * @param title the title to check against the web page title
     * @return true if the given title is equal to the web page title, false otherwise
     */
    public boolean hasTitle(String title) {
        return Objects.equals(this.title, title);
    }
}
