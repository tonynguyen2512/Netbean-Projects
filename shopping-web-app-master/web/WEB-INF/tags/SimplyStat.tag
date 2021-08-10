<%-- 
    Document   : SimplyStat
    Created on : Apr 18, 2018, 1:40:56 PM
    Author     : bas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag body-content="empty" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="values" type="int[]" rtexprvalue="true" required="true"%>
<%@tag dynamic-attributes="elements" %>
<c:set var="min" value="${values[0]}" />
<c:set var="max" value="${values[0]}" />
<c:set var="sum" value="${0.0}" />
<c:forEach items="${values}" var="number" varStatus="vs">
  <c:set var="sum" value="${sum + number}" />
  <c:set var="max" value="${number > max ? number : max}" />
  <c:set var="min" value="${number < min ? number : min}" />
  <c:set var="count" value="${vs.count}" />
</c:forEach>


<%-- any content can be specified here e.g.: --%>
<c:forEach items="${elements}" var="entry">
   <c:choose>
       <c:when test="${entry.value == 'min'}" >
           Min = ${min} <br />
       </c:when>
       <c:when test="${entry.value == 'max'}" >
           Max = ${max} <br />
       </c:when>
       <c:when test="${entry.value == 'average'}" >
           Average = ${sum / count} <br />
       </c:when>
   </c:choose>
</c:forEach>
