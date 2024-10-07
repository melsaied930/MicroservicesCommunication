package com.example.microservicescommunication.webClient;

import com.example.microservicescommunication.ClientBaseUrlConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
@RequestMapping("/webClient/requester")
public class RequesterControllerWebClient {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;
    private final ClientBaseUrlConfig clientBaseUrlConfig;

    @Autowired
    public RequesterControllerWebClient(WebClient.Builder webClientBuilder, ClientBaseUrlConfig clientBaseUrlConfig) {
        this.webClientBuilder = webClientBuilder;
        this.clientBaseUrlConfig = clientBaseUrlConfig;
    }

    @PostConstruct
    public void init() {
        String baseUrl = clientBaseUrlConfig.getBaseUrl("/responser");
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @GetMapping("/request")
    public String getMessageFromResponder() {
        return this.webClient.get()
                .uri("/get-message")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Block and get the result synchronously
    }

    @PostMapping("/request")
    public Map<String, String> getMessage(
            @RequestBody(required = false) String body,
            @RequestHeader(value = "custom-header", required = false) String header,
            @RequestParam(value = "param", required = false) String param
    ) {
        // Assign default values if any of the values are null
        body = (body != null && !body.isEmpty()) ? body : "WebClient";
        header = (header != null && !header.isEmpty()) ? header : "Controller";
        final String finaParam = (param != null && !param.isEmpty()) ? param : "Requester"; // final or effectively final

        return this.webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/get-message")
                        .queryParam("urlParam", finaParam) // Use finaParam
                        .build())
                .header("custom-header", header) // Add custom header
                .bodyValue(body) // Add body in the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .block(); // Block and get the result synchronously
    }
}
