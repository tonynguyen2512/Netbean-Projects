<%-- 
    Document   : Transform
    Created on : Apr 4, 2018, 3:39:04 PM
    Author     : bas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="I dont't know" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="mode" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@attribute name="color" required="false" rtexprvalue="true" %>
<%@tag body-content="scriptless" %>

<%-- any content can be specified here e.g.: --%>
<c:set var="color" value="${color == null ? 'gray' : color}" />
<c:choose>
    <c:when test="${mode == 'upper'}">
        <p style="text-transform: uppercase; color: ${color}">
            <jsp:doBody />
        </p>
    </c:when>
    <c:when test="${mode == 'hide'}">
    </c:when>
    <c:otherwise>
        <p style="color: ${color}">
            <jsp:doBody />
        </p>
    </c:otherwise>
</c:choose>