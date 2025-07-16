package dev.samuel.literalura.service;

import dev.samuel.literalura.model.Book;
import dev.samuel.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }


}
