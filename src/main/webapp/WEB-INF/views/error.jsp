<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error | University Bookstore</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <div class="container">
        <div class="error-card">
            <div class="error-icon">&#9888;</div>
            <h2>Something Went Wrong</h2>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <p>${errorMessage}</p>
                </c:when>
                <c:otherwise>
                    <p>An unexpected error occurred. Please try again.</p>
                </c:otherwise>
            </c:choose>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">&#8592; Back to Home</a>
        </div>
    </div>
</body>
</html>
