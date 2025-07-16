package dev.samuel.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.samuel.literalura.dto.AuthorDTO;
import dev.samuel.literalura.dto.BookDTO;
import dev.samuel.literalura.dto.GutendexResponseDTO;
import dev.samuel.literalura.model.Author;
import dev.samuel.literalura.model.Book;
import dev.samuel.literalura.repository.AuthorRepository;
import dev.samuel.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class GutendexApiService {
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String BASE_URL = "https://gutendex.com/books/?search=";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public GutendexApiService(HttpClient client, ObjectMapper mapper, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.client = client;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Book> searchBookByTitle(String title) {
        GutendexResponseDTO gutendexResponseDTO = getBookByTitleRequest(title);

        if (gutendexResponseDTO == null || gutendexResponseDTO.count() == 0) {
            System.out.println("Book not found.");
            return Optional.empty();
        }

        BookDTO bookDTO = gutendexResponseDTO.results().getFirst();
        AuthorDTO authorDTO = bookDTO.authors().getFirst();

        if (bookRepository.existsByExternalId(bookDTO.id())) {
            System.out.println("Book already exists.");
            return Optional.empty();
        }
        Author author = authorRepository.findByName(authorDTO.name())
                .orElseGet(() -> authorRepository.save(new Author(authorDTO)));

        Book book = new Book(bookDTO, author);
        bookRepository.save(book);

        return Optional.of(book);
    }

    private GutendexResponseDTO getBookByTitleRequest(String title) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + URLEncoder.encode(title, StandardCharsets.UTF_8))).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), GutendexResponseDTO.class);
        } catch (Exception e) {
            System.out.println("Error calling Gutendex API");
            return null;
        }
    }
}
