package com.lab3.lab3.dto;

import com.lab3.lab3.model.UserBooks;

public class UserBooksDto {
    private Long id;
    private UserInfoDto userDto;
    private BooksDto booksDto;
    private UserBooks.OrderStatus status;

    public UserBooksDto() {

    }

    public UserBooksDto(Long id, UserBooks.OrderStatus status,
                    UserInfoDto userDto, BooksDto dishDto) {
        this.id = id;
        this.userDto = userDto;
        this.booksDto = dishDto;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfoDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserInfoDto userDto) {
        this.userDto = userDto;
    }

    public BooksDto getBooksDto() {
        return booksDto;
    }

    public void setBooksDto(BooksDto booksDto) {
        this.booksDto = booksDto;
    }

    public UserBooks.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(UserBooks.OrderStatus status) {
        this.status = status;
    }
}
