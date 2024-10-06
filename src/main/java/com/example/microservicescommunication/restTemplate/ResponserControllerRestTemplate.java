package com.example.microservicescommunication.restTemplate;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template/responser")
public class ResponserControllerRestTemplate {

    @GetMapping("/get-message")
    public String getMessage() {
        return "Hello from RestTemplate ResponserController!";
    }
}
