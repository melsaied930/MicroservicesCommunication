package com.example.microservicescommunication.restTemplate;

import com.example.microservicescommunication.ClientBaseUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/template/requester")
public class RequesterControllerRestTemplate {

    private final RestTemplate restTemplate;
    private final ClientBaseUrlConfig clientBaseUrlConfig;

    @Autowired
    public RequesterControllerRestTemplate(RestTemplate restTemplate, ClientBaseUrlConfig clientBaseUrlConfig) {
        this.restTemplate = restTemplate;
        this.clientBaseUrlConfig = clientBaseUrlConfig;
    }

    @GetMapping("/request")
    public String getMessageFromResponder() {
        String url = clientBaseUrlConfig.getBaseUrl("/responser/get-message");
        return restTemplate.getForObject(url, String.class);
    }
}
