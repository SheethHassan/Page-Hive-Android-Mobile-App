package com.example.bookfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {
    @SerializedName("title")
    private String title;

    @SerializedName("authors")
    private List <String> authors;

    @SerializedName("description")
    private String description;

    @SerializedName("imageLinks")
    private ImageLinks imageLinks;


    public String getTitle() {
        return title;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public String getDescription() {
        return description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

}
