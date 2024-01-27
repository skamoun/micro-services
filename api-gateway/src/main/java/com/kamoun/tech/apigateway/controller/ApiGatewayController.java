package com.kamoun.tech.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gateway")
public class ApiGatewayController {

    @GetMapping
    public String getDefault(){

        return  " api gateway responds successfully!";
    }
}
