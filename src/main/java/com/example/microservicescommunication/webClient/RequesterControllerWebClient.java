package com.example.microservicescommunication.webClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/webClient")
public class RequesterControllerWebClient {

    private final WebClient webClient;

    public RequesterControllerWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/webClient").build(); // Responser's URL
    }

    @GetMapping("/request")
    public Mono<Map<String, Object>> fetchInfo() {
        return this.webClient.get()
                .uri("/response")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}
