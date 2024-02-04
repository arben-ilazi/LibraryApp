package com.example.LibraryApp.controller;


import com.example.LibraryApp.pojo.Author;
import com.example.LibraryApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired AuthorService authorService;


    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{authorId}")
    public Author getAuthorById(@PathVariable Integer authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("authors")
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/authors/{authorId}")
    public Author updateAuthor(@PathVariable Integer authorId, @RequestBody Author updatedAuthor) {
        return authorService.updateAuthor(authorId, updatedAuthor);
    }

    @DeleteMapping("/authors/{authorId}")
    public void deleteAuthor(@PathVariable Integer authorId) {
        authorService.deleteAuthor(authorId);
    }
}
