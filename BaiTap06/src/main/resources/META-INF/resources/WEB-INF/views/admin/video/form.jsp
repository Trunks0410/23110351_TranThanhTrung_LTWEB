<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${videoModel.edit ? 'Edit Video' : 'Add New Video'}</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h2>${videoModel.edit ? 'Edit Video' : 'Add New Video'}</h2>
	<form:form action="/admin/video/save" method="post"
		modelAttribute="videoModel">
		<form:hidden path="id" />
		<form:hidden path="edit" />
		<label>Title:</label>
		<form:input path="title" />
		<form:errors path="title" cssClass="error" />
		<br />
		<label>URL:</label>
		<form:input path="url" />
		<form:errors path="url" cssClass="error" />
		<br />
		<label>Category:</label>
		<form:select path="categoryId">
			<form:option value="">Select Category</form:option>
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</form:select>
		<br />
		<label>User:</label>
		<form:select path="userId">
			<form:option value="">Select User</form:option>
			<form:options items="${users}" itemValue="id" itemLabel="username" />
		</form:select>
		<br />
		<button type="submit">${videoModel.edit ? 'Update' : 'Insert'}</button>
	</form:form>
	<a href="/admin/video/list">Back to List</a>
</body>
</html>