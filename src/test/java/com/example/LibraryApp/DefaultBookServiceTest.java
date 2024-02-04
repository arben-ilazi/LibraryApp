package com.example.LibraryApp;

import com.example.LibraryApp.pojo.Author;
import com.example.LibraryApp.pojo.Book;
import com.example.LibraryApp.pojo.Genre;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.GenreRepository;
import com.example.LibraryApp.service.DefaultBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private DefaultBookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        // Given
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<Book> result = bookService.getAllBooks();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void getBookById() {
        // Given
        int bookId = 1;
        Book book = new Book();
        book.setBookId(bookId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // When
        Book result = bookService.getBookById(bookId);

        // Then
        assertEquals(book, result);
    }

    @Test
    void createBook() {
        // Given
        Author author = new Author();
        author.setAuthorId(1);
        Genre genre = new Genre();
        genre.setGenreId(1);

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setAuthor(author);
        book.setGenre(genre);

        when(authorRepository.findById(any())).thenReturn(Optional.of(author));
        when(genreRepository.findById(any())).thenReturn(Optional.of(genre));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // When
        Book result = bookService.createBook(book);

        // Then
        assertEquals(book, result);
    }

    @Test
    void updateBook() {
        // Given
        int bookId = 1;
        Book existingBook = new Book();
        existingBook.setBookId(bookId);
        existingBook.setTitle("Existing Book");

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Book");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        // When
        Book result = bookService.updateBook(bookId, updatedBook);

        // Then
        assertEquals(updatedBook.getTitle(), result.getTitle());
    }

    @Test
    void deleteBook() {
        // Given
        int bookId = 1;

        // When
        bookService.deleteBook(bookId);

        // Then
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
