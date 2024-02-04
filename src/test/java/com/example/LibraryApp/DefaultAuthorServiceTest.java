package com.example.LibraryApp;

import com.example.LibraryApp.pojo.Author;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.service.DefaultAuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultAuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private DefaultAuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors() {
        // Given
        Author author1 = new Author();
        Author author2 = new Author();
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorRepository.findAll()).thenReturn(authors);

        // When
        List<Author> result = authorService.getAllAuthors();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void getAuthorById() {
        // Given
        int authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        // When
        Author result = authorService.getAuthorById(authorId);

        // Then
        assertEquals(author, result);
    }

    @Test
    void createAuthor() {
        // Given
        Author author = new Author();
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        // When
        Author result = authorService.createAuthor(author);

        // Then
        assertEquals(author, result);
    }

    @Test
    void updateAuthor() {
        // Given
        int authorId = 1;
        Author existingAuthor = new Author();
        existingAuthor.setAuthorId(authorId);
        existingAuthor.setBiography("Old Biography");

        Author updatedAuthor = new Author();
        updatedAuthor.setBiography("New Biography");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(existingAuthor);

        // When
        Author result = authorService.updateAuthor(authorId, updatedAuthor);

        // Then
        assertEquals(updatedAuthor.getBiography(), result.getBiography());
    }

    @Test
    void deleteAuthor() {
        // Given
        int authorId = 1;

        // When
        authorService.deleteAuthor(authorId);

        // Then
        verify(authorRepository, times(1)).deleteById(authorId);
    }
}