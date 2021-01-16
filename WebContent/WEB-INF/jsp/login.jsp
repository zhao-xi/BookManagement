<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css"> 
    </head>
    <body>
        <div class="login">
            <div class="header">
                <h1>
                    <a href="/login.do">登录</a>
                </h1>
                <button></button>
            </div>
            <form action="/login.do" method="post">
                <div class="name">
                    <input type="text" id="name" name="username" value="demo">
                    <p></p>
                </div>
                <div class="pwd">
                    <input type="password" id="pwd" name="password" value="demo">
                    <p></p>
                </div>
               
                <div class="btn-red">
                    <input type="submit" value="登录" id="login-btn">
                </div>
            </form>
        </div>
    </body>
</html>