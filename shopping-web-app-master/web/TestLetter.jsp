<%-- 
    Document   : TestLetter
    Created on : Apr 18, 2018, 3:27:43 PM
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
        <tf:Letter>
            <jsp:attribute name="footer">
                <p>
                    Sincerely,
                    <br /><br />
                    ---------------------------------------<br />
                    John Donaldson
                </p>
            </jsp:attribute>
            <jsp:attribute name="header">
                John Donaldson <br />
                8 Sue Circle <br />
                Smithtown, CA 08067 909-555-5555 <br />
                john.donaldson@emailexample.com                
                <br />
                April 18, 2018
                <br />
                <br />
                George Gilhooley <br/>
                XYZ Company<br/>
                87 Delaware Road<br/>
                Hatfield, CA 08065<br/>
            </jsp:attribute>
            <jsp:body>
                <p>Dear Mr. Gilhooley,</p>
                <p>
                    I am writing to apply for the programmer position advertised in the Times Union. As requested, I am enclosing a completed job application, my certification, my resume, and three references.
                    The opportunity presented in this listing is very appealing to me, and I believe that my strong technical experience and education will make me a very competitive candidate for this position. The key strengths that I possess for success in this position include:
                    I have successfully designed, developed, and supported live use applications.
                    I strive for continued excellence.
                    I provide exceptional contributions to customer service for all customers.
                </p>
            </jsp:body>
        </tf:Letter>
    </body>
</html>
