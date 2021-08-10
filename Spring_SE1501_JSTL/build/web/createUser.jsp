<%-- 
    Document   : createUser
    Created on : Feb 27, 2021, 2:24:42 PM
    Author     : Admin
--%>

<%@page import="samples.dtos.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="MainController" method="POST">
            user Id <input type="text" name="userID"/>${requestScope.ERROR.userIDError}</br>
            </br>
            Full Name <input type="text" name="fullName"/></br>
            ${requestScope.ERROR.fullNameError}</br>
            Role ID <input type="text" name="roleID"/></br>
            ${requestScope.ERROR.roleIDError}</br>
            Password <input type="text" name="password"/></br>
            ${requestScope.ERROR.passwordError}</br>
            Confirm <input type="text" name="confirm"/></br>
            ${requestScope.ERROR.confirmError}</br>
            <input type="submit" name="action" value="Create"/>
            <input type="reset" name="Reset"/>
        </form>
    </body>
</html>
