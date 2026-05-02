package com.university.bookstore.repository;

import com.university.bookstore.dto.BookAuthorDTO;
import com.university.bookstore.entity.Author;
import com.university.bookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Repository integration tests using @DataJpaTest.
 * Uses H2 in-memory database via the 'test' profile.
 */
@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author savedAuthor;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        // Seed a test author and two books
        savedAuthor = authorRepository.save(
                new Author("Test Author", "test@example.com", "American", 1980));

        bookRepository.save(new Book("Book One", "Fiction", 2020, new BigDecimal("10.00"), savedAuthor));
        bookRepository.save(new Book("Book Two", "Non-Fiction", 2021, new BigDecimal("15.50"), savedAuthor));
    }

    @Test
    @DisplayName("findAll() should return all persisted books")
    void testFindAll() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle).containsExactlyInAnyOrder("Book One", "Book Two");
    }

    @Test
    @DisplayName("findAllBooksWithAuthors() custom JPQL inner join should return book+author data")
    void testFindAllBooksWithAuthors() {
        List<BookAuthorDTO> results = bookRepository.findAllBooksWithAuthors();

        assertThat(results).hasSize(2);
        // Verify the projection fields
        assertThat(results).extracting(BookAuthorDTO::getAuthorName)
                .containsOnly("Test Author");
        assertThat(results).extracting(BookAuthorDTO::getBookTitle)
                .containsExactlyInAnyOrder("Book One", "Book Two");
        // Verify price is present
        assertThat(results.get(0).getPrice()).isNotNull();
    }
}
