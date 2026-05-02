<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${isEdit ? 'Edit' : 'Add'} Book | University Bookstore</title>
    <meta name="description" content="${isEdit ? 'Edit an existing' : 'Add a new'} book to the university bookstore">
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
            <h1>${isEdit ? '&#9998; Edit' : '&#10133; Add New'} <span class="accent">Book</span></h1>
        </div>

        <!-- Book Form -->
        <div class="form-card">
            <form:form modelAttribute="book"
                       action="${pageContext.request.contextPath}/books/${isEdit ? 'edit/'.concat(book.id) : 'add'}"
                       method="post" id="bookForm">

                <div class="form-grid">
                    <!-- Title -->
                    <div class="form-group">
                        <label for="title">Title</label>
                        <form:input path="title" id="title" placeholder="Enter book title" cssErrorClass="error"/>
                        <form:errors path="title" cssClass="field-error"/>
                    </div>

                    <!-- Genre -->
                    <div class="form-group">
                        <label for="genre">Genre</label>
                        <form:input path="genre" id="genre" placeholder="e.g. Science Fiction" cssErrorClass="error"/>
                        <form:errors path="genre" cssClass="field-error"/>
                    </div>

                    <!-- Published Year -->
                    <div class="form-group">
                        <label for="publishedYear">Published Year</label>
                        <form:input path="publishedYear" id="publishedYear" type="number" placeholder="e.g. 2023" cssErrorClass="error"/>
                        <form:errors path="publishedYear" cssClass="field-error"/>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label for="price">Price ($)</label>
                        <form:input path="price" id="price" type="number" step="0.01" placeholder="e.g. 14.99" cssErrorClass="error"/>
                        <form:errors path="price" cssClass="field-error"/>
                    </div>

                    <!-- Author Dropdown -->
                    <div class="form-group full-width">
                        <label for="authorId">Author</label>
                        <select name="authorId" id="authorId">
                            <option value="">-- Select Author --</option>
                            <c:forEach var="author" items="${authors}">
                                <option value="${author.id}"
                                    ${book.author != null && book.author.id == author.id ? 'selected' : ''}>
                                    ${author.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Form Actions -->
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${isEdit ? '&#10004; Update Book' : '&#10133; Add Book'}
                        </button>
                        <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary">Cancel</a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>
