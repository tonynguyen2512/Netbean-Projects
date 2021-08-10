<%-- 
    Document   : search
    Created on : Mar 13, 2021, 11:25:06 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Price</th>
                <th>cookingTime</th>
                <th>description</th>
                <th>createDate</th>
                <th>Update</th>
                <th>Delete</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "food" varStatus="count" items="${requestScope.LIST}">
                <tr>
                    <td>${count.count}</td>
                    <td>${food.name}</td>
                    <td>${food.price}</td>
                    <td>${food.cookingTime}</td>
                    <td>${food.description}</td>
                    <td>${food.createDate}</td>
                    <td><a href="MainController?action=Update&id=${food.id}">Update</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</html>
