package com.lab3.lab3.service;

import com.lab3.lab3.dto.BooksDto;
import com.lab3.lab3.dto.UserBooksDto;
import com.lab3.lab3.dto.UserInfoDto;
import com.lab3.lab3.model.Books;
import com.lab3.lab3.model.UserBooks;
import com.lab3.lab3.model.Users;
import com.lab3.lab3.repository.BooksRepository;
import com.lab3.lab3.repository.UserBooksRepository;
import com.lab3.lab3.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserBooksService {
    private final BooksRepository bookRepository;
    private final UsersRepository userRepository;
    private final UserBooksRepository userBooksRepository;

    @Autowired
    public UserBooksService(BooksRepository bookRepository, UserBooksRepository userBooksRepository,
                            UsersRepository userRepository) {

        this.bookRepository = bookRepository;
        this.userBooksRepository = userBooksRepository;
        this.userRepository = userRepository;
    }

    public void createOrder(Long bookId, Long userId) {
        Users user = userRepository.getById(userId);
        Books book = bookRepository.getById(bookId);

        UserBooks order = new UserBooks();
        order.setUser(user);
        order.setBook(book);
        order.setStatus(UserBooks.OrderStatus.WAITING_APPROVAL);
        userBooksRepository.save(order);
    }

    public void updateOrderStatus(Long orderId, UserBooks.OrderStatus status) {
        UserBooks order = userBooksRepository.getById(orderId);
        Books book = bookRepository.getById(order.getBook().getId());
        Integer prevCount = book.getCount();
        order.setStatus(status);
        userBooksRepository.save(order);
        if(status == UserBooks.OrderStatus.IN_LIBRARY || status == UserBooks.OrderStatus.OUT_LIBRARY) {
            book.setCount(prevCount - 1);
            bookRepository.save(book);
        } else {
            book.setCount(prevCount + 1);
            bookRepository.save(book);
        }
    }

    public List<UserBooksDto> getAllOrders() {
        return userBooksRepository
                .findAll(Sort.by(Sort.Direction.DESC, "status"))
                .stream()
                .map(order -> mapOrderToDto(order, true))
                .toList();
    }

    public List<UserBooksDto> getUserOrders(Long userId) {
        Users user = userRepository.getById(userId);
        return userBooksRepository
                .findAllByUser(user, Sort.by(Sort.Direction.DESC, "status"))
                .stream()
                .map(order -> mapOrderToDto(order, false))
                .toList();
    }


    public Map<String, List<UserBooksDto>> getUserOrdersGroupedByStatus(Long userId) {
        return getUserOrders(userId).stream().collect(Collectors.groupingBy(order -> order.getStatus().name()));
    }

    public Map<String, List<UserBooksDto>> getAllOrdersByStatus() {
        return getAllOrders().stream().collect(Collectors.groupingBy(order -> order.getStatus().name()));
    }

    private UserBooksDto mapOrderToDto(UserBooks order, boolean withUser) {
        UserInfoDto userDto = null;

        if (withUser) {
            Users user = order.getUser();
            userDto = new UserInfoDto(user.getName(), user.getSecondName(), user.getEmail(), user.getId());
        }

        Books book  = order.getBook();
        BooksDto bookDto = new BooksDto(book.getId(), book.getName(), book.getDescription(), book.getAuthor(), book.getCount());
        return new UserBooksDto(order.getId(), order.getStatus(), userDto, bookDto);
    }
}
