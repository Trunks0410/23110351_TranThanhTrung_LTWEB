<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
<title>User List</title>
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
	<h2>User List</h2>
	<p>${message}</p>
	<form action="/admin/user/list" method="get">
		<input type="text" name="keyword" value="${keyword}"
			placeholder="Search by username" />
		<button type="submit">Search</button>
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Email</th>
			<th>Role</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.role}</td>
				<td><a href="/admin/user/form?id=${user.id}">Edit</a> <a
					href="/admin/user/delete/${user.id}"
					onclick="return confirm('Are you sure?')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${totalPages > 1}">
		<c:forEach begin="0" end="${totalPages - 1}" var="i">
			<a href="/admin/user/list?page=${i}&keyword=${keyword}">${i + 1}</a>
		</c:forEach>
	</c:if>
	<br />
	<a href="/admin/user/form">Add New User</a>
</body>
</html>