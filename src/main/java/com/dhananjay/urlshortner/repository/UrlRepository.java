package com.dhananjay.urlshortner.repository;

import com.dhananjay.urlshortner.Model.UrlEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<UrlEntity, String> {

}
