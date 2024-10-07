package com.example.microservicescommunication.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(
        name = "responser",
        url = "#{@clientBaseUrlConfig.getBaseUrl('/responser')}"
)
public interface ResponserClient {

    @GetMapping("/get-message")
    String getMessage();

    @PostMapping("/get-message")
    Map<String, String> getMessage(
            @RequestBody(required = false) String name,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @RequestParam(value = "urlParam", required = false) String urlParam
    );
}
