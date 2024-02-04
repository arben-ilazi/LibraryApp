package com.example.LibraryApp.service;

import com.example.LibraryApp.pojo.Author;
import com.example.LibraryApp.pojo.Genre;
import com.example.LibraryApp.pojo.Book;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultBookService implements BookService{
    @Autowired BookRepository bookRepository;
    @Autowired GenreRepository genreRepository;
    @Autowired AuthorRepository authorRepository;

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book createBook(Book newBook) {
        // Check if the specified author and genre IDs exist
        Author author = authorRepository.findById(newBook.getAuthor().getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author with ID " + newBook.getAuthor().getAuthorId() + " not found"));

        Genre genre = genreRepository.findById(newBook.getGenre().getGenreId())
                .orElseThrow(() -> new NoSuchElementException("Genre with ID " + newBook.getGenre().getGenreId() + " not found"));

        // Set the author and genre for the new book
        newBook.setAuthor(author);
        newBook.setGenre(genre);

        // Save and return the new book
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateBook(Integer bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + bookId + " not found"));

        // Update basic book attributes
        existingBook.setTitle(updatedBook.getTitle());

        // Update associated genre
        if (updatedBook.getGenre() != null && updatedBook.getGenre().getGenreId() != null) {
            Genre genre = genreRepository.findById(updatedBook.getGenre().getGenreId())
                    .orElseThrow(() -> new NoSuchElementException("Genre with ID " + updatedBook.getGenre().getGenreId() + " not found"));
            existingBook.setGenre(genre);
        }

        // Update associated author
        if (updatedBook.getAuthor() != null && updatedBook.getAuthor().getAuthorId() != null) {
            Author author = authorRepository.findById(updatedBook.getAuthor().getAuthorId())
                    .orElseThrow(() -> new NoSuchElementException("Author with ID " + updatedBook.getAuthor().getAuthorId() + " not found"));
            existingBook.setAuthor(author);
        }

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
