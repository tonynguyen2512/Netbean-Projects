<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user Information page</title>
    </head>
    <body>
        <%
            String ID = request.getParameter("ID");
            String Name = request.getParameter("Name");
            String search = request.getParameter("search");
        %>
        <form action="MainController">
            Food ID<input type="text" name="ID" value="<%=ID%>" readonly="true"/></br>
            Food Name<input type="text" name="fullName" value="<%=Name%>" required="true"/></br>
            <input type="hidden" name="search" value="<%= search %>" required="true"/>
            <input type="submit" name="action" value="Confirm" />
            <input type="reset" name="action" value="Reset"/>
        </form>
    </body>
</html>
