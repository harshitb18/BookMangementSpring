package com.Books.BooksManagement.B;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private String description;
    private String category;

    public Book(){

    }

    public Book(String title,String author,String description,String category){
        this.title=title;
        this.author=author;
        this.description=description;
        this.category=category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "id "+id+"title "+title+"author "+author+"description "+description+"category "+category;
    }

    public void setId(int id) {
        this.id=id;
    }
}
