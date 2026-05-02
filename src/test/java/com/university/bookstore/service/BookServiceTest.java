package com.university.bookstore.service;

import com.university.bookstore.entity.Author;
import com.university.bookstore.entity.Book;
import com.university.bookstore.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookService using Mockito.
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book createSampleBook(Long id) {
        Author author = new Author("Author", "a@b.com", "US", 1980);
        author.setId(1L);
        Book book = new Book("Sample Book", "Fiction", 2023, new BigDecimal("12.99"), author);
        book.setId(id);
        return book;
    }

    @Test
    @DisplayName("findAll() should return all books from repository")
    void testFindAll() {
        List<Book> expected = Arrays.asList(createSampleBook(1L), createSampleBook(2L));
        when(bookRepository.findAll()).thenReturn(expected);

        List<Book> result = bookService.findAll();

        assertThat(result).hasSize(2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById() should return book when it exists")
    void testFindById_found() {
        Book expected = createSampleBook(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(expected));

        Book result = bookService.findById(1L);

        assertThat(result.getTitle()).isEqualTo("Sample Book");
    }

    @Test
    @DisplayName("findById() should throw EntityNotFoundException when book not found")
    void testFindById_notFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Book not found");
    }

    @Test
    @DisplayName("save() should delegate to repository.save()")
    void testSave() {
        Book book = createSampleBook(null);
        Book saved = createSampleBook(1L);
        when(bookRepository.save(any(Book.class))).thenReturn(saved);

        Book result = bookService.save(book);

        assertThat(result.getId()).isEqualTo(1L);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("update() should merge values and save")
    void testUpdate() {
        Book existing = createSampleBook(1L);
        Book updated = createSampleBook(1L);
        updated.setTitle("Updated Title");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(any(Book.class))).thenReturn(existing);

        Book result = bookService.update(1L, updated);

        assertThat(existing.getTitle()).isEqualTo("Updated Title");
        verify(bookRepository).save(existing);
    }

    @Test
    @DisplayName("deleteById() should throw when entity not found")
    void testDeleteById_notFound() {
        when(bookRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> bookService.deleteById(99L))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
