package com.example.agronepal.model;

import android.widget.TextView;

public class User1 {
    private String Username, Phone, Type, Email;

    public User1() {
    }

    public User1(String username) {
        Username = username;
    }

    public User1(String username, String phone, String type, String email) {
        Username = username;
        Phone = phone;
        Type = type;
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
