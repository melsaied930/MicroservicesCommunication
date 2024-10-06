package com.example.microservicescommunication.feignClients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feignClient/requester")
public class RequesterControllerFeignClient {

    private final ResponserClient responserClient;

    public RequesterControllerFeignClient(ResponserClient responserClient) {
        this.responserClient = responserClient;
    }

    @GetMapping("/request")
    public String getMessageFromResponder() {
        return responserClient.getMessage();
    }
}
