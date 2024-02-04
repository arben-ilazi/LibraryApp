package com.example.LibraryApp.controller;

import com.example.LibraryApp.pojo.Genre;
import com.example.LibraryApp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    @Autowired GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/genres/{genreId}")
    public Genre getGenreById(@PathVariable Integer genreId) {
        return genreService.getGenreById(genreId);
    }

    @PostMapping("/genres")
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("/genres/{genreId}")
    public Genre updateGenre(@PathVariable Integer genreId, @RequestBody Genre updatedGenre) {
        return genreService.updateGenre(genreId, updatedGenre);
    }

    @DeleteMapping("/genres/{genreId}")
    public void deleteGenre(@PathVariable Integer genreId) {
        genreService.deleteGenre(genreId);
    }
}
