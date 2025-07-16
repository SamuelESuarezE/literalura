package dev.samuel.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.samuel.literalura.dto.BookDTO;
import dev.samuel.literalura.dto.GutendexResponseDTO;
import dev.samuel.literalura.model.Book;
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

    public GutendexApiService(HttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public Optional<Book> searchBookByTitle(String title) {
        GutendexResponseDTO gutendexResponseDTO = getBookByTitleRequest(title);

        if (gutendexResponseDTO == null || gutendexResponseDTO.count() == 0) {
            System.out.println("Book not found.");
            return Optional.empty();
        }

        BookDTO bookDTO = gutendexResponseDTO.results().getFirst();

        return Optional.of(new Book(bookDTO));
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
