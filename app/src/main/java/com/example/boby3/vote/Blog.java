package com.example.boby3.vote;

/**
 * Created by boby on 3/9/2017.
 */

public class Blog{

    private String imageUrl;
    private String description;

    public Blog(String imageUrl, String description, String title) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.title = title;
    }
    public Blog(){

    }

    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




}