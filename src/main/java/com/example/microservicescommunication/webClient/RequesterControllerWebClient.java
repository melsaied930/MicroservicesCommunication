package com.example.microservicescommunication.webClient;

import com.example.microservicescommunication.ClientBaseUrlConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

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
    public String getMessage() {
        return this.webClient.get()
                .uri("/get-message")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Block and get the result synchronously
    }
}
