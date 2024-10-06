package com.example.microservicescommunication.restTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/template/requester")
public class RequesterControllerRestTemplate {

    @Value("${server.protocol:http}")
    private String protocol;

    @Value("${server.hostname:localhost}")
    private String hostname;

    @Value("${server.port:8080}")
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/request")
    public String getMessageFromResponder() {
        String url = protocol + "://" + hostname + ":" + port + "/template/responser/get-message";
        return restTemplate.getForObject(url, String.class);
    }
}
