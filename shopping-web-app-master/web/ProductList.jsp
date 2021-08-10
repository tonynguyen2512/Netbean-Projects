<%-- 
    Document   : ProductList
    Created on : Feb 14, 2018, 1:40:05 PM
    Author     : bas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="/WEB-INF/tlds/MyTagLib.tld" prefix="mf" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tf" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .img-thumbnail {
                width: auto;
                margin: auto;
                height: 124px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include  page="/WEB-INF/jsp/header.jsp?title=Product List" flush="true" />
            <div class="row justify-content-center">
                <div class="col-sm-10 col-md-8"><hr />
                    <form action="ProductList" method="post">
                        <table class="table-striped table-bordered">
                            <tr>
                                <td>
                                    Search from Product name :
                                </td>
                                <td>
                                    <input type="text" required name="productName" class="form-control" value="${param.productName}"/>
                                </td>
                                <td>
                                    <button class="btn btn-primary">submit</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <hr />
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-10 col-lg-10">
                    <div class="row">
                        <c:forEach items="${products}" var="p" varStatus="vs" >
                            <tf:ProductCard p="${p}" />
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
