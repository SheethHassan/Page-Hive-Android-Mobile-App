package com.example.bookfinder.models;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("id")
    private String id;

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public String getId() {
        return id;
    }

public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }



}
