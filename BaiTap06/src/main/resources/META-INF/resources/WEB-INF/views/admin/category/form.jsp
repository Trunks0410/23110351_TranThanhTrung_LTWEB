<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${categoryModel.edit ? 'Edit Category' : 'Add New Category'}</title>
</head>
<body>
	<h2>${categoryModel.edit ? 'Chỉnh sửa danh mục' : 'Thêm danh mục mới'}</h2>

	<form:form action="/category/save" method="post"
		modelAttribute="categoryModel">
		<form:hidden path="id" />
		<form:hidden path="edit" />

		<div>
			<label for="name">Tên:</label>
			<form:input path="name" id="name" />
			<form:errors path="name" cssClass="error" />
		</div>
		<br />

		<div>
			<label for="description">Mô tả:</label>
			<form:input path="description" id="description" />
		</div>
		<br />

		<div>
			<label for="status">Trạng thái:</label>
			<form:input path="status" id="status" />
		</div>
		<br />

		<button type="submit">${categoryModel.edit ? 'Cập nhật' : 'Thêm mới'}</button>
	</form:form>
</body>
</html>