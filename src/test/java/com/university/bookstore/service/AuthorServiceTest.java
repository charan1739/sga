package com.university.bookstore.service;

import com.university.bookstore.entity.Author;
import com.university.bookstore.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AuthorService using Mockito.
 */
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author createSampleAuthor(Long id) {
        Author author = new Author("Jane Doe", "jane@test.com", "British", 1975);
        author.setId(id);
        return author;
    }

    @Test
    @DisplayName("findAll() should return all authors from repository")
    void testFindAll() {
        List<Author> expected = Arrays.asList(createSampleAuthor(1L), createSampleAuthor(2L));
        when(authorRepository.findAll()).thenReturn(expected);

        List<Author> result = authorService.findAll();

        assertThat(result).hasSize(2);
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById() should return author when it exists")
    void testFindById_found() {
        Author expected = createSampleAuthor(1L);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(expected));

        Author result = authorService.findById(1L);

        assertThat(result.getName()).isEqualTo("Jane Doe");
    }

    @Test
    @DisplayName("findById() should throw EntityNotFoundException when not found")
    void testFindById_notFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Author not found");
    }

    @Test
    @DisplayName("save() should delegate to repository.save()")
    void testSave() {
        Author author = createSampleAuthor(null);
        Author saved = createSampleAuthor(1L);
        when(authorRepository.save(any(Author.class))).thenReturn(saved);

        Author result = authorService.save(author);

        assertThat(result.getId()).isEqualTo(1L);
        verify(authorRepository).save(author);
    }

    @Test
    @DisplayName("update() should merge values and save")
    void testUpdate() {
        Author existing = createSampleAuthor(1L);
        Author updated = createSampleAuthor(1L);
        updated.setName("Updated Name");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(any(Author.class))).thenReturn(existing);

        Author result = authorService.update(1L, updated);

        assertThat(existing.getName()).isEqualTo("Updated Name");
        verify(authorRepository).save(existing);
    }

    @Test
    @DisplayName("deleteById() should throw when entity not found")
    void testDeleteById_notFound() {
        when(authorRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> authorService.deleteById(99L))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
