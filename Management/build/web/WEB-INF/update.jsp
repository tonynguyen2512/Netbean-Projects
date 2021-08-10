<%-- 
    Document   : UpdateController
    Created on : Mar 13, 2021, 13:43:04 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action="MainController" method="POST">
        ID <input type="text" name ="id" value="${param.id}" required="false">
        Name <input type="text" name ="name">
        Description <input type="text" name ="description">
        Price <input type="text" name ="price">
        Cooking Time <input type="text" name ="cookingTime">
        Confirm <input type="submit" name ="action" value="Confirm">
    </form>
</html>

