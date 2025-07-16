package dev.samuel.literalura;

import dev.samuel.literalura.service.GutendexApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final GutendexApiService gutendexApiService;

    public LiteraluraApplication(GutendexApiService gutendexApiService) {
        this.gutendexApiService = gutendexApiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Literalura!");
        System.out.println("Enter the title of a book: ");
        var title = scanner.nextLine();

        var optionalBook = gutendexApiService.searchBookByTitle(title);

        optionalBook.ifPresent(System.out::println);

    }
}
