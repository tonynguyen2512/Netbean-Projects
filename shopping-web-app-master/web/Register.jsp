<%-- 
    Document   : Register
    Created on : Mar 21, 2018, 3:54:20 PM
    Author     : bas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register Form ::</h1> <hr />
        <form action="Register" method="post">
            First name : <input type="text" name="firstName" value="${param.firstName}"/> <br />
            Last name : <input type="text" name="lastName" value="${param.lastName}"/> <br />
            User name : <input type="text" name="userName" value="${param.userName}"/>
            <span style="color: red">${emptyFields.userName != null && param.userName != null ? '***':''}</span>
            <br />
            Email Address : <input type="text" name="emailAddress" value="${param.emailAddress}"/>
            <span style="color: red">${emptyFields.emailAddress != null && param.emailAddress != null ? '***':''}</span>
            <br />
            Bla Bla Bla : <input type="text" name="bla" value="${param.bla}"/> <br /> <br /> 
            <button>submit</button>
            <input type="submit" />
        </form>
    </body>
</html>
