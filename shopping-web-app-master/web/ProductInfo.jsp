<%-- 
    Document   : ProductInfo
    Created on : Feb 7, 2018, 4:59:39 PM
    Author     : bas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-10 col-lg-8 text-center">
                    <h3 class="text-left">Product Info ::</h3>
                    <c:set var="p" value="${product}"/>
                    <c:set var="cutPos" value="${fn:indexOf(p.productline.productline, ' ')}"/>
                    <c:set var="path" value="${cutPos > 0 ? fn:substring(p.productline.productline, 0, cutPos) : p.productline.productline}"/>
                    <c:set var="imgFile" value="model-images/${fn:toLowerCase(path)}/${p.productcode}.jpg"/>
                    <img class="img-thumbnail"  src="${imgFile}" title="${p.productcode}" alt="image-banner" width="420">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-sm-10 col-lg-8">
                    <table>
                        <tr>
                            <td>Product Code</td>
                            <td><input type="text" value="${product.productcode}" disabled class="form-control" /></td>
                        </tr>
                        <tr>
                            <td>Product Name:</td>
                            <td><input type="text" value="${product.productname}" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>Product Scale:</td>
                            <td><input type="text" value="${product.productscale}" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>Product Vender:</td>
                            <td><input type="text" value="${product.productvendor}" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>Product Line:</td>
                            <td>
                                <input type="text" value="${product.productline.productline}" class="form-control" />
                                <input type="text" value="${product.productline.textdescription}" class="form-control" />
                            </td>
                        </tr>
                        <tr>
                            <td>Product Description</td>
                            <td><textarea rows="5" cols="60">${product.productdescription}</textarea></td>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td><input type="text" value="${product.msrp}" /></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
