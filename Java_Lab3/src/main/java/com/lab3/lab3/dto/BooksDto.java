package com.lab3.lab3.dto;

import java.io.Serializable;

public class BooksDto implements Serializable {
    private String name;
    private String description;
    private String author;
    private Integer count;
    private Long id;

    public BooksDto(Long id, String name, String description, String author, Integer count) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.count = count;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}