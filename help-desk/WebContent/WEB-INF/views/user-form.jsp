<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-user-style.css" />

<title>User Form</title>

</head>

<body>
	<ul>
		<li><a href="http://localhost:8080/help-desk/user/list/">Users</a></li>
		<li><a href="http://localhost:8080/help-desk/group/list/">Groups</a></li>
		<li><a href="http://localhost:8080/help-desk/task/list/">Tasks</a></li>
		<li style="float: right"><a href="http://localhost:8080/help-desk/showLoginPage">Login</a></li>
	</ul>

	<div id="container">

		<form:form action="saveUser" modelAttribute="user" method="POST">
			<form:hidden path="id"></form:hidden>
			<table>
				<tbody>
					<tr>
						<td><label>First Name: </label></td>
						<td><form:input path="firstName" id="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last Name: </label></td>
						<td><form:input path="lastName" /></td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td><label>Group id: </label></td>
						<td><form:input path="userGroup" id="userGroup"/></td>
					</tr> 
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/user/list"> Back to
				List</a>
		</p>
	</div>
</body>
</html>