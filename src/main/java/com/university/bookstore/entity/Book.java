package com.university.bookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Book entity — represents a book in the system.
 * Has a many-to-one relationship with Author (each book belongs to one author).
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Published year is required")
    @Column(name = "published_year")
    private Integer publishedYear;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    // Many Books belong to one Author; eager fetch so author data is always loaded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // --- Constructors ---
    public Book() {}

    public Book(String title, String genre, Integer publishedYear, BigDecimal price, Author author) {
        this.title = title;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.price = price;
        this.author = author;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "'}";
    }
}
