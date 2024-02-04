package com.example.LibraryApp;

import com.example.LibraryApp.pojo.Genre;
import com.example.LibraryApp.repository.GenreRepository;
import com.example.LibraryApp.service.DefaultGenreService;
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

class DefaultGenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private DefaultGenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGenres() {
        // Given
        Genre genre1 = new Genre();
        genre1.setGenreName("Fantasy");
        Genre genre2 = new Genre();
        genre2.setGenreName("Science Fiction");
        List<Genre> genres = Arrays.asList(genre1, genre2);
        when(genreRepository.findAll()).thenReturn(genres);

        // When
        List<Genre> result = genreService.getAllGenres();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void getGenreById() {
        // Given
        int genreId = 1;
        Genre genre = new Genre();
        genre.setGenreId(genreId);
        genre.setGenreName("Fantasy");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));

        // When
        Genre result = genreService.getGenreById(genreId);

        // Then
        assertEquals(genre, result);
    }

    @Test
    void createGenre() {
        // Given
        Genre genre = new Genre();
        genre.setGenreName("Fantasy");
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        // When
        Genre result = genreService.createGenre(genre);

        // Then
        assertEquals(genre, result);
    }

    @Test
    void updateGenre() {
        // Given
        int genreId = 1;
        Genre existingGenre = new Genre();
        existingGenre.setGenreId(genreId);
        existingGenre.setGenreName("Fantasy");
        Genre updatedGenre = new Genre();
        updatedGenre.setGenreName("Science Fiction");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(existingGenre));
        when(genreRepository.save(existingGenre)).thenReturn(existingGenre);

        // When
        Genre result = genreService.updateGenre(genreId, updatedGenre);

        // Then
        assertEquals(updatedGenre.getGenreName(), result.getGenreName());
    }

    @Test
    void deleteGenre() {
        // Given
        int genreId = 1;

        // When
        genreService.deleteGenre(genreId);

        // Then
        verify(genreRepository, times(1)).deleteById(genreId);
    }
}

