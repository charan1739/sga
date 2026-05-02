package com.university.bookstore.controller;

import com.university.bookstore.entity.Author;
import com.university.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for Author CRUD operations.
 * Maps requests under /authors to the appropriate service calls and JSP views.
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /** GET /authors — list all authors */
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    /** GET /authors/add — show blank add form */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("isEdit", false);
        return "authors/form";
    }

    /** POST /authors/add — save new author */
    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute("author") Author author,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "authors/form";
        }
        try {
            authorService.save(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author added successfully!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Could not add author: a record with the same email may already exist.");
            return "redirect:/authors/add";
        }
        return "redirect:/authors";
    }

    /** GET /authors/edit/{id} — show pre-filled edit form */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        model.addAttribute("isEdit", true);
        return "authors/form";
    }

    /** POST /authors/edit/{id} — update existing author */
    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") Author author,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "authors/form";
        }
        try {
            authorService.update(id, author);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Could not update author: a record with the same email may already exist.");
            return "redirect:/authors/edit/" + id;
        }
        return "redirect:/authors";
    }

    /** GET /authors/delete/{id} — delete an author and redirect */
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            authorService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Author deleted successfully!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Cannot delete this author because they have associated books. Remove the books first.");
        }
        return "redirect:/authors";
    }
}
