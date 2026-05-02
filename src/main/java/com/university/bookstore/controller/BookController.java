package com.university.bookstore.controller;

import com.university.bookstore.dto.BookAuthorDTO;
import com.university.bookstore.entity.Book;
import com.university.bookstore.service.AuthorService;
import com.university.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for Book CRUD operations.
 * The list endpoint uses the custom inner join query from BookService.
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /** GET /books — list all books using the inner join query (book + author data) */
    @GetMapping
    public String listBooks(Model model) {
        List<BookAuthorDTO> booksWithAuthors = bookService.findAllBooksWithAuthors();
        model.addAttribute("books", booksWithAuthors);
        return "books/list";
    }

    /** GET /books/add — show blank add form with author dropdown */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("isEdit", false);
        return "books/form";
    }

    /** POST /books/add — save new book */
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult bindingResult,
                          @RequestParam("authorId") Long authorId,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("isEdit", false);
            return "books/form";
        }
        try {
            book.setAuthor(authorService.findById(authorId));
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Could not add book: a data integrity error occurred.");
            return "redirect:/books/add";
        }
        return "redirect:/books";
    }

    /** GET /books/edit/{id} — show pre-filled edit form */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("isEdit", true);
        return "books/form";
    }

    /** POST /books/edit/{id} — update existing book */
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             @RequestParam("authorId") Long authorId,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("isEdit", true);
            return "books/form";
        }
        try {
            book.setAuthor(authorService.findById(authorId));
            bookService.update(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Could not update book: a data integrity error occurred.");
            return "redirect:/books/edit/" + id;
        }
        return "redirect:/books";
    }

    /** GET /books/delete/{id} — delete a book and redirect */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        return "redirect:/books";
    }
}
