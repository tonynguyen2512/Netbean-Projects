<%-- 
    Document   : updateUser
    Created on : Feb 27, 2021, 1:31:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user Information page</title>
    </head>
    <body>
        <%
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String search = request.getParameter("search");
        %>
        <form action="MainController">
            User ID<input type="text" name="userID" value="<%= userID%>" readonly="true"/></br>
            Full Name<input type="text" name="fullName" value="<%= fullName%>" required="true"/></br>
            Role ID<input type="text" name="roleID" value="<%= roleID %>" required="true"/></br>
            <input type="hidden" name="search" value="<%= search %>" required="true"/>
            <input type="submit" name="action" value="Confirm" />
            <input type="reset" name="action" value="Reset"/>
        </form>
    </body>
</html>
