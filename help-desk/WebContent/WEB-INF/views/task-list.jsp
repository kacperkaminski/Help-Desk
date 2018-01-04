<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List users</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<ul>
		<li><a href="http://localhost:8080/help-desk/user/list/">Users</a></li>
		<li><a href="http://localhost:8080/help-desk/group/list/">Groups</a></li>
		<li><a href="http://localhost:8080/help-desk/task/list/">Tasks</a></li>
		<li style="float: right"><a href="http://localhost:8080/help-desk/showLoginPage">Login</a></li>
	</ul>

	<div id="container">

		<div id="content">

			<input type="button" value="Add Task"
				onclick="window.location.href='/help-desk/group/showFormForAdd'; return false;"
				class="add-button" />

			<table>
				<tr>
					<th>ID</th>
					<th>Group name</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempUserGroup" items="${userGroup}">
					<c:url var="deleteLink" value="/group/delete">
						<c:param name="userGroupId" value="${tempUserGroup.id}" />
					</c:url>
					<tr>
						<td>${tempUserGroup.id}</td>
						<td>${tempUserGroup.title}</td>
						<td><a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this user group?'))) return false">|
								Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>