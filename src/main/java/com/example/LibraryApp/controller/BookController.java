package com.example.LibraryApp.controller;

import com.example.LibraryApp.pojo.Book;
import com.example.LibraryApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable Integer bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book newBook) {
        return bookService.createBook(newBook);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(
            @PathVariable Integer bookId,
            @RequestBody Book updatedBook) {
        return bookService.updateBook(bookId, updatedBook);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
    }

}
