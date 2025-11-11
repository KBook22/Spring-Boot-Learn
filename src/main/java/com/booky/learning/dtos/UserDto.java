package com.booky.learning.dtos;

public class UserDto {
    private String id;
    private String username;

    public UserDto(String id, String username) {
        this.id = id;
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
