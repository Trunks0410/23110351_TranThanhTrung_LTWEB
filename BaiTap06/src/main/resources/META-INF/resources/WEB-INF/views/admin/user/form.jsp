<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${userModel.edit ? 'Edit User' : 'Add New User'}</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h2>${userModel.edit ? 'Edit User' : 'Add New User'}</h2>
	<form:form action="/admin/user/save" method="post"
		modelAttribute="userModel">
		<form:hidden path="id" />
		<form:hidden path="edit" />
		<label>Username:</label>
		<form:input path="username" />
		<form:errors path="username" cssClass="error" />
		<br />
		<label>Password:</label>
		<form:password path="password" />
		<form:errors path="password" cssClass="error" />
		<c:if test="${userModel.edit}">
			<small>(Leave blank to keep current password)</small>
		</c:if>
		<br />
		<label>Email:</label>
		<form:input path="email" />
		<form:errors path="email" cssClass="error" />
		<br />
		<label>Role:</label>
		<form:select path="role">
			<form:option value="ADMIN">ADMIN</form:option>
			<form:option value="USER">USER</form:option>
		</form:select>
		<br />
		<button type="submit">${userModel.edit ? 'Update' : 'Insert'}</button>
	</form:form>
	<a href="/admin/user/list">Back to List</a>
</body>
</html>