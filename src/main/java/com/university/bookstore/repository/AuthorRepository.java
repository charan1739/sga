package com.university.bookstore.repository;

import com.university.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Author entity.
 * Inherits standard CRUD operations from JpaRepository.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
