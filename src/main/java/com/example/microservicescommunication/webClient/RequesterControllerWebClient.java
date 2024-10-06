package com.example.microservicescommunication.webClient;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/webClient/requester")
public class RequesterControllerWebClient {

    @Value("${server.protocol:http}")
    private String protocol;

    @Value("${server.hostname:localhost}")
    private String hostname;

    @Value("${server.port:8080}")
    private int port;

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    public RequesterControllerWebClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        String baseUrl = protocol + "://" + hostname + ":" + port + "/webClient/responser";
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @GetMapping("/request")
    public String getMessage() {
        return this.webClient.get()
                .uri("/get-message")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Block and get the result synchronously
    }
}
