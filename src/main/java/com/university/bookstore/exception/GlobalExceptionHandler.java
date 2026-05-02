package com.university.bookstore.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global exception handler using @ControllerAdvice.
 * Catches common exceptions and redirects with user-friendly error messages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles DataIntegrityViolationException (e.g., duplicate email, FK constraint violations).
     * Redirects back with an error flash message.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                RedirectAttributes redirectAttributes) {
        String message = "Data integrity error: a record with the same unique value may already exist.";
        // Try to extract a more helpful message from the root cause
        if (ex.getRootCause() != null && ex.getRootCause().getMessage() != null) {
            String rootMsg = ex.getRootCause().getMessage();
            if (rootMsg.contains("Duplicate entry")) {
                message = "A record with that value already exists. Please use a unique value.";
            } else if (rootMsg.contains("foreign key constraint")) {
                message = "Cannot complete the operation because related records exist.";
            }
        }
        redirectAttributes.addFlashAttribute("errorMessage", message);
        return "redirect:/error";
    }

    /**
     * Handles EntityNotFoundException (e.g., attempting to edit/delete a non-existent record).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(EntityNotFoundException ex,
                                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    /**
     * Ignore missing static resource requests (favicon.ico, etc.) — let Spring return a 404.
     * Without this, the browser's automatic favicon request would trigger our error page.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public void handleNoResourceFound(NoResourceFoundException ex) throws NoResourceFoundException {
        // Re-throw so Spring MVC handles it with a standard 404 response
        throw ex;
    }

    /**
     * Catch-all handler for any other unexpected application exceptions.
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex,
                                          RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage",
                "An unexpected error occurred: " + ex.getMessage());
        return "redirect:/error";
    }
}
