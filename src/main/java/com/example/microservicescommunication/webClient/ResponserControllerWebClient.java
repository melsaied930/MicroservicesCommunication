package com.example.microservicescommunication.webClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webClient/responser")
public class ResponserControllerWebClient {
    @GetMapping("/get-message")
    public String getMessage() {
        return "Hello from WebClient ResponserController!";
    }
}
