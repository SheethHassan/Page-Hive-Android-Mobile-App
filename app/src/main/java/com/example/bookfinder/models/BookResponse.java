package com.example.bookfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {

    @SerializedName("items")
    private List<Book> items;

    public List<Book> getItems() {
        return items;
    }
    public void setItems(List<Book> items) {
        this.items = items;
    }
}
