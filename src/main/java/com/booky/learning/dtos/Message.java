package com.booky.learning.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Message {

    @JsonAlias("message")
    public String message;

    public Message() {}

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
