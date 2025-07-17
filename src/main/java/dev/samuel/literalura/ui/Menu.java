package dev.samuel.literalura.ui;

import dev.samuel.literalura.model.Book;
import dev.samuel.literalura.service.AuthorService;
import dev.samuel.literalura.service.BookService;
import dev.samuel.literalura.service.GutendexApiService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    private final GutendexApiService gutendexApiService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final List<String> allowedLanguages = List.of("es", "en", "fr", "pt");

    public Menu(GutendexApiService gutendexApiService, BookService bookService, AuthorService authorService) {
        this.gutendexApiService = gutendexApiService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void displayMenu() {
        System.out.println("");
        System.out.println("Welcome to Literalura!");

        var option = -1;

        while (option != 0) {
            System.out.println("""
            
            Select an option:
            1. Search book by title
            2. See registered books
            3. See registered authors
            4. See authors alive in a determined year
            5. See books by language
            0. Exit  
            """);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    seeRegisteredBooks();
                    break;
                case 3:
                    seeRegisteredAuthors();
                    break;
                case 4:
                    seeAuthorsAliveInADeterminedYear();
                    break;
                case 5:
                    seeBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Exiting... See you later :)");
                    break;
            }
        }
    }

    public void searchBookByTitle() {
        System.out.println("Enter the title of the book:");
        var title = scanner.nextLine();
        Optional<Book> bookOptional= gutendexApiService.searchBookByTitle(title);

        if (bookOptional.isPresent()) {
            System.out.println("Book registered:");
            System.out.println(bookOptional.get());
        }
    }

    public void seeRegisteredBooks() {
        bookService.getAllBooks().forEach(System.out::println);
    }

    public void seeRegisteredAuthors() {
        authorService.getAllAuthorsWithBooks().forEach(System.out::println);
    }

    public void seeAuthorsAliveInADeterminedYear() {
        System.out.println("Enter a year: ");
        var year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Authors alive in: " + year);
        authorService.getAuthorsAliveInDeterminedYear(year).forEach(System.out::println);
    }
    public void seeBooksByLanguage() {
        System.out.println("""
        Enter the language to search the books:
        es - Spanish
        en - English
        fr - French
        pt - Portuguese
        """);
        var lang = scanner.nextLine();

        if (!allowedLanguages.contains(lang)) {
            System.out.println("Language not supported, please try again.");
            return;
        }

        var books = bookService.getAllBooksByLanguage(lang);

        if (books == null) {
            System.out.println("No books found in " + lang);
            return;
        }

        System.out.println("Books in " + lang);

        books.forEach(System.out::println);
    }
}
