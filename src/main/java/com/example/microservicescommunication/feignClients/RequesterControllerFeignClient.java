package com.example.microservicescommunication.feignClients;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/request")
    public Map<String, String> getMessage(
            @RequestBody(required = false) String body,
            @RequestHeader(value = "header", required = false) String header,
            @RequestParam(value = "param", required = false) String param
    ) {
        body = (body != null && !body.isEmpty()) ? body : "Feign Client";
        header = (header != null && !header.isEmpty()) ? header : "Controller";
        param = (param != null && !param.isEmpty()) ? param : "Requester";

        return responserClient.getMessage(body, header, param);
    }
}
