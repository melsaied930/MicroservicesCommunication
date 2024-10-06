package com.example.microservicescommunication.restTemplate;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/resttemplate")
public class RequesterControllerRestTemplate {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String responderBaseUrl = "http://localhost:8080/resttemplate";

    @GetMapping("/request")
    public String getMessageFromResponder() {
        String url = responderBaseUrl + "/response";
        return restTemplate.getForObject(url, String.class);
    }
}
