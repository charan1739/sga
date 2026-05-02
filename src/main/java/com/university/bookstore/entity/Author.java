package com.university.bookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Author entity — represents a book author in the system.
 * Has a one-to-many relationship with Book.
 */
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotNull(message = "Birth year is required")
    @Column(name = "birth_year")
    private Integer birthYear;

    // One Author can have many Books; mappedBy refers to the 'author' field in Book entity
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // --- Constructors ---
    public Author() {}

    public Author(String name, String email, String nationality, Integer birthYear) {
        this.name = name;
        this.email = email;
        this.nationality = nationality;
        this.birthYear = birthYear;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Author{id=" + id + ", name='" + name + "'}";
    }
}
