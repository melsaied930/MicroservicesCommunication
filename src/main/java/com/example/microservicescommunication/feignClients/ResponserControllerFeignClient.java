package com.example.microservicescommunication.feignClients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feignClient/responser")
public class ResponserControllerFeignClient {

    @GetMapping("/get-message")
    public String getMessage() {
        return "Hello from FeignClient ResponserController!";
    }
}
