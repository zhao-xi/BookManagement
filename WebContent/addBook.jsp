<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>新建图书信息</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/add.css">
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <script>
        	/* 通过ajax获取category数据，然后填到下拉表单里 */
       		function readCategoryData() {
       			var xmlhttp;
       			if(window.XMLHttpRequest) {
       				xmlhttp = new XMLHttpRequest();
       			} else {
       				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
       			}
       			xmlhttp.open("GET", "/management?target=category_json", true);
       			xmlhttp.send();
       			xmlhttp.onreadystatechange = function() {
       				if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
       					var text = xmlhttp.responseText;
       					var json = JSON.parse(text);
       					var html = "";
       					for(var i = 0; i < json.length; i++) {
       						var category = json[i];
       						html = html + "<option value='" + category.name + "'>" + category.name + "</option>";
       					}
       					document.getElementById("categoryId").innerHTML = html;
       				}
       			}
       		}
        </script>
    </head>
    <body onload="readCategoryData()">
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/management?target=book">
                        图书信息管理
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${sessionScope.username}!</h1>
                <p>请小心地新增图书信息，要是建了一个错误的就不好了。。。</p>
            </div>
            <div class="page-header">
                <h3><small>新建</small></h3>
            </div>
            <form class="form-horizontal" action="/dept/add.do" method="post">

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书编号 ：</label>
                    <div class="col-sm-8">
                        <input name="bookId" class="form-control" id="bookId">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书名称 ：</label>
                    <div class="col-sm-8">
                        <input name="bookName" class="form-control" id="bookName">
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
                    <select id="categoryId" name="categoryId" class="col-sm-2 form-control" style="width: auto;margin-left: 15px">
                       <option value="ca0001" selected="">计算机</option>
                       <option value="ca0002">文学</option>
                       <option value="ca0003">历史</option>
                       <!-- 下拉列表的内容要从分类中进行读取，value值是分类id -->
                    </select>
                </div>

                 <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">价格 ：</label>
                    <div class="col-sm-8">
                        <input name="bookPrice" class="form-control" id="bookPrice">
                    </div>
                  </div>
                   
                  <div class="form-group" >
                    <label for="name" class="col-sm-2 control-label">图书封面 ：</label>
                    <input type="file" id="bookPic" name="bookPic" style="padding-left: 15px">
                  </div>

                  <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">备注 ：</label>
                    <div class="col-sm-8">
                        <input name="remarks" class="form-control" id="remarks">
                    </div>
                  </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
    </body>
</html>
