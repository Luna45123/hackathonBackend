package com.hackathon.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Hello {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public ResponseEntity<Moodface> getDataFromExternalApi() {
        String url = "http://localhost:5000/getMood";
        Moodface response = restTemplate.getForObject(url, Moodface.class);
        return new ResponseEntity<Moodface>(response,HttpStatus.OK);
    }
}
