package dev.samuel.literalura.repository;

import dev.samuel.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByExternalId(Long externalId);
}
