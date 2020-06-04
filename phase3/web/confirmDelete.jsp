<%-- 
    Document   : confirmDelete
    Created on : Sep 23, 2019, 5:54:35 PM
    Author     : milesforet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
        <link rel="stylesheet" href="styles/phase1.css" type="text/css"/>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <c:choose>
            <c:when test="${user.firstName != null}">
            </c:when>
            <c:otherwise>
                <c:redirect url="/login.jsp"/>
            </c:otherwise>
        </c:choose>
        <p><c:out value="${user.firstName}"/> <a href="membership?action=logout">Logout</a></p>
        <h2>Are you sure you want to delete this product?</h2>
        <div class="row">
            <div class="column">
                <p>Code:</p>
                <p>Description:</p>
                <p>Price:</p>
            </div>
            <div class="column">
                <p><c:out value="${pp.code}"/></p>
                <p><c:out value="${pp.description}"/></p>
                <p><c:out value="${pp.priceCurrencyFormat}"/></p>

            </div>
        </div>
        <br>

        <button class='buttoncd' type="button"><a href="productManagement?action=actuallyDelete&amp;code=${pp.code}">Yes</a></button>
        <button class='buttoncd' type="button"><a href="productManagement?action=displayProducts">No</a></button>
    </body>
</html>
