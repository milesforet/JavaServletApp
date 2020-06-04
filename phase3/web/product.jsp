<%-- 
    Document   : product
    Created on : Sep 23, 2019, 5:53:57 PM
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
    <body class="bodyclass">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:choose>
            <c:when test="${user.firstName != null}">
            </c:when>
            <c:otherwise>
                <c:redirect url="/login.jsp"/>
            </c:otherwise>
        </c:choose>
        <p><c:out value="${user.firstName}"/> <a href="membership?action=logout">Logout</a></p>
        <h2>Product</h2>
        <form action="productManagement?action=addProduct" method="post">
            <label>Code:</label>
            <input type="text" name="code" value="<c:out value='${pp.code}' />"> <br>
            <label>Description:</label>
            <textarea name='description' rows="3" cols="25" ><c:out value='${pp.description}' /></textarea><br>
            <label>Price:</label>
            <input type="text" name="price" value="<c:out value='${pp.price}' />"> <br>
            <input type="submit" value="Update Product" >
        </form>
        <form action="productManagement?action=displayProducts" method="post">
            <input type="submit" value="View Products">
        </form>
    </body>
</html>
