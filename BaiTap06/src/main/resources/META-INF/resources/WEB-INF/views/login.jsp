<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h2>Login</h2>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<div>
			<label>Username: <input type="text" name="username" /></label>
		</div>
		<div>
			<label>Password: <input type="password" name="password" /></label>
		</div>
		<div>
			<input type="submit" value="Login" />
		</div>
	</form>
	<c:if test="${param.error != null}">
		<p style="color: red">Invalid username or password.</p>
	</c:if>
</body>
</html>