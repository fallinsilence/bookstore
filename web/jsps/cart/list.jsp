<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物车列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
  <c:if test="${empty existUser}" var="flag">
	  亲，你还没登录，请去<a href="/jsps/user/login.jsp">登录</a>
  </c:if>

  <c:if test="${!flag}">
	  <h1>购物车</h1>

	  <table border="1" width="100%" cellspacing="0" background="black">
		  <tr>
			  <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				  <a href="/cart?method=clearCart">清空购物车</a>
			  </td>
		  </tr>
		  <tr>
			  <th>图片</th>
			  <th>书名</th>
			  <th>作者</th>
			  <th>单价</th>
			  <th>数量</th>
			  <th>小计</th>
			  <th>操作</th>
		  </tr>
		  <c:forEach var="item" items="${cart.cartItems}">
			  <tr>
				  <td><div><img src="${pageContext.request.contextPath}/${item.book.image}" width="120" height="150"></div></td>
				  <td>${item.book.bname}</td>
				  <td>${item.book.author}</td>
				  <td>${item.book.price}</td>
				  <td>${item.count}</td>
				  <td>${item.subTotal}</td>
				  <td><a href="/cart?method=removeCart&bid=${item.book.bid}">删除</a></td>
			  </tr>
		  </c:forEach>

		  <tr>
			  <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				  合计：${ cart.total }元
			  </td>
		  </tr>
		  <tr>
			  <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				  <a id="buy" href="/order?method=createOrder"></a>
			  </td>
		  </tr>

	  </table>
  </c:if>

  </body>
</html>
