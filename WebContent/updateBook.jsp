<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>修改图书信息</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/add.css">
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="js/validation.js"></script>
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
       						if(category.name == '${requestScope.book.category}') {
       							html = html + "<option value='" + category.name + "' selected>" + category.name + "</option>";
       						} else {
       							html = html + "<option value='" + category.name + "'>" + category.name + "</option>";
       						}
       					}
       					document.getElementById("categoryId").innerHTML = html;
       				}
       			}
       		}
        	
        	/* 检测输入项 */
        	function checkSubmit() {
        		r1 = checkEmpty('#bookName', '#errBookName')
        		r2 = checkInteger('#bookPrice', '#errBookPrice');
        		r3 = checkEmpty('#remarks', '#errNote');
        		return r1 && r2 && r3;
        	}
        	
        	/* 上传了新图片 */
        	function selectCover() {
				checkFile("#bookPic", "#errBookPic");
				$("#isCoverModified").val(1);
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
                <p>请小心的修改图书信息。。。</p>
            </div>
            <div class="page-header">
                <h3><small>修改</small></h3>
            </div>
            <form class="form-horizontal" action="/dept/update.do" method="post" onsubmit="return checkSubmit()" enctype="multipart/form-data">

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书编号 ：</label>
                    <div class="col-sm-8">
                        <input name="bookId" class="form-control" id="bookId" value="${requestScope.book.id}" readonly="readonly">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书名称 ：</label>
                    <div class="col-sm-8">
                    	<span id="errBookName" style="color:red"></span>
                        <input name="bookName" class="form-control" id="bookName" value="${requestScope.book.name}" onblur="checkEmpty('#bookName', '#errBookName')">
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
                    <select id="categoryId" name="category" class="col-sm-2 form-control" style="width: auto;margin-left: 15px">
                       <option value="ca0001" selected="">计算机</option>
                       <option value="ca0002">文学</option>
                       <option value="ca0003">历史</option>
                       <!-- 下拉列表的内容要从分类中进行读取，value值是分类id -->
                    </select>
                </div>

                 <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">价格 ：</label>
                    <div class="col-sm-8">
                    	<span id="errBookPrice" style="color:red"></span>
                        <input name="bookPrice" class="form-control" id="bookPrice" value="${requestScope.book.price}" onblur="checkInteger('#bookPrice', '#errBookPrice')">
                    </div>
                  </div>
                   
                  <div class="form-group" >
                    <label for="name" class="col-sm-2 control-label">图书封面 ：</label>
                    <input name="isCoverModified" value="0" type="hidden" id="isCoverModified">
                    <span id="errBookPic" style="color:red"></span>
                    <input type="file" id="bookPic" name="bookPic" style="padding-left: 15px" accept="image/*" onchange="selectCover()">
                  </div>

                  <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">备注 ：</label>
                    <div class="col-sm-8">
                    	<span id="errNote" style="color:red"></span>
                        <input name="note" class="form-control" id="remarks" value="${requestScope.book.note}" onblur="checkEmpty('#remarks', '#errNote')">
                    </div>
                  </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">修改</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
    </body>
</html>
