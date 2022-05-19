package com.lab3.lab3.service;

import com.lab3.lab3.dto.BooksDto;
import com.lab3.lab3.model.Books;
import com.lab3.lab3.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BooksService {
    BooksRepository bookRepository;

    @Lazy
    @Autowired
    public BooksService(BooksRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Page<BooksDto> findPaginated(Pageable pageable) {
        List<Books> books = new ArrayList<>(bookRepository.findAll());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BooksDto> list;
        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex).stream().map(book -> new BooksDto(book.getId(),
                    book.getName(), book.getDescription(), book.getAuthor(), book.getCount())).toList();
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), books.size());

    }
}
