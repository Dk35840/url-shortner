package com.dhananjay.urlshortner.services;

import com.dhananjay.urlshortner.exception.UrlNotFoundException;

public interface UrlShortner {

    /**
     * If we passes the longUrl then it return the shortner URL
     * 
     * @param longUrl
     * @return the shortner URL of the long URL
     */
    String registerNewUrl(String longUrl);

    /**
     * 
     * @param shortUrl
     * @return the original URL
     */
    String getUrl(String shortUrl) throws UrlNotFoundException;

}
