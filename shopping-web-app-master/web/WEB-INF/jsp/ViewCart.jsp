<%-- 
    Document   : ViewCart
    Created on : Feb 28, 2018, 2:44:02 PM
    Author     : bas
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
        <style>
            .img-thumbnail {
                height: 124px;
                overflow: hidden;
            }
            
            .img-thumnail img {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <h3>Your Cart ::</h3>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-11">
                    <form action="UpdateCart" method="post">
                        <table border="1" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Photo</th>
                                    <th>Detail</th>
                                    <th>Unit Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.cart.orders}" var="od" varStatus="vs">
                                    <c:set var="p" value="${od.product}" />
                                    <c:set var="cutPos" value="${fn:indexOf(p.productline.productline, ' ')}"/>
                                    <c:set var="path" value="${cutPos > 0 ? fn:substring(p.productline.productline, 0, cutPos) : p.productline.productline}"/>
                                    <c:set var="imgFile" value="model-images/${fn:toLowerCase(path)}/${p.productcode}.jpg"/>
                                    <tr>
                                        <th>${vs.count}</th>
                                        <td>
                                            <img src="${imgFile}" width="100">
                                        </td>
                                        <td>${p.productname}</td>
                                        <td>${p.msrp}</td>
                                        <td style="width: 15px;">
                                            <input style="width: 100%;"name="${p.productcode}" value="${od.quantityordered}" type="number" />
                                        </td>
                                        <td>${od.quantityordered * p.msrp}</td>
                                        <td>
                                            <input type="checkbox" name="deleteItems" value="${p.productcode}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr class="text-right">
                                    <td colspan="5">Total Price: </td>
                                    <td>
                                        <fmt:formatNumber pattern="#,###.###" value="${cart.totalPrice}" />
                                    </td>
                                <tr>
                                <tr class="text-right">
                                    <td>
                                        <a class="btn btn-primary" href="ProcessOrder">submit progress</a>
                                    </td>
                                    <td colspan="6">
                                        <button class="btn btn-primary" type="submit">Update</button>
                                    </td>
                                <tr>
                            </tbody>
                        </table>
                        <div class="text-right">
                            
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
