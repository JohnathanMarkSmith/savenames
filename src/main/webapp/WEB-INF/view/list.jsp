<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<center><h1>Save Names Database Example</h1></center>
<p>
    <center>[<a href="./add">Enter New Name</a> | <a href="./search">Search For Name</a> | <a href="./list">List All Name(s)</a>]</center><p>

<!-- Form -->
<div id="form-container">

    <h1>List of Names:</h1>

    <c:forEach items="${requestScope.names}" var="id">
        <c:out value="${id.message}"/><br>
    </c:forEach>

</div>

<center>
    By Johnathan Mark Smith
</center>


