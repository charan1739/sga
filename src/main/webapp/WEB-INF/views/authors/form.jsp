<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${isEdit ? 'Edit' : 'Add'} Author | University Bookstore</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <div class="container">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">&#10060; ${errorMessage}</div>
        </c:if>
        <div class="page-header">
            <h1>${isEdit ? '&#9998; Edit' : '&#10133; Add New'} <span class="accent">Author</span></h1>
        </div>
        <div class="form-card">
            <form:form modelAttribute="author"
                       action="${pageContext.request.contextPath}/authors/${isEdit ? 'edit/'.concat(author.id) : 'add'}"
                       method="post" id="authorForm">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <form:input path="name" id="name" placeholder="Full name" cssErrorClass="error"/>
                        <form:errors path="name" cssClass="field-error"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <form:input path="email" id="email" type="email" placeholder="author@example.com" cssErrorClass="error"/>
                        <form:errors path="email" cssClass="field-error"/>
                    </div>
                    <div class="form-group">
                        <label for="nationality">Nationality</label>
                        <form:input path="nationality" id="nationality" placeholder="e.g. British" cssErrorClass="error"/>
                        <form:errors path="nationality" cssClass="field-error"/>
                    </div>
                    <div class="form-group">
                        <label for="birthYear">Birth Year</label>
                        <form:input path="birthYear" id="birthYear" type="number" placeholder="e.g. 1965" cssErrorClass="error"/>
                        <form:errors path="birthYear" cssClass="field-error"/>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${isEdit ? '&#10004; Update Author' : '&#10133; Add Author'}
                        </button>
                        <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">Cancel</a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>
