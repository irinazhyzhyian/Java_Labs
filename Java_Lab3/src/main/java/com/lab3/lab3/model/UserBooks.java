package com.lab3.lab3.model;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user_books")
public class UserBooks {

    public enum OrderStatus{
        WAITING_APPROVAL, IN_LIBRARY, OUT_LIBRARY
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @Column(name = "status")
    private OrderStatus status;
}
