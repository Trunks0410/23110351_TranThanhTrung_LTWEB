<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
<title>Danh sách danh mục</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<h2>Danh sách danh mục</h2>

	<p>${message}</p>

	<form class="search-form" action="/admin/category/search" method="get">
		<input type="text" name="keyword" value="${keyword}"
			placeholder="Tìm kiếm theo tên..." />
		<button type="submit">Tìm kiếm</button>
	</form>

	<a href="/admin/category/form">Thêm mới</a>

	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên</th>
				<th>Mô tả</th>
				<th>Trạng thái</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>${category.id}</td>
					<td>${category.name}</td>
					<td>${category.description}</td>
					<td>${category.status}</td>
					<td><a href="/admin/category/edit/${category.id}">Sửa</a> | <a
						href="/admin/category/delete/${category.id}"
						onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination">
		<c:forEach begin="1" end="${totalPages}" var="i">
			<c:choose>
				<c:when test="${currentPage == i}">
					<a href="/admin/category/search?keyword=${keyword}&page=${i}"
						class="active">${i}</a>
				</c:when>
				<c:otherwise>
					<a href="/admin/category/search?keyword=${keyword}&page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>

</body>
</html>