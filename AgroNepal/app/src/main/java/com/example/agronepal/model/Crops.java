package com.example.agronepal.model;

public class Crops {
    private String name ;
    private String Description;
    private String categorie;
    private String image_url;

    public Crops() {
    }

    public Crops(String name, String description, String categorie, String image_url) {
        this.name = name;
        Description = description;
        this.categorie = categorie;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
