<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
<title>Danh sách Video</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<h2>Danh sách Video</h2>
	<p>${message}</p>
	<form action="/admin/video/list" method="get">
		<input type="text" name="keyword" value="${keyword}"
			placeholder="Tìm kiếm theo tiêu đề" />
		<button type="submit">Tìm kiếm</button>
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Tiêu đề</th>
			<th>URL</th>
			<th>Danh mục</th>
			<th>Người dùng</th>
			<th>Hành động</th>
		</tr>
		<c:forEach var="video" items="${videos}">
			<tr>
				<td>${video.id}</td>
				<td>${video.title}</td>
				<td>${video.url}</td>
				<td>${video.category != null ? video.category.name : 'N/A'}</td>
				<td>${video.user != null ? video.user.username : 'N/A'}</td>
				<td><a href="/admin/video/form?id=${video.id}">Sửa</a> <a
					href="/admin/video/delete/${video.id}"
					onclick="return confirm('Bạn có chắc chắn muốn xóa không?')">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${totalPages > 1}">
		<c:forEach begin="0" end="${totalPages - 1}" var="i">
			<a href="/admin/video/list?page=${i}&keyword=${keyword}">${i + 1}</a>
		</c:forEach>
	</c:if>
	<br />
	<a href="/admin/video/form">Thêm Video mới</a>
</body>
</html>