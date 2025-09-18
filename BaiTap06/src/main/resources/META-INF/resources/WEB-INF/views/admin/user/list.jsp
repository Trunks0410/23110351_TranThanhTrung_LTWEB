<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
<title>Danh sách người dùng</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<h2>Danh sách người dùng</h2>
	<p>${message}</p>
	<form action="/admin/user/list" method="get">
		<input type="text" name="keyword" value="${keyword}"
			placeholder="Tìm kiếm theo tên người dùng" />
		<button type="submit">Tìm kiếm</button>
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Tên người dùng</th>
			<th>Email</th>
			<th>Vai trò</th>
			<th>Hành động</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.role}</td>
				<td><a href="/admin/user/form?id=${user.id}">Sửa</a> <a
					href="/admin/user/delete/${user.id}"
					onclick="return confirm('Bạn có chắc chắn muốn xóa không?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${totalPages > 1}">
		<c:forEach begin="0" end="${totalPages - 1}" var="i">
			<a href="/admin/user/list?page=${i}&keyword=${keyword}">${i + 1}</a>
		</c:forEach>
	</c:if>
	<br />
	<a href="/admin/user/form">Thêm người dùng mới</a>
</body>
</html>