package dev.samuel.literalura.model;

import dev.samuel.literalura.dto.AuthorDTO;
import dev.samuel.literalura.dto.BookDTO;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    private Long id;
    private String title;
    private String language;
    private Integer downloads;

    @ManyToOne
    private Author author;

    protected Book() {}

    public Book(BookDTO bookDTO) {
        this.id = bookDTO.id();
        this.title = bookDTO.title();
        this.language = bookDTO.languages().getFirst();
        this.downloads = bookDTO.downloads();
        this.author = new Author(bookDTO.authors().getFirst());
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
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", downloads=" + downloads +
                ", author=" + author +
                '}';
    }
}
