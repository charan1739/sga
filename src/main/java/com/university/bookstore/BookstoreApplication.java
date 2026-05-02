package com.university.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main entry point for the University Bookstore application.
 * Extends SpringBootServletInitializer for WAR deployment (required for JSP support).
 */
@SpringBootApplication
public class BookstoreApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BookstoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}
