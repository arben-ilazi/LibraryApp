package com.example.LibraryApp.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private String name;
    private String biography;
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private Set<Book> books;

    public Author() {
    }

    public Author(Integer authorId, String name, String biography, Set<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.biography = biography;
        this.books = books;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", books=" + books +
                '}';
    }
}
