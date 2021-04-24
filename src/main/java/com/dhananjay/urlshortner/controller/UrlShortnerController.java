package com.dhananjay.urlshortner.controller;

import com.dhananjay.urlshortner.exception.UrlNotFoundException;
import com.dhananjay.urlshortner.services.UrlShortner;
import com.dhananjay.urlshortner.services.UrlShortnerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlShortnerController.URL_ENDPOINT)
public class UrlShortnerController {

  public static final String URL_ENDPOINT = "/URLShortner";
  @Autowired
  UrlShortner urlShortnerService;

  @PostMapping("/registerUrl")
  ResponseEntity<String> registerUrl(String longUrl) {

    if (longUrl == null) {
      return ResponseEntity.badRequest().body(null);
    }
    String url = urlShortnerService.registerNewUrl(longUrl);

    if (url == null) {
      return ResponseEntity.badRequest().body(null);
    }

    return ResponseEntity.ok().body(url);

  }

  @GetMapping("/getUrl")
  ResponseEntity<String> getUrl(String shortUrl) {

    if (shortUrl == null) {
      return ResponseEntity.badRequest().body(null);
    }
    String url = null;
    try {
      url = urlShortnerService.getUrl(shortUrl);
    } catch (UrlNotFoundException e) {
      return ResponseEntity.badRequest().body(null);
    }

    if (url == null) {
      return ResponseEntity.ok().body(null);
    }

    return ResponseEntity.ok().body(url);

  }
}
