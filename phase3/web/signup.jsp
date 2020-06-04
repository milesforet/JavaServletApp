<%-- 
    Document   : signup
    Created on : Sep 23, 2019, 5:54:56 PM
    Author     : milesforet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="styles/phase1.css" type="text/css"/>
    </head>
    <body class="bodyclass">
        <h2>Sign-Up Form</h2>
        <form action="membership?action=signup" method="post">
            <label>First Name</label>
            <input type="text" name="firstName" required/> <br>
            <label>Last Name</label>
            <input type="text" name="lastName" required/> <br>
            <label>Email</label>
            <input type="email" name="email" required/> <br>
            <label>Password</label>
            <input type="password" name="password" required/> <br>
            <button class="submitbutton" type="submit">Sign Up</button>
        </form>
    </body>
</html>
