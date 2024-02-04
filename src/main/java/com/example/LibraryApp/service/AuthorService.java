package com.example.LibraryApp.service;

import com.example.LibraryApp.pojo.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(Integer authorId);

    Author createAuthor(Author author);

    Author updateAuthor(Integer authorId, Author updatedAuthor);

    void deleteAuthor(Integer authorId);
}