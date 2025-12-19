package com.example.bookfinder.models;

public class SavedBook {
    private String bookId;
    private String title;
    private String author;
    private String thumb;
    private String status;
    private String notes;

    public SavedBook(){
        //Firestore
    }
    public SavedBook(String bookId, String title, String author, String thumb, String status, String notes){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.thumb = thumb;
        this.status = status;
        this.notes = notes;
        }

    public String getBook_id() {return bookId;}
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getThumb() { return thumb; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }

    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes;}
}
