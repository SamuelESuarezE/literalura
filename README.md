# 📚 Literalura 

A simple console-based Spring Boot 3 application that lets you search, store and browse public-domain books from Project Gutenberg via the Gutendex REST API.
Built with Java 21, Gradle and PostgreSQL.

![Literalura](./docs/literalura.gif)

## Features
- Search book by title (fetched from Gutendex)
- List books and authors stored locally
- Find authors alive in a specific year
- Filter books by language (Spanish, English, French, Portuguese)

## Quick Start
1. Clone the repository
2. Create a PostgreSQL database named 'literalura'
3. Configure resources/application.properties

```properties
spring.application.name=literalura
spring.datasource.url=jdbc:postgresql://localhost:<PORT>/literalura
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```
4. Run using IntelliJ 🛫✅