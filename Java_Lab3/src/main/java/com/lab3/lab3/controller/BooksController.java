package com.lab3.lab3.controller;

import com.lab3.lab3.dto.BooksDto;
import com.lab3.lab3.service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping("/")
    public String getAllUsers(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        Page<BooksDto> booksPage = booksService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("booksPage", booksPage);
        int totalPages = booksPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            if (currentPage > 1) {
                pageNumbers.add(currentPage - 1);
            }
            pageNumbers.add(currentPage);
            if (currentPage < totalPages) {
                pageNumbers.add(currentPage + 1);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "index";
    }
}
