<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>图书后台管理</title>
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>

    <body>
       <header>
            <div class="container">
                    <nav>
                            <a href="/management?target=book" >图书信息管理</a>
                    </nav>
                    <nav>
                            <a href="/management?target=category" >分类管理</a>
                    </nav>
                   
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>图书管理系统</h1>
                    <p>图书信息管理</p>
                </div>
            </div>
        </section>
        <section class="main">

            <div class="container">
                <div class="form-group"  style="float: right;">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-primary" id="searchButton">查询</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div class="form-group" style="float: right;width: 300px;">
                    <div class="col-sm-8">
                        <input name="searchContent" class="form-control" id="searchContent"
                        placeholder="输入要查询的分类" style="width: 250px">
                    </div>
                </div>
            </div>
            <div class="container">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>图书编号</th>
                        <th>图书名称</th>
                        <th>分类</th>
                        <th>价格</th>
                        <th>图书封面</th>
                        <th>操作</th>

                    </tr>
                    </thead>
                    <tbody id="booktable">
                        <c:forEach items="${bookList}" var="book" varStatus="status">
                            <tr id="tr1">
                                <td>${status.index + 1}</td>
                                <td>${book.id}</td>
                                <td>${book.name}</td>
                                <td>${book.category}</td>
                                <td>￥${book.price}</td>
                                <td><img src="${book.cover}" style="height:100px;"></td>
                                <td>
                                <a href="/updateBook?bookId=${book.id}">修改</a>
                                <a href="/deleteBook?bookId=${book.id}">删除</a>

                                </td>
                                <!--在循环显示数据时，此处的book0001可以用EL表达式进行替换-->

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <div id="fatie">
                    <a href="addBook.jsp"><button>新建</button></a>
                </div>
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
    
    <script type="text/javascript">
      		// 利用ajax通过分类查询图书
      		document.getElementById("searchButton").onclick = function() {
      			var searchContent = document.getElementById("searchContent").value;
      			// 1. 创建XmlHttpRequest对象
      			var xmlhttp;
      			if(window.XMLHttpRequest) {
      				xmlhttp = new XMLHttpRequest();
      			} else {
      				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
      			}
      			// 2. 发送ajax请求
      			xmlhttp.open("GET", "/searchBook?searchContent=" + searchContent, true);
      			xmlhttp.send();
      			// 3. 处理服务求响应
      			xmlhttp.onreadystatechange = function() {
      				if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      					var text = xmlhttp.responseText;
      					var json = JSON.parse(text);
      					html = "";
      					for(var i = 0; i < json.length; i++) {
      						var book = json[i];
      						html = html + "<tr id='tr1'>";
      						html = html + "<td>" + (i + 1) + "</td>";
      						html = html + "<td>" + book.id + "</td>";
      						html = html + "<td>" + book.name + "</td>";
      						html = html + "<td>" + book.category + "</td>";
      						html = html + "<td>" + book.price + "</td>";
      						html = html + "<td><img src='" + book.cover + "' style='height:100px'></td>";
      						html = html + "<td><a href='/updateBook?bookId=${"+book.id+"}'>修改</a><a href='/deleteBook?bookId=${"+book.id+"}'>删除</a></td>";
      						html = html + "</tr>"
      					}
      					document.getElementById("booktable").innerHTML = html;
      				}
      			}
      		}
      	</script>
</html>