<%@page import="java.util.List"%>
<%@page import="thien.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                //user = new UserDTO ("","","","");
                /*response.sendRedirect("login.html");
            return;*/
                response.sendRedirect("login.html");
                return;
            }
            String searchValue = request.getParameter("search");
            if (searchValue == null) {
                searchValue = "";
            }
        %>
        <h1>Welcome: <%= ((UserDTO) session.getAttribute("LOGIN_USER")).getFullName()%></h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout">
        </form>
        <form action="MainController">
            Search <input type="text" name="search" value="<%= searchValue%>" />
            <input type="submit" name="action" value="Search"/>
        </form>
        <%
            List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (list != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Role ID</th>
                    <th>Password</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserDTO dto : list) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= dto.getUserID()%></td>
                    <td><%= dto.getFullName()%></td>
                    <td><%= dto.getRole()%></td>
                    <td><%= dto.getPassword()%></td>
                    <td>
                        <a href="MainController?search=<%=request.getParameter("search")%>&action=Delete&userID=<%=dto.getUserID()%>" >Delete</a>
                    </td>

                </tr> 
                <%
                    }
                %>
            </tbody>
            <%
                }
            %>
    </body>
</html>
