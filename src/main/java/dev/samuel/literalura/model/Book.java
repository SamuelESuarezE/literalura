package dev.samuel.literalura.model;

import dev.samuel.literalura.dto.BookDTO;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalId;
    private String title;
    private String language;
    private Integer downloads;

    @ManyToOne
    private Author author;

    protected Book() {}

    public Book(BookDTO bookDTO, Author author) {
        this.externalId = bookDTO.id();
        this.title = bookDTO.title();
        this.language = bookDTO.languages().getFirst();
        this.downloads = bookDTO.downloads();
        this.author = author;
    }

    public Long getExternalId() {
        return externalId;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return String.format("""
        ----- Book -----
        Title: %s
        Author: %s
        Language: %s
        Downloads: %d
        -----------------
        """, title, author.getName(), language, downloads);
    }
}
