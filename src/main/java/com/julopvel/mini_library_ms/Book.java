package com.julopvel.mini_library_ms;

import com.google.common.base.MoreObjects;

// This model represents the `books` table
// and serves as a POJO for its content

// Note that `id` and `availabilityStatus` properties
// are set by the database

public class Book {

    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private Boolean availabilityStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(Boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("author", author)
                .add("isbn", isbn)
                .add("availabilityStatus", availabilityStatus)
                .toString();
    }

}
