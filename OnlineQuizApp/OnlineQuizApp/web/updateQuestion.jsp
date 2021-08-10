<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.onlinequizapp.dtos.QuestionDTO"%>
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
        <title>Update Question Information</title>
    </head>
    <body>
        <form action="QuestionUpdateController" name="check" value="1" method="POST">
            <h1 align="center">UPDATE ${sessionScope.LIST_QUESTION.name}</h1>
            <div class="main-agileinfo">
                <div class="agileits-top">
                    <h2 style="text-align: center">Question Search and Edit page</h2>
                    Question ID <input type="text" name="questionID" value="${param.questionID}" id="categoryID" readonly=""/></br>
                    <label>
                        <span>Name</span></br>
                        <input type="text" name="name" value="${param.name}" id="name" required=""/></br>
                    </label>
                    <label>
                        <span>1st Answer</span></br>
                        <input type="text" name="answer1" value="${param.answer1}" id="answer1" required=""/></br>
                    </label>
                    <label>
                        <span>2nd Answer</span></br>
                        <input type="text" name="answer2" value="${param.answer2}" id="answer2" required=""/></br>
                    </label>
                    <label>
                        <span>3rd Answer</span></br>
                        <input type="text" name="answer3" value="${param.answer3}" id="answer3" required=""/></br>
                    </label>
                    <label>
                        <span>4th Answer</span></br>
                        <input type="text" name="answer4" value="${param.answer4}" id="answer4" required=""/></br>
                    </label>
                    <label>
                        <span>Description</span></br>
                        <input type="text" name="description" value="${param.description}" id="description" required=""/></br>
                    </label>
                    <label>
                        <span>Correct Answer</span></br>
                        <input type="text" name="answer" value="${param.answer}" id="answer" required=""/></br>
                    </label>
                    <label>
                        <span>Author ID</span></br>
                        <input type="text" name="authorID" value="${param.authorID}" id="authorID" required=""/></br>
                    </label>
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
                    <label>
                        <span>Status (1,0)</span></br>
                        <input type="text" name="status" value="${param.status}" id="status" required=""/></br>    
                        <input type="hidden" name="search" value="${param.search}" required=""/></br>
                        <input type="hidden" name="cate" value="${param.cate}"/>
                        <input type="hidden" name="check" value="${param.check}"/>
                        <input type="submit" name="action" value="Confirm Update Question"/>
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