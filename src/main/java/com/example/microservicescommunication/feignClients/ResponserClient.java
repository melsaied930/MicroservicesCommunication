package com.example.microservicescommunication.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Declare the Feign client and provide the base URL (or service name in case of Eureka)
@FeignClient(name = "responser", url = "http://localhost:8080/feignClient")
public interface ResponserClient {

    // Define a method for the GET request
    @GetMapping("/response")
    String getMessage();

    // Define a method for the POST request
    @PostMapping("/echo")
    String echoMessage(@RequestBody String message);
}
