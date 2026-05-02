package com.university.bookstore.dto;

/**
 * DTO (projection) for the custom inner join query result.
 * Carries book title paired with the author's name.
 */
public interface BookAuthorDTO {
    String getBookTitle();
    String getAuthorName();
    Long getBookId();
    String getGenre();
    Integer getPublishedYear();
    java.math.BigDecimal getPrice();
}
