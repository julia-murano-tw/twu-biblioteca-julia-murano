package com.twu.biblioteca;

public class Book {

    private String Title;
    private String Author;
    private int PublicationYear;
    private boolean checkedOut = false;
    public Book(String title, String author, int publicationYear) {
        this.Title = title;
        this.Author = author;
        this.PublicationYear = publicationYear;
    }


    public String getTitle() {
        return this.Title;
    }

    public String getAuthor(){
        return this.Author;
    }

    public int getPublicationYear(){
        return this.PublicationYear;
    }
    public void checkOut(){
        this.checkedOut = true;
    }

    public boolean isCheckedOut() {
        return this.checkedOut;
    }
}
