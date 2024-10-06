package com.example.microservicescommunication.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "responser", url = "${server.protocol}://${server.hostname}:${server.port}/feignClient/responser")
public interface ResponserClient {

    @GetMapping("/get-message")
    String getMessage();
}
