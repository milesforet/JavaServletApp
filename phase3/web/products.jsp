<%-- 
    Document   : products
    Created on : Sep 23, 2019, 5:53:49 PM
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
        <h2>Products</h2>
        <table>
            <tr>
                <th>Code</th>
                <th>Description</th> 
                <th class='price'>Price</th>
                <th> </th>
                <th> </th>
            </tr>

            <c:forEach var="p" items="${products}">
                <tr>
                    <td><c:out value='${p.code}'/></td>
                    <td><c:out value='${p.description}'/></td>
                    <td><c:out value='${p.priceCurrencyFormat}'/></td>
                    <td><a href="productManagement?action=updateProduct&amp;code=${p.code}">Edit</a></td>
                    <td><a href="productManagement?action=confirmDelete&amp;code=${p.code}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="product.jsp" method="post">
            <button type="submit">Add Product</button>
        </form>
    </body>
</html>
