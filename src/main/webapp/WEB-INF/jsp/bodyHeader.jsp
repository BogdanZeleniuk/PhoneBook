<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand">Contacts List</a>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <li>
                            <a class="btn btn-primary" role="button" href="<c:url value="/logout"/>">Logout</a>
                </li>
            </form>
        </div>
    </div>
</div>