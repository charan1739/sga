<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authors | University Bookstore</title>
    <meta name="description" content="Browse and manage authors in the university bookstore">
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <div class="container">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">&#10004; ${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">&#10060; ${errorMessage}</div>
        </c:if>
        <div class="page-header">
            <h1>&#9997; <span class="accent">Authors</span> Directory</h1>
            <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary">+ Add New Author</a>
        </div>
        <c:choose>
            <c:when test="${not empty authors}">
                <div class="data-table-wrapper">
                    <table class="data-table" id="authorsTable">
                        <thead>
                            <tr>
                                <th>#</th><th>Name</th><th>Email</th><th>Nationality</th><th>Birth Year</th><th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="author" items="${authors}" varStatus="s">
                                <tr>
                                    <td>${s.index + 1}</td>
                                    <td><strong>${author.name}</strong></td>
                                    <td>${author.email}</td>
                                    <td><span class="badge">${author.nationality}</span></td>
                                    <td>${author.birthYear}</td>
                                    <td class="actions-cell">
                                        <a href="${pageContext.request.contextPath}/authors/edit/${author.id}" class="btn btn-edit">&#9998; Edit</a>
                                        <a href="${pageContext.request.contextPath}/authors/delete/${author.id}" class="btn btn-delete"
                                           onclick="return confirm('Delete this author and all their books?')">&#128465; Delete</a>
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
                        <div class="icon">&#9997;</div>
                        <p>No authors found. Add your first author!</p>
                        <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary" style="margin-top:1rem;">+ Add Author</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
