<%-- 
    Document   : OrderSummary
    Created on : Apr 25, 2018, 4:45:00 PM
    Author     : bas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Customer Info: <br />
        <c:set var="customer" value="${order.customernumber}" />
            <b>name:</b> ${customer.customername} <br />
            Address: <br />
            ${customer.addressline1} <br />
            ${customer.addressline2} <br />
            ${customer.city}, ${customer.state}, ${customer.country} <br />
            ${customer.phone} <br />
            ---------------------------------------------------------<br />
            Your Order :: <br />
            <c:set var="sum" value="${0.0}" />
            ${order.ordernumber}, ${order.orderdate}, ${order.status} <br />
            --------- <br />
            <c:forEach items="${order.orderDetailCollection}" var="orderDetail">
                ${orderDetail.orderlinenumber} : ${orderDetail.product.productname},
                ${orderDetail.priceeach} x ${orderDetail.quantityordered} = ${orderDetail.priceeach * orderDetail.quantityordered} <br />
                <c:set var="sum" value="${sum + orderDetail.priceeach * orderDetail.quantityordered}" />
            </c:forEach>
            --------- <br />
            Total: ${sum}
            ============ <br />
            <a href="index1.jsp"><input type="button" value="OK"/></a>
    </body>
</html>
