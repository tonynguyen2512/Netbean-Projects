<%-- 
    Document   : shopping
    Created on : Feb 23, 2021, 3:29:00 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Milk Tea Luxury</title>
    </head>
    <body>
        <h1>Welcome to my Tea Farm!</h1>
        <form action="MainController">
            Choose your tea:
            <select name="cmbTea">
                <option value="T001-Vinamilk-10">Vinamilk</option>
                <option value="T002-Bubble Tea-20">Bubble Tea</option>
                <option value="T003-Pink Tea-40">Pink Tea</option>
            </select>
            <input type="submit" name="action" value="Add"/>
            <input type="submit" name="action" value="Add"/>
        </form>
        <%
            String message = (String)request.getAttribute("MESSAGE");
            if (message!=null) {
                %>
                <h1>
                    <%= message %>
                </h1>
                    <%
            }
        %>
    </body>
</html>
