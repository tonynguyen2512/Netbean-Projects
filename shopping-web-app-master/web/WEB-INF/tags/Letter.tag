<%-- 
    Document   : Letter
    Created on : Apr 18, 2018, 3:23:56 PM
    Author     : bas
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag body-content="scriptless" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<table>
    <tr>
        <td><jsp:invoke fragment="header" /></td>
    </tr>
    <tr style="border-bottom: bisque solid thin">
        <td><jsp:doBody /></td>
    </tr>
    <tr style="border-bottom: bisque solid thin">
        <td><jsp:invoke fragment="footer" /></td>
    </tr>
</table>