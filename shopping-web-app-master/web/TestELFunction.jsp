<%-- 
    Document   : TestELFunction
    Created on : Mar 28, 2018, 2:14:35 PM
    Author     : bas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/MyTagLib.tld" prefix="mf" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="mtf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <tf:Transform mode="upper">
            asdfasdfasd
        </tf:Transform>
        <hr />
        ${mf:addMethod(param.x, param.y)} <br />
        &lt;mf:transform mode="upper"&gt;
        <mf:TranformTag mode="upper" color="orange">
            Developing Applications with NetBeans IDE User's Guide
            For additional help documentation about using the IDE, 
            click any of the following chapter titles to open the chapter of the developing applications with 
            Netbeans IDE user' s guide in your web browser
        </mf:TranformTag>
        <hr />
        &lt;mf:transform mode="hide"&gt;
        <mf:TranformTag mode="hide">
            Developing Applications with NetBeans IDE User's Guide
            For additional help documentation about using the IDE, 
            click any of the following chapter titles to open the chapter of the developing applications with 
            Netbeans IDE user' s guide in your web browser
        </mf:TranformTag>
        <hr />
        &lt;mf:transform mode="xxx"&gt;
        <mf:TranformTag mode="xxx" color="darkblue">
            Developing Applications with NetBeans IDE User's Guide
            For additional help documentation about using the IDE, 
            click any of the following chapter titles to open the chapter of the developing applications with 
            Netbeans IDE user' s guide in your web browser
        </mf:TranformTag>
    </body>
</html>
