<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${videoModel.edit ? 'Sửa Video' : 'Thêm Video Mới'}</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<h2>${videoModel.edit ? 'Sửa Video' : 'Thêm Video Mới'}</h2>
	<form:form action="/admin/video/save" method="post"
		modelAttribute="videoModel">
		<form:hidden path="id" />
		<form:hidden path="edit" />
		<label>Tiêu đề:</label>
		<form:input path="title" />
		<form:errors path="title" cssClass="error" />
		<br />
		<label>URL:</label>
		<form:input path="url" />
		<form:errors path="url" cssClass="error" />
		<br />
		<label>Danh mục:</label>
		<form:select path="categoryId">
			<form:option value="">Chọn danh mục</form:option>
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</form:select>
		<br />
		<label>Người dùng:</label>
		<form:select path="userId">
			<form:option value="">Chọn người dùng</form:option>
			<form:options items="${users}" itemValue="id" itemLabel="username" />
		</form:select>
		<br />
		<button type="submit">${videoModel.edit ? 'Cập nhật' : 'Thêm'}</button>
	</form:form>
	<a href="/admin/video/list">Quay lại danh sách</a>
</body>
</html>