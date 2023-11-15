package com.mirea.book.catalog


public class BookDTO {
    
    private String author
    private String title
    private int year
    private String genre

    public String getAuthor() {
        return author
    }

    public void setAuthor(String author) {
        this.author = author
    }

    public String getTitle() {
        return title
    }

    public void setTitle(String title) {
        this.title = title
    }

    public int getYear() {
        return year
    }

    public void setYear(int year) {
        this.year = year
    }

    public String getGenre() {
        return genre
    }

    public void setGenre(String genre) {
        this.genre = genre
    }
}