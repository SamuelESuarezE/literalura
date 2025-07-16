package dev.samuel.literalura.service;

import dev.samuel.literalura.model.Author;
import dev.samuel.literalura.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAllAuthorsWithBooks() {
        return repository.findAllWithBooks();
    }
}
