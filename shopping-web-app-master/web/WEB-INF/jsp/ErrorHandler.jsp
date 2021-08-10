<%-- 
    Document   : ErrorHandler
    Created on : Mar 14, 2018, 3:49:04 PM
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
        <h1>OOP ! Something </h1> 
        <hr/>
        <h4>
            Error code: <span style="color: red">${pageContext.errorData.statusCode}</span><br/>
            Error message: <span style="color: red" >${pageContext.errorData.statusCode == 404 ? "Document Not Found": pageContext.exception}</span>
        </h4>
    </body>
</html>
