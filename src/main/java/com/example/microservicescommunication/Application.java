package com.example.microservicescommunication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//http://localhost:8080/swagger-ui.html
//https://localhost:8443/swagger-ui/index.html
@SpringBootApplication
@EnableFeignClients // Enable Feign Clients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
