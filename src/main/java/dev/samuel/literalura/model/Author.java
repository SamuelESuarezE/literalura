package dev.samuel.literalura.model;

import dev.samuel.literalura.dto.AuthorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    protected Author() {}

    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.name();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    @Override
    public String toString() {
        return String.format("""
        Author: %s
        Birth date: %d
        Death date: %d
        Books: %s
        """, name, birthYear, deathYear, books.stream().map(Book::getTitle).toList());
    }
}
