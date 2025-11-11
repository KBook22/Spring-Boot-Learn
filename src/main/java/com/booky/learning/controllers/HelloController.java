package com.booky.learning.controllers;

import com.booky.learning.dtos.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @PostMapping("/hello")
    public Message post(@RequestBody Message message) {
        message.setMessage("Hi, " + message.getMessage());
        return message;
    }
}
