package dev.samuel.literalura.dto;

import java.util.List;

public record GutendexResponseDTO(
        Integer count,
        Boolean next,
        Boolean previous,
        List<BookDTO> results
) {
}
