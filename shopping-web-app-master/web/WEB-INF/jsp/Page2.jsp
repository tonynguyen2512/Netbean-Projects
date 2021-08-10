<%-- 
    Document   : Page1
    Created on : Apr 18, 2018, 4:33:00 PM
    Author     : bas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp?title=Page #2" />
            <div class="row justify-content-center">
                <div class="col-8">
                    <h1>This is page 2</h1>
                </div>
            </div>
        </div>
    </body>
</html>
