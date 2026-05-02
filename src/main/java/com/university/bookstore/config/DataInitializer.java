package com.university.bookstore.config;

import com.university.bookstore.entity.Author;
import com.university.bookstore.entity.Book;
import com.university.bookstore.repository.AuthorRepository;
import com.university.bookstore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * DataInitializer — seeds the database with 10 Authors and 10 Books on startup.
 * Only inserts data if the tables are empty (idempotent).
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        // Only seed data if tables are empty
        if (authorRepository.count() > 0 || bookRepository.count() > 0) {
            log.info("Database already contains data — skipping initialization.");
            return;
        }

        log.info("Seeding database with sample Authors and Books...");

        // --- 10 Authors ---
        Author a1  = authorRepository.save(new Author("George Orwell",        "orwell@literature.com",     "British",    1903));
        Author a2  = authorRepository.save(new Author("Jane Austen",          "austen@literature.com",     "British",    1775));
        Author a3  = authorRepository.save(new Author("Mark Twain",           "twain@literature.com",      "American",   1835));
        Author a4  = authorRepository.save(new Author("Gabriel Garcia Marquez","marquez@literature.com",   "Colombian",  1927));
        Author a5  = authorRepository.save(new Author("Haruki Murakami",      "murakami@literature.com",   "Japanese",   1949));
        Author a6  = authorRepository.save(new Author("Chimamanda Adichie",   "adichie@literature.com",   "Nigerian",   1977));
        Author a7  = authorRepository.save(new Author("Leo Tolstoy",          "tolstoy@literature.com",   "Russian",    1828));
        Author a8  = authorRepository.save(new Author("Toni Morrison",        "morrison@literature.com",  "American",   1931));
        Author a9  = authorRepository.save(new Author("Franz Kafka",          "kafka@literature.com",     "Czech",      1883));
        Author a10 = authorRepository.save(new Author("Isabel Allende",       "allende@literature.com",   "Chilean",    1942));

        // --- 10 Books (each linked to an author) ---
        bookRepository.save(new Book("1984",                          "Dystopian",        1949, new BigDecimal("12.99"), a1));
        bookRepository.save(new Book("Pride and Prejudice",           "Romance",          1813, new BigDecimal("9.99"),  a2));
        bookRepository.save(new Book("Adventures of Huckleberry Finn","Adventure",        1884, new BigDecimal("11.50"), a3));
        bookRepository.save(new Book("One Hundred Years of Solitude", "Magical Realism",  1967, new BigDecimal("14.99"), a4));
        bookRepository.save(new Book("Norwegian Wood",                "Literary Fiction", 1987, new BigDecimal("13.25"), a5));
        bookRepository.save(new Book("Americanah",                    "Literary Fiction", 2013, new BigDecimal("15.00"), a6));
        bookRepository.save(new Book("War and Peace",                 "Historical Fiction",1869, new BigDecimal("18.50"), a7));
        bookRepository.save(new Book("Beloved",                       "Historical Fiction",1987, new BigDecimal("14.00"), a8));
        bookRepository.save(new Book("The Metamorphosis",             "Absurdist Fiction",1915, new BigDecimal("7.99"),  a9));
        bookRepository.save(new Book("The House of the Spirits",      "Magical Realism",  1982, new BigDecimal("13.75"), a10));

        log.info("Database seeding complete: 10 Authors and 10 Books inserted.");
    }
}
