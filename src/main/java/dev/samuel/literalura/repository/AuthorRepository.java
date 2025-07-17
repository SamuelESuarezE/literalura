package dev.samuel.literalura.repository;

import dev.samuel.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);

    @Query("select distinct a from Author a join fetch a.books")
    List<Author> findAllWithBooks();

    @Query("select distinct a from Author a join fetch a.books where a.birthYear <= :year and a.deathYear >= :year")
    List<Author> findAllWithBooksAliveInYear(@Param("year") Integer year);
}