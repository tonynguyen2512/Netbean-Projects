<%-- 
    Document   : ProductList
    Created on : Feb 14, 2018, 1:40:05 PM
    Author     : bas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function() {
                $('#example').DataTable();
            } );
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                    
                <div class="col-sm-1 col-md-2"></div>
                <div class="col-sm-10 col-md-8">
                    <h3>Product List ::</h3> <hr />
                    <form action="ProductList" method="post">
                        <table id="example" class="table-striped table-bordered">
                            <tr>
                                <td>
                                    Search from Product name :
                                </td>
                                <td>
                                    <input type="text" required name="productName" class="form-control" />
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
            <div class="row">
                
                <div class="col-sm-1 col-md-2"></div>
                <div class="col-sm-10 col-md-8">
                    <table class="table-striped table-bordered">
                        <thead>
                            <th>No</th>
                            <th>Product Name</th>
                            <th>Product Scale</th>
                            <th>Product Line</th>
                            <th>Price</th>
                        </thead>
                        <c:forEach items="${products}" var="p" varStatus="vs">
                            <tr class="pr-4">
                                <td>${vs.count}</td>
                                <td><a href="ProductManager?productCode=${p.productcode}">${p.productname}</a></td>
                                <td class="text-center">${p.productscale}</td>
                                <td>${p.productline.productline}</td>
                                
                                <td class="text-right">${p.msrp}</td>
                            </tr>
                        </c:forEach>
                    </table>        
                </div>
            </div>
        </div>
    </body>
</html>
