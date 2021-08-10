<%-- 
    Document   : updateCategory
    Created on : Jul 21, 2021, 12:19:31 PM
    Author     : ASUS
--%>

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
        <title>Update Category Information</title>
    </head>
    <body>
        <form action="CategoryUpdateController" name="check" value="1" method="POST">
            <h1 align="center">UPDATE ${sessionScope.LIST_QUIZ_CATEGORY.categoryName}</h1>
            <div class="main-agileinfo">
                <div class="agileits-top">
                    <h2 style="text-align: center">Category Search and Edit page</h2>
                    Category ID<input type="text" name="categoryID" value="${param.categoryID}" id="categoryID" readonly=""/></br>
                    <label>
                        <span>Category Name</span></br><input type="text" name="categoryName" value="${param.categoryName}" id="categoryName" required=""/></br>
                    </label>
                    <label>
                        <span>Level (Hard, Medium, Easy)</span></br><input type="text" name="level"  value="${param.level}" required=""/></br>
                    </label>
                    <label>
                        <span>Description</span></br><input type="text" name="description" id="description" value="${param.description}" required=""/></br>
                    </label>
                    <label><span>Status (1,0)</span></br>
                        <input type="text" name="status" value="${param.status}" id="status" required=""/></br>    
                        <input type="hidden" name="search" value="${param.search}" required=""/></br>
                        <input type="hidden" name="cate" value="${param.cate}"/>
                        <input type="hidden" name="check" value="${param.check}"/>
                        <input type="submit" name="action" value="Confirm Update Quiz"/>
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