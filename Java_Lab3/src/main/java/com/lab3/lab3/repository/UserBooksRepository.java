package com.lab3.lab3.repository;

import com.lab3.lab3.dto.UserBooksDto;
import com.lab3.lab3.model.UserBooks;
import com.lab3.lab3.model.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBooksRepository extends JpaRepository<UserBooks, Long> {
    List<UserBooks> findAllByUser(Users user, Sort status);

}
