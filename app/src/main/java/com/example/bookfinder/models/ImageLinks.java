package com.example.bookfinder.models;

import com.google.gson.annotations.SerializedName;

public class ImageLinks {
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }
}
