package com.example.service;

import com.example.beans.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class ApiService {

    private final static HttpClient httpClient = HttpClient.newHttpClient();
    private final static String baseUrl = "http://localhost:9192/";

    public static String authenticate(User user) throws Exception {
        // Prepare the request body
        String requestBody = "{\"key\":\"value\"}";
        ObjectMapper mapper = new ObjectMapper();
        String string = null;
        try {
            // convert user object to json string and return it
             string= mapper.writeValueAsString(user);
        }

        // catch various errors
        catch (JsonGenerationException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        // Create the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "authenticate"))

                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(string))
                .build();

        // Send the request
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check response status and body
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.statusCode());
        }
    }

    public String postWithHeadersAndBody(User user) throws Exception {

        String response1=authenticate(user);
       // String str=response1.
        // Prepare the request body
        String requestBody = "{\"key\":\"value\"}";

        ObjectMapper mapper = new ObjectMapper();
        String string = null;
        try {
            // convert user object to json string and return it
            string= mapper.writeValueAsString(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        // Create the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl ))
                .header("Authorization", "Bearer"+" "+ response1)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(string))
                .build();

        // Send the request
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response=="+response.statusCode());
        // Check response status and body
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.statusCode());
        }
    }
}
