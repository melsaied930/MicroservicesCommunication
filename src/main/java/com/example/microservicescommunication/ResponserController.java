package com.example.microservicescommunication;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/responser")
public class ResponserController {

    @GetMapping("/get-message")
    public String getMessage() {
        return "Hello from ResponserController!";
    }

    @PostMapping("/get-message")
    public Map<String, String> getMessage(
            @RequestBody(required = false) String name,
            @RequestHeader(value = "headerParam", required = false, defaultValue = "default-header") String headerParam,
            @RequestParam(value = "urlParam", required = false, defaultValue = "default-param") String urlParam
    ) {

        if (name == null || name.isEmpty()) {
            name = "Guest";
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "! Welcome from ResponserController!");
        response.put("header", "Received custom-header: " + headerParam);
        response.put("url", "Received URL parameter: " + urlParam);

        return response;
    }
}
