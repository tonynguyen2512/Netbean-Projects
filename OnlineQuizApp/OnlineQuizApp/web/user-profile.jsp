<%-- 
    Document   : user-profile
    Created on : Jul 22, 2021, 12:16:13 PM
    Author     : Mr Hien Khoa
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>User profile</title>
        <style media="screen">
            body {
                background: #4e73df
            }

            .form-control:focus {
                box-shadow: none;
                border-color: #BA68C8
            }

            .back:hover {
                color: #682773;
                cursor: pointer
            }

            .labels {
                font-size: 11px
            }

            .add-experience:hover {
                background: #BA68C8;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8
            }
        </style>
    </head>
    <body>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <a align="center" href="login.html">Please login first</a>
        </c:if>
        <c:if test="${!empty sessionScope.LOGIN_USER}">
            <div class="container rounded bg-white mt-5 mb-5">
                <form action="UpdateController" method="POST">

                    <div class="row">
                        <div class="col-md-3 border-right">
                            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${sessionScope.LOGIN_USER.fullname}</span><span class="text-black-50">${sessionScope.LOGIN_USER.email}</span><span> </span></div>
                        </div>
                        <div class="col-md-5 border-right">
                            <div class="p-3 py-5">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h4 class="text-right">Profile Settings</h4>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12"><label class="labels">Full Name</label><input type="text" class="form-control" placeholder="Enter Full Name" name="Name" value="${sessionScope.LOGIN_USER.fullname}"></div>
                                    <div class="col-md-12"><label class="labels">Phone Number</label><input type="text" class="form-control" placeholder="Enter Phone number" name="Phone" value="${sessionScope.LOGIN_USER.phone}"></div>
                                    <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control"  value="${sessionScope.LOGIN_USER.email}" readonly="true"></div>
                                    <div class="col-md-12"><label class="labels">Address</label><input type="text" class="form-control" placeholder="Enter Address" name="Address" value="${sessionScope.LOGIN_USER.address}"></div>
                                </div>
                                <input type="hidden" name="check" value="profile">
                                <div class="row mt-3">
                                    <div class="col-md-6 mt-5"><input class="btn btn-primary" type="submit" value="Save Profile"></div>
                                    <div class="col-md-6 mt-5">
                                        <c:if test="${sessionScope.LOGIN_USER.role =='AD'}">
                                            <a class="btn btn-danger" href="dashboardadmin.jsp" role="button">Cancel</a>
                                        </c:if>
                                        <c:if test="${sessionScope.LOGIN_USER.role =='T' || sessionScope.LOGIN_USER.role =='T1' }">
                                            <a class="btn btn-danger" href="dashboardteacher.jsp" role="button">Cancel</a>
                                        </c:if>
                                        <c:if test="${sessionScope.LOGIN_USER.role =='S' || sessionScope.LOGIN_USER.role =='S1' }">
                                            <a class="btn btn-danger" href="dashboardstudent.jsp" role="button">Cancel</a>
                                        </c:if>   
                                    </div>        
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="p-3 py-5">
                                <div class="d-flex justify-content-between align-items-center experience"><span>Account</span><span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;${sessionScope.LOGIN_USER.role}</span></div><br>
                                <div class="col-md-12"><label class="labels">Username</label><input type="text" class="form-control" placeholder="Enter Username" name="Username" value="${sessionScope.LOGIN_USER.userID}"></div> <br>
                                <div class="col-md-12"><label class="labels">Password</label><input type="password" class="form-control" value="hellu" readonly></div>
                                <a href="forgot-password.html">Reset password</a>
                            </div>
                        </div>
                        <div class="text-success text-center">
                            ${requestScope.SUCCESS}
                        </div>
                        <div class="text-danger text-center">
                            ${requestScope.ERROR.fullNameError}
                        </div>
                    </div>
                </form>
            </div>
        </c:if>



        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <!--
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        -->
    </body>
</html>

