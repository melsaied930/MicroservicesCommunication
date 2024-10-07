package com.example.microservicescommunication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBaseUrlConfig {

    @Value("${server.protocol:http}")
    private String protocol;

    @Value("${server.hostname:localhost}")
    private String hostname;

    @Value("${server.port:8080}")
    private int port;

    public String getBaseUrl(String servicePath) {
        return String.format("%s://%s:%d%s", protocol, hostname, port, servicePath);
    }
}
