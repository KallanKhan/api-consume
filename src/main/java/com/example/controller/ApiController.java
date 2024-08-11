package com.example.controller;

import com.example.beans.User;
import com.example.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/consume")
    public String consumeApi(@RequestBody User user) {

        try {
            return apiService.postWithHeadersAndBody(user);
        } catch (Exception e) {
            // Handle exception appropriately
            return "Error: " + e.getMessage();
        }
    }
}
