package com.example.LibraryApp.service;

import com.example.LibraryApp.pojo.Genre;
import com.example.LibraryApp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultGenreService implements GenreService{
    @Autowired GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return (List<Genre>) genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Integer genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Integer genreId, Genre updatedGenre) {
        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NoSuchElementException("Genre with ID " + genreId + " not found"));

        existingGenre.setGenreName(updatedGenre.getGenreName());

        return genreRepository.save(existingGenre);
    }

    @Override
    public void deleteGenre(Integer genreId) {
        genreRepository.deleteById(genreId);
    }
}
