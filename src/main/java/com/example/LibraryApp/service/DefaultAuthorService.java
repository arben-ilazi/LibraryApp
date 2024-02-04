package com.example.LibraryApp.service;


import com.example.LibraryApp.pojo.Author;
import com.example.LibraryApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class DefaultAuthorService implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Integer authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Integer authorId, Author updatedAuthor) {
        // Check if the author with the given ID exists
        Set<Author> authors = Collections.singleton(authorRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchElementException("Author with ID " + authorId + " not found")));

        // Author with the given ID exists, update biography
        Author existingAuthor = authors.iterator().next();
        existingAuthor.setBiography(updatedAuthor.getBiography());

        // Save and return the updated author
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(Integer authorId) {
        authorRepository.deleteById(authorId);
    }
}
