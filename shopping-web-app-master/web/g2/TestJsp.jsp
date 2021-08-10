<%-- 
    Document   : TestJsp
    Created on : Jan 17, 2018, 3:57:16 PM
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
        <h1>Hello World!</h1>
        <hr />
        <!--<form action="/demo_web_g2/vs">-->
        <form action="../vs">
            x: <input type='text' name='x' /><br />
            y: <input type='text' name='y' /><br />
            <button>submit</button>
            
        </form>
        x = ${param.x} <br />
        y = ${param.y} <br />
        x + y = ${param.x + param.y} 
    </body>
</html>
