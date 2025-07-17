package dev.samuel.literalura.service;

import dev.samuel.literalura.model.Book;
import dev.samuel.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public List<Book> getAllBooksByLanguage(String lang) {
        var books = repository.findAllByLanguage(lang);
        if (books.isEmpty()) { return null; }
        return books;
    }
}
