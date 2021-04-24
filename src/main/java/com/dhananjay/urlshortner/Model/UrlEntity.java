package com.dhananjay.urlshortner.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlEntity {
    public UrlEntity() {
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    private String longUrl;

    public UrlEntity(String id, String longUrl) {
        this.id = id;
        this.longUrl = longUrl;
    }

}
