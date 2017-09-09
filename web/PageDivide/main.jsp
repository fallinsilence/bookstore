<%--
  Created by IntelliJ IDEA.
  User: hugang
  Date: 17-9-1
  Time: 上午9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
    <frameset rows="15%,*">
        <frame src="top.jsp">
        <frameset cols="15%,*">
            <frame src="left.jsp">
            <frame src="right.jsp" name="right">
        </frameset>
    </frameset>
</html>
