<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${userModel.edit ? 'Sửa người dùng' : 'Thêm người dùng mới'}</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<h2>${userModel.edit ? 'Sửa người dùng' : 'Thêm người dùng mới'}</h2>
	<form:form action="/admin/user/save" method="post"
		modelAttribute="userModel">
		<form:hidden path="id" />
		<form:hidden path="edit" />
		<label>Tên người dùng:</label>
		<form:input path="username" />
		<form:errors path="username" cssClass="error" />
		<br />
		<label>Mật khẩu:</label>
		<form:password path="password" />
		<form:errors path="password" cssClass="error" />
		<c:if test="${userModel.edit}">
			<small>(Để trống nếu không muốn thay đổi mật khẩu)</small>
		</c:if>
		<br />
		<label>Email:</label>
		<form:input path="email" />
		<form:errors path="email" cssClass="error" />
		<br />
		<label>Vai trò:</label>
		<form:select path="role">
			<form:option value="ADMIN">ADMIN</form:option>
			<form:option value="USER">USER</form:option>
		</form:select>
		<br />
		<button type="submit">${userModel.edit ? 'Cập nhật' : 'Thêm'}</button>
	</form:form>
	<a href="/admin/user/list">Quay lại danh sách</a>
</body>
</html>