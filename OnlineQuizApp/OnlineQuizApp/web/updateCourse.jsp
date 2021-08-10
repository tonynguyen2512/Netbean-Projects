<%--
    Document   : updateCourse
    Created on : Jul 25, 2021, 9:00:48 PM
    Author     : User-PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.onlinequizapp.daos.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="org.onlinequizapp.dtos.CategoryDTO"%>
<%@page import="org.onlinequizapp.dtos.CategoryBlogDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link href="CSS/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
        <!--<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'><link rel="stylesheet" href="./login.css">
        --><link href="CSS/nicepage.css" media="screen" rel="stylesheet" />
        <link href="CSS/Home.css" media="screen" rel="stylesheet" /><script class="u-script" type="text/javascript" src="JS/jquery-1.9.1.min.js" defer=""></script><script class="u-script" type="text/javascript" src="JS/nicepage.js" defer=""></script><meta name="generator" content="Nicepage 3.3.5, nicepage.com">
        <link href="images/MongSinhShop.png" rel="icon" />
        <title>Update Course Information</title>
    </head>
    <body
        <h3 align="center">Welcome: ${sessionScope.LOGIN_USER.fullname}</h3>
        <div class="main-w3layouts wrapper">
            <h1>Course Edit Page</h1>
            <div class="main-agileinfo">
                <div class="agileits-top">
                    <form action="CourseUpdateController">
                        Course ID<input type="text" name="courseID" value="${param.courseID}" id="categoryID" readonly=""/><br>
                        Status<input type="text" name="status" value="${param.status}" id="status" required=""/></br>
                        Course Name<input type="text" name="courseName" value="${param.courseName}"/><br>
                        Duration<input type="text" name="duration" value="${param.duration}"/><br>
                        <div>Category ID
                            <select name="categoryID" class="form-control" aria-label="Default select example">
                                <option selected>${param.categoryID}</option>
                                <c:if test="${requestScope.LIST_QUIZ_CATEGORY != null && not empty requestScope.LIST_QUIZ_CATEGORY}">
                                    <c:forEach var="category" varStatus="counter" items="${requestScope.LIST_QUIZ_CATEGORY}">
                                        <c:if test="${category.status==1}">
                                            <option value="${category.categoryID}">${counter.count} - ${category.categoryName}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div><br>
                        Description<input type="text" name="description" value="${param.description}"/>
                        <input type="hidden" name="search" value="${param.search}" required=""/></br>
                        <input type="hidden" name="check" value="Search" required=""/></br>
                        <input type="hidden" name="authorID" value="${param.authorID}" required=""/></br>
                        <input type="submit" name="action" value="Confirm"/>
                    </form>
                </div>
            </div>
        </div>
        <ul class="colorlib-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </body>
</html>

