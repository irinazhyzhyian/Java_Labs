package com.lab3.lab3.dto;

import com.lab3.lab3.model.UserBooks;

public class UpdateUserBooksDto {
    private UserBooks.OrderStatus status;

    public UpdateUserBooksDto() {

    }

    public UpdateUserBooksDto(UserBooks.OrderStatus status) {
        this.status = status;
    }

    public UserBooks.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(UserBooks.OrderStatus status) {
        this.status = status;
    }

}
