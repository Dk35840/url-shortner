package com.dhananjay.urlshortner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import com.dhananjay.urlshortner.controller.UrlShortnerController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
class UrlShortnerControllerTest {

    @Autowired
    UrlShortnerController urlController;
    public static final String URLENDPOINT = "/URLShortner";

    public static final String RegisterURL = URLENDPOINT + "/registerUrl";
    public static final String GetUrl = URLENDPOINT + "/getUrl";

    private MockMvc mvc;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.standaloneSetup(urlController).build();
    }

    @Test
    public String registerUrlTest() throws Exception {

        URI uri = UriComponentsBuilder.fromPath(RegisterURL).queryParam("longUrl", "www.facebook.com").build().toUri();

        assertEquals(uri.toString(), URLENDPOINT + "/registerUrl?longUrl=www.facebook.com");

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        return mvcResult.getResponse().getContentAsString();

    }

    @Test
    public void getFaceBookUrl() throws Exception {

        String shortUrl = registerUrlTest();

        URI uri = UriComponentsBuilder.fromPath(GetUrl).queryParam("shortUrl", shortUrl).build().toUri();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);

        String urlResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(urlResponse, "www.facebook.com");

    }

    public void getWrongUrl() throws Exception {

        String shortUrl = registerUrlTest();

        URI uri = UriComponentsBuilder.fromPath(GetUrl).queryParam("shortUrl", shortUrl).build().toUri();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(HttpStatus.BAD_REQUEST.value(), status);

    }
}
