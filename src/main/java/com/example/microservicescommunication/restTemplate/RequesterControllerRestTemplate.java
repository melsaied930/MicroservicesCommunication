package com.example.microservicescommunication.restTemplate;

import com.example.microservicescommunication.ClientBaseUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/request")
    public Map<String, String> getMessage(
            @RequestBody(required = false) String body,
            @RequestHeader(value = "custom-header", required = false) String header,
            @RequestParam(value = "urlParam", required = false) String param
    ) {
        body = (body != null && !body.isEmpty()) ? body : "RestTemplate";
        header = (header != null && !header.isEmpty()) ? header : "Controller";
        param = (param != null && !param.isEmpty()) ? param : "Requester";

        // Build URL with query parameter
        String url = clientBaseUrlConfig.getBaseUrl("/responser/get-message?urlParam={urlParam}");

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("custom-header", header);

        // Build HttpEntity with body and headers
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        // URL parameters map
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("urlParam", param);

        // Make the POST request using RestTemplate
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, String>>() {
                },
                urlParams
        );

        return response.getBody();
    }
}
