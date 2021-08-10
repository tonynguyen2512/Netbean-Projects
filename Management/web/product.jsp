<%-- 
    Document   : index
    Created on : Mar 13, 2021, 9:58:50 AM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Management!</title>
    </head>
    <body>
        <h1>WELCOME TO FOOD MANAGEMENT!</h1>
    <form action="MainController" method="POST">
        <input type="text" name ="id">
        <input type="text" name ="name">
        <input type="text" name ="description">
        <input type="text" name ="price">
        <input type="text" name ="cookingTime"></br>
        <input type="submit" name ="action" value="Add">
    </form>
    <form action="MainController" method="POST">
        <input type="submit" name ="action" value="Search">
    </form>
    </body>
</html>
