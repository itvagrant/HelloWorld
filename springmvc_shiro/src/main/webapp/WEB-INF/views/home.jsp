<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>
	Hello world!  
</h1>
	<c:if test="${not empty requestScope.user}">
	<p><b>id：</b>${requestScope.user.id}</p>
	<p><b>姓名：</b>${requestScope.user.name}</p>
	<p><b>年龄：</b>${requestScope.user.age}</p>
	<p><b>性别：</b>${requestScope.user.sex}</p>
	</c:if>
	<c:if test="${empty requestScope.user}">
		<h3>未查询到相关用户信息！</h3>
	</c:if>
	<hr/>
	<p><b>sessionScope-name:</b>${sessionScope.name}</p>
	<p><b>sessionScope-age:</b>${sessionScope.age}</p>
	<p><b>requestScope-name:</b>${requestScope.name}</p>
	<p><b>requestScope-age:</b>${requestScope.age}</p>
</body>
</html>
