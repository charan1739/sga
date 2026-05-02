package com.university.bookstore.service;

import com.university.bookstore.dto.BookAuthorDTO;
import com.university.bookstore.entity.Book;
import com.university.bookstore.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Book entity.
 * Encapsulates business logic and repository interactions.
 */
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** Retrieve all books */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /** Find a single book by ID; throws EntityNotFoundException if not found */
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    /** Save a new book */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /** Update an existing book — merges new values onto the persisted entity */
    public Book update(Long id, Book updatedBook) {
        Book existing = findById(id); // throws if not found
        existing.setTitle(updatedBook.getTitle());
        existing.setGenre(updatedBook.getGenre());
        existing.setPublishedYear(updatedBook.getPublishedYear());
        existing.setPrice(updatedBook.getPrice());
        existing.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(existing);
    }

    /** Delete a book by ID */
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    /** Custom inner join query — returns book titles with author names */
    public List<BookAuthorDTO> findAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }
}
