package com.example.agronepal.model;

public class User {
    public String Username, Phone, Type, Email;

    public User() {
    }

    public User(String username, String phone, String type, String email) {
        this.Username = username;
        this.Phone = phone;
        this.Type = type;
        this.Email = email;
    }

}