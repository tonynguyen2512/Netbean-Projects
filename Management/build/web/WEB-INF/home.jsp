<%-- 
    Document   : home
    Created on : Mar 13, 2021, 13:09:08 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <h1>WELCOME TO FOOD MANAGEMENT!</h1>
    <form action="MainController" method="POST">
        <input type="text" name ="id">
        <input type="text" name ="name">
        <input type="text" name ="description">
        <input type="text" name ="price">
        <input type="text" name ="cookingTime">
        <input type="submit" name ="action" value="Add">
    </form>
    <form action="MainController" method="POST">
        <input type="submit" name ="action" value="Search">
    </form>
</html>
