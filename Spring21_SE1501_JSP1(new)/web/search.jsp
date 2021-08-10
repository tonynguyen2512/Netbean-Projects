import thien.dtos.UserDTO;
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
        <h1>Welcome: ${sessionScope.LOGIN_USER.fullName} </h1>

        <!--%= ((UserDTO) session.getAttribute("LOGIN_USER")).getFullName()%></h1>-->
        <form action="MainController">
            <input type="submit" name="action" value="Logout">
        </form>
        <form action="MainController">
            Search <input type="text" name="search" value="${param.search}" />
            <input type="submit" name="action" value="Search"/>
        </form>
    <c:if test="${requestScope.LIST_USER != null}">
        <c:if test="${not empty requestScope.LIST_USER}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Role ID</th>
                        <th>Password</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="user" varStatus="counter" items="${requestScope.LIST}">
                    <form action="MainController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${user.userID}</td>
                            <td>${user.fullName}</td>
                            <td>${user.roleID}</td>
                            <td>${user.password}</td>
                            <td>
                        <c:url var="delete" value="MainController" >
                            <c:param name="action" value="Delete"></c:param>
                            <c:param name="search" value="${param.search}"></c:param>
                            <c:param name="userID" value="${user.userID}"></c:param>
                        </c:url>
                        <a href="${delete}" >Delete</a>
                        </td>
                        <td>
                            <input type="submit" name="action" value="Update"/>
                            <input type="hidden" name="userID" value="${user.userID}"/>
                            <input type="hidden" name="fullName" value="${user.fullName}"/>
                            <input type="hidden" name="roleID" value="${user.roleID}"/>
                            <input type="hidden" name="search" value="${param.search}"/>
                        </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
                </tbody>
            </table>
        </c:if>
    </c:if>
</body>
</html>
