package com.university.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController — handles the root URL and the generic error page.
 */
@Controller
public class HomeController {

    /** Redirect root to the books list */
    @GetMapping("/")
    public String home() {
        return "redirect:/books";
    }

    /** Generic error page that displays flash-attributed error messages */
    @GetMapping("/error")
    public String errorPage(Model model) {
        return "error";
    }
}
