package com.lab3.lab3.dto;

public class CreateUserBooksDto {
    private Long bookId;

    public CreateUserBooksDto(Long bookId) {
        this.bookId = bookId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookIdId(Long bookId) {
        this.bookId = bookId;
    }
}
