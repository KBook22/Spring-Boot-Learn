package com.booky.learning.models;

public class UserModel {
    private String id;
    private String username;
    public UserModel() {
    }
    public UserModel(String id, String username) {
        this.id = id;
        this.username = username;
    }
//    ID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
//    Username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
