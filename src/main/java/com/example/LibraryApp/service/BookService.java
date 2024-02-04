package com.example.LibraryApp.service;

import com.example.LibraryApp.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Integer bookId);

    Book createBook(Book newBook);

    Book updateBook(Integer bookId, Book updatedBook);

    void deleteBook(Integer bookId);
}