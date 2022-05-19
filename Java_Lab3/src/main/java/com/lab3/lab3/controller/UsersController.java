package com.lab3.lab3.controller;

import com.lab3.lab3.dto.UserRegisterDto;
import com.lab3.lab3.model.Users;
import com.lab3.lab3.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;

@Controller
public class UsersController {

    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegisterDto userRegistrationDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String showRegisterForm(Users user) {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() { return "login"; }

    @PostMapping("/register")
    public RedirectView registerUserAccount(@ModelAttribute("user") UserRegisterDto registrationDto) {
        userService.save(registrationDto);
        return new RedirectView("/");
    }
}
