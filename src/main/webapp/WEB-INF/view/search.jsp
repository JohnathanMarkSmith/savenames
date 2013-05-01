<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<title>SaveNames By Johnathan Mark Smith</title>
<body>


<center><h1>Save Names Database Example</h1></center>
<p>
    <center>[<a href="./add">Enter New Name</a> | <a href="./search">Search For Name</a> | <a href="./list">List All Name(s)</a>]</center><p>

<!-- Form -->
<div id="form-container">

<c:url var="saveUrl" value="/search.html"/>

<form:form modelAttribute="message" action="${saveUrl}" >
    Enter Names to Search For:
    <form:input path="message" class="Name required" required="required" name="message" type="text" maxlength="16" />

    <input type="submit" class="submit-button" id="submit" name="submit"  value="submit">
</form:form>
    <p>

    <h1>List of Names Found (<c:out value="${fn:length(requestScope.names)}"/>):</h1>

    <c:forEach items="${requestScope.names}" var="id">
        <c:out value="${id.message}"/><br>
    </c:forEach>

</div>

<center>
    By Johnathan Mark Smith
</center>


