package com.university.bookstore.service;

import com.university.bookstore.entity.Author;
import com.university.bookstore.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Author entity.
 * Encapsulates business logic and repository interactions.
 */
@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /** Retrieve all authors */
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    /** Find a single author by ID; throws EntityNotFoundException if not found */
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
    }

    /** Save a new author */
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    /** Update an existing author — merges new values onto the persisted entity */
    public Author update(Long id, Author updatedAuthor) {
        Author existing = findById(id); // throws if not found
        existing.setName(updatedAuthor.getName());
        existing.setEmail(updatedAuthor.getEmail());
        existing.setNationality(updatedAuthor.getNationality());
        existing.setBirthYear(updatedAuthor.getBirthYear());
        return authorRepository.save(existing);
    }

    /** Delete an author by ID */
    public void deleteById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
