package com.dhananjay.urlshortner.exception;

public class UrlNotFoundException extends Exception {
    
    public UrlNotFoundException(){
        super("Url Not Found in DB");
    }
   
}
