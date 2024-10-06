package com.example.microservicescommunication.feignClients;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feignClient")
public class RequesterControllerFeignClient {

    private final ResponserClient responserClient;

    // Inject the Feign client into the controller
    public RequesterControllerFeignClient(ResponserClient responserClient) {
        this.responserClient = responserClient;
    }

    // Endpoint to trigger the Feign GET request
    @GetMapping("/get-message")
    public String getMessageFromResponder() {
        return responserClient.getMessage();
    }

    // Endpoint to trigger the Feign POST request
    @PostMapping("/send-message")
    public String sendMessageToResponder(@RequestBody String message) {
        return responserClient.echoMessage(message);
    }
}
