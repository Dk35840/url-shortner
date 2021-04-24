package com.dhananjay.urlshortner.services;

import java.util.Map;
import java.util.Random;

import com.dhananjay.urlshortner.Model.UrlEntity;
import com.dhananjay.urlshortner.exception.UrlNotFoundException;
import com.dhananjay.urlshortner.repository.UrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortnerImpl implements UrlShortner {

    @Autowired
    UrlRepository urlRepository;

    @Override
    public String registerNewUrl(String longUrl) {

        StringBuilder sb= new StringBuilder();

        Random r = new Random();

        for(int i=0;i<9;i++)
        sb.append((char)(r.nextInt(26) + 'a'));

        String key="http://UrlShort.com/"+sb.toString();

        UrlEntity urlEntity= new UrlEntity(key,longUrl);
        urlRepository.save(urlEntity);
      

        return key;
    }

    @Override
    public String getUrl(String shortUrl) throws UrlNotFoundException {

      UrlEntity urlEntity =urlRepository.findById(shortUrl).orElseThrow(()->new UrlNotFoundException());
     
        return urlEntity.getLongUrl();
        
    }

  
    
}
