<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="headTag.jsp"/>

<body>
<jsp:include page="bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <br>
        <h4>Application error: </h4>
        <h2>${exception.message}</h2>
<!--
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
-->
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>