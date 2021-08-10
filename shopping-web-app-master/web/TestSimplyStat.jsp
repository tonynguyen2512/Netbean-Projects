<%-- 
    Document   : TestSimplyStat
    Created on : Apr 18, 2018, 1:54:02 PM
    Author     : bas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
          int x[] = {1, 2, 5, 7, 9};
          pageContext.setAttribute("x", x);
        %>
        <tf:SimplyStat values="${x}" output1="min" output2="max" output3="average" />
        <hr />
        <tf:SimplyStat2 values="${x}">
          Min = ${min} <br />
          Max = ${max} <br />
          Average = ${average} <br/>
          <hr />
        </tf:SimplyStat2>
          at end <br />
          Min = ${min} <br />
          Max = ${max} <br />
          Average = ${average} <br/>
          <hr />
    </body>
</html>
