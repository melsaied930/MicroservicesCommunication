package com.example.microservicescommunication.restTemplate;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resttemplate")
public class ResponserControllerRestTemplate {

        @GetMapping("/response")
    public String getMessage() {
        return "Hello from ResponserController!";
    }
}
