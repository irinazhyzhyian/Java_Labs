package com.lab3.lab3.controller;

import com.lab3.lab3.dto.CreateUserBooksDto;
import com.lab3.lab3.dto.UpdateUserBooksDto;
import com.lab3.lab3.dto.UserBooksDto;
import com.lab3.lab3.dto.UserInfoDto;
import com.lab3.lab3.model.UserBooks;
import com.lab3.lab3.service.UserBooksService;
import com.lab3.lab3.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserBooksController {
    private final UserBooksService userBooksService;
    private final UsersService userService;

    @Autowired
    public UserBooksController(UserBooksService userBooksService, UsersService userService) {
        this.userBooksService = userBooksService;
        this.userService = userService;
    }



    @PostMapping("/orders")
    public String createOrder(CreateUserBooksDto orderDto, Principal principal) {
        UserInfoDto user = userService.getByEmail(principal.getName());
        userBooksService.createOrder(orderDto.getBookId(), user.getId());
        return "redirect:/my-books";
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/orders/{orderId}")
    public RedirectView updateOrder(@PathVariable Long orderId, UpdateUserBooksDto orderDto) {
        String newStatus = "";
        if (orderDto.getStatus() != null) {
            userBooksService.updateOrderStatus(orderId, orderDto.getStatus());
            newStatus = orderDto.getStatus().name();
        }

        return new RedirectView("/orders?status=" + newStatus);

    }

    @GetMapping("/my-books")
    public String getMyOrders(Model model,
                              @RequestParam("status") Optional<String> status,
                              Principal principal) {
        UserInfoDto user = userService.getByEmail(principal.getName());
        Map<String, List<UserBooksDto>> orders = userBooksService.getUserOrdersGroupedByStatus(user.getId());
        UserBooks.OrderStatus orderStatus = status
                .map(UserBooks.OrderStatus::valueOf)
                .orElse(UserBooks.OrderStatus.WAITING_APPROVAL);

        model.addAttribute("status", orderStatus.name());
        model.addAttribute("orders", orders.get(orderStatus.name()));
        return "my-books";
    }


    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/orders")
    public String getAllOrder(Model model, @RequestParam("status") Optional<String> status) {
        Map<String, List<UserBooksDto>> orders = userBooksService.getAllOrdersByStatus();
        UserBooks.OrderStatus orderStatus = status
                .map(UserBooks.OrderStatus::valueOf)
                .orElse(UserBooks.OrderStatus.WAITING_APPROVAL);

        model.addAttribute("status", orderStatus.name());
        model.addAttribute("orders", orders.get(orderStatus.name()));
        return "my-books";
    }

}
