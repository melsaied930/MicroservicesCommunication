package com.example.microservicescommunication.webClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webClient")
public class ResponserControllerWebClient {

    @GetMapping("/response")
    public Map<String, String> getInfo() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Responser");
        response.put("status", "success");
        return response;
    }
}
