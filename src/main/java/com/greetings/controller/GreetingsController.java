package com.greetings.controller;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json"})
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*")
@Getter
public class GreetingsController {


    private static final String RESPONSE = "Hello";

    @Autowired
    private Gson gson;

    @GetMapping("/greetings/test")
    public ResponseEntity<String> getGreetings() {
        return ResponseEntity.ok(gson.toJson(RESPONSE));
    }

    @GetMapping("/greetings/test/{userId}")
    public ResponseEntity<String> getGreetingsWithUserId(@PathVariable(name = "userId") String userId) throws RuntimeException {
        if (userId.equals("Jacopo")) {
            throw new RuntimeException(String.format("%s user is unable to access", userId));
        }

        String response = StringUtils.isEmpty(userId) ? RESPONSE : RESPONSE + " " + userId;
        return ResponseEntity.ok(gson.toJson(response));
    }
}
