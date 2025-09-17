<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
<title>Video List</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h2>Video List</h2>
	<p>${message}</p>
	<form action="/admin/video/list" method="get">
		<input type="text" name="keyword" value="${keyword}"
			placeholder="Search by title" />
		<button type="submit">Search</button>
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>URL</th>
			<th>Category</th>
			<th>User</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="video" items="${videos}">
			<tr>
				<td>${video.id}</td>
				<td>${video.title}</td>
				<td>${video.url}</td>
				<td>${video.category != null ? video.category.name : 'N/A'}</td>
				<td>${video.user != null ? video.user.username : 'N/A'}</td>
				<td><a href="/admin/video/form?id=${video.id}">Edit</a> <a
					href="/admin/video/delete/${video.id}"
					onclick="return confirm('Are you sure?')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${totalPages > 1}">
		<c:forEach begin="0" end="${totalPages - 1}" var="i">
			<a href="/admin/video/list?page=${i}&keyword=${keyword}">${i + 1}</a>
		</c:forEach>
	</c:if>
	<br />
	<a href="/admin/video/form">Add New Video</a>
</body>
</html>