<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books | University Bookstore</title>
    <meta name="description" content="Browse and manage the university bookstore's book collection">
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>

    <div class="container">
        <!-- Flash Messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">&#10004; ${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">&#10060; ${errorMessage}</div>
        </c:if>

        <!-- Page Header -->
        <div class="page-header">
            <h1>&#128214; <span class="accent">Books</span> Collection</h1>
            <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary">+ Add New Book</a>
        </div>

        <!-- Books Table (using inner join query results) -->
        <c:choose>
            <c:when test="${not empty books}">
                <div class="data-table-wrapper">
                    <table class="data-table" id="booksTable">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Title</th>
                                <th>Genre</th>
                                <th>Year</th>
                                <th>Price</th>
                                <th>Author</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${books}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><strong>${book.bookTitle}</strong></td>
                                    <td><span class="badge">${book.genre}</span></td>
                                    <td>${book.publishedYear}</td>
                                    <td>$${book.price}</td>
                                    <td>${book.authorName}</td>
                                    <td class="actions-cell">
                                        <a href="${pageContext.request.contextPath}/books/edit/${book.bookId}" class="btn btn-edit">&#9998; Edit</a>
                                        <a href="${pageContext.request.contextPath}/books/delete/${book.bookId}" class="btn btn-delete"
                                           onclick="return confirm('Are you sure you want to delete this book?')">&#128465; Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="data-table-wrapper">
                    <div class="empty-state">
                        <div class="icon">&#128218;</div>
                        <p>No books found. Add your first book to get started!</p>
                        <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary" style="margin-top:1rem;">+ Add Book</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
