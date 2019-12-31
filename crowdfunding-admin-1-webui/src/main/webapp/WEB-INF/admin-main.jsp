<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<title>❤汇聚点滴的力量，成就非凡的伟业❤</title>
</head>
<body>
	<h1>后台主页面</h1>
	${sessionScope['LOGIN-ADMIN'] }
	<br/>
	<a href="admin/logout.html">退出</a>
</body>
</html>