package dev.samuel.literalura.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private Integer downloads;

    @ManyToOne
    private Author author;

    protected Book() {}

    public Book(String title, String language, Integer downloads) {
        this.title = title;
        this.language = language;
        this.downloads = downloads;
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
}
