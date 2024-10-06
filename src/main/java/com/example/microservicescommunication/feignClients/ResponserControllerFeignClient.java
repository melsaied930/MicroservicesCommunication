package com.example.microservicescommunication.feignClients;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feignClient")
public class ResponserControllerFeignClient {

    @GetMapping("/response")
    public String getMessage() {
        return "Hello from ResponserController!";
    }

    @PostMapping("/echo")
    public String echoMessage(@RequestBody String message) {
        return "Echo from ResponserController: " + message;
    }
}
