package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.model.SearchCondition;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping(path = {"/retrieve"})
    public Page retrieveBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return bookService.retrieveBooks(page, size);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBook();
    }

    //list book user co the muon
    @GetMapping("/borrow")
    public List<Book> getAllBooksByStatus() {
        return bookService.getAllBookAvailable();
    }

    @GetMapping("/{id}")
    public Book findBookByid(@PathVariable(name = "id") long id) {
        return bookService.findByid(id);
    }

    @DeleteMapping
    public int deleteBook(@RequestBody Book book) {
        return bookService.deleteBook(book.getId());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @PostMapping("/search")
    public List<Book> listSearchBook(@RequestBody SearchCondition body) {
        return bookService.searchResult(body);
    }

}