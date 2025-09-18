<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng nhập</title>
<link rel="stylesheet" href="/css/login.css">
</head>
<body>
	<h2>Đăng nhập</h2>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<div>
			<label>Tên người dùng: <input type="text" name="username" /></label>
		</div>
		<div>
			<label>Mật khẩu: <input type="password" name="password" /></label>
		</div>
		<div>
			<input type="submit" value="Đăng nhập" />
		</div>
	</form>
	<c:if test="${param.error != null}">
		<p style="color: red">Tên người dùng hoặc mật khẩu không hợp lệ.</p>
	</c:if>
</body>
</html>