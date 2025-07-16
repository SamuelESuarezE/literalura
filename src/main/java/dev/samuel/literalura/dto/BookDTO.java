package dev.samuel.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
    Long id,
    String title,
    List<String> languages,
    List<AuthorDTO> authors,
    @JsonProperty("download_count") Integer downloads
) {
}
