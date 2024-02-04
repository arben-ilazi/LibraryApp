package com.example.LibraryApp.service;

import com.example.LibraryApp.pojo.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre getGenreById(Integer genreId);

    Genre createGenre(Genre genre);

    Genre updateGenre(Integer genreId, Genre updatedGenre);

    void deleteGenre(Integer genreId);
}
