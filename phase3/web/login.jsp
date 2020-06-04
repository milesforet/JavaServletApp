<%-- 
    Document   : login
    Created on : Sep 23, 2019, 5:54:47 PM
    Author     : milesforet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="styles/phase1.css" type="text/css"/>
    </head>
    <body class="bodyclass">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <h2>Login</h2>
        <form action="./membership?action=login" method="post">
            ${alert}
            <label>Email</label>
            <input type="text" name="email" required/> <br>
            <label>Password</label>
            <input type="password" name="password" required/> <br>
            <button class="submitbutton" type="submit">Login</button><br>        
        </form>
        <a href="membership?action=signup">New user? Click here to register.</a>
    </body>
</html>
