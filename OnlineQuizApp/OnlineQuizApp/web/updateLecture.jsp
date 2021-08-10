<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.onlinequizapp.dtos.LectureDTO"%>
<%@page import="org.onlinequizapp.daos.ClassDAO"%>
<%@page import="java.util.List"%>
<%@page import="org.onlinequizapp.dtos.ClassDTO"%>
<%@page import="org.onlinequizapp.dtos.CourseDTO"%>
<%@page import="org.onlinequizapp.daos.CourseDAO"%>
<%@page import="org.onlinequizapp.daos.LectureDAO"%>
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
        <title>Update Lecture Information</title>
    </head>
    <body>
        <form action="LectureUpdateController" name="check" value="1" method="POST">
            <h1 align="center">UPDATE ${sessionScope.LIST_LECTURE.lectureName}</h1>
            <div class="main-agileinfo">
                <div class="agileits-top">
                    <h2 style="text-align: center">Lecture Edit page</h2>
                    Lecture ID <input type="text" name="lectureID" value="${param.lectureID}" id="lectureID" readonly=""/></br>
                    <label>
                        <span>Lecture Name</span></br>
                        <input type="text" name="lectureName" value="${param.lectureName}" id="name" required=""/></br>
                    </label>
                    <div>Course ID
                        <select name="courseID" class="form-select" aria-label="Default select example">
                            <option selected>${param.courseID}</option>
                            <c:if test="${requestScope.LIST_COURSE != null && not empty requestScope.LIST_COURSE}">
                                <c:forEach var="course" varStatus="counter" items="${requestScope.LIST_COURSE}">
                                    <c:if test="${course.status==1}">
                                        <option value="${course.courseID}">${counter.count} - ${course.courseName}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div><br>
                    <div>Class ID
                        <select name="classID" class="form-select" aria-label="Default select example">
                            <option selected>${param.classID}</option>
                            <c:if test="${requestScope.LIST_CLASS != null && not empty requestScope.LIST_CLASS}">
                                <c:forEach var="lop" varStatus="counter" items="${requestScope.LIST_CLASS}">
                                    <c:if test="${lop.status==1}">
                                        <option value="${lop.classID}">${counter.count} - ${lop.numberOfStudent}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div><br>
                    <label>
                        <span>Description</span></br>
                        <input type="text" name="description" value="${param.description}" id="description" required=""/></br>
                    </label>
                    <label>
                        <span>Status (1,0)</span></br>
                        <input type="text" name="status" value="${param.status}" id="status" required=""/></br>    
                        <input type="hidden" name="search" value="${param.search}" required=""/></br>
                        <input type="hidden" name="cate" value="${param.cate}"/>
                        <input type="hidden" name="check" value="${param.check}"/>
                        <input type="submit" name="action" value="Confirm Update Lecture"/>
                    </label>
                </div>
            </div>
        </form>
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