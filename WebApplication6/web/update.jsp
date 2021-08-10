<%-- 
    Document   : UpdateController
    Created on : Mar 13, 2021, 13:43:04 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action="MainController" method="POST">
        <input type="text" name ="id" value="${param.id}" required="false">
        <input type="text" name ="name">
        <input type="text" name ="description">
        <input type="text" name ="price">
        <input type="text" name ="cookingTime">
        <input type="submit" name ="action" value="Confirm">
    </form>
</html>

