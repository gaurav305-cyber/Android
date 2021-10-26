package com.example.agronepal.model;

public class Experiences {
    private String Title, Experience;

    public Experiences() {
    }

    public Experiences(String title, String experience) {
        Title = title;
        Experience = experience;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }
}
