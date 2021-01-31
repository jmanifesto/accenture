package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.URL;

import java.util.Optional;

public interface URLService {

    URL shortenURL(URL url, String localURL);

    Optional<URL> updateUrl(URL url, String user);

    Iterable<URL> listAllURLs();

    /**
     * Get a list of all URLs that beString to a user.
     *
     * @param user
     * @return a list of users
     */
    Iterable<URL> listURLs(String user);

    /**
     * Add a new URL to the collection of URLs for a user.
     *
     * @param user
     * @param url
     * @return The URL object which has been created
     */
    URL addURL(String user, String url);

    /**
     * Get a specific URL of a user by its ID.
     * @param user
     * @param id
     * @return The URL object
     */
    Optional<URL> getURL(Long user, String id);

    /**
     * Return a specific URL by ID.
     *
     * @param id
     * @return The URL object
     */
    Optional<URL> getURL(Long id);

    /**
     * Delete a specific URL which belongs to a user.
     * @param user
     * @param id
     */
    void deleteURL(Long id, String user);
}
