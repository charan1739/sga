package com.university.bookstore.repository;

import com.university.bookstore.dto.BookAuthorDTO;
import com.university.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Book entity.
 * Contains a custom JPQL inner join query to fetch book + author data.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Custom JPQL query: INNER JOIN between Book and Author.
     * Returns a projection (BookAuthorDTO) containing book details and author name.
     */
    @Query("SELECT b.title AS bookTitle, a.name AS authorName, b.id AS bookId, " +
           "b.genre AS genre, b.publishedYear AS publishedYear, b.price AS price " +
           "FROM Book b INNER JOIN b.author a")
    List<BookAuthorDTO> findAllBooksWithAuthors();
}
