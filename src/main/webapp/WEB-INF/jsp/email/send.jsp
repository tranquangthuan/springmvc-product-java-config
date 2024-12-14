<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Send Email Form</title>
</head>
<body>
	<div align="center">
		<a href="${pageContext.request.contextPath}">Home</a>
		<form:form action="${pageContext.request.contextPath}/email/send"
			method="post" modelAttribute="emailForm">
			<table>
				<tr>
					<td><form:label path="sendToEmail">Email Người Nhânj</form:label></td>
					<td><form:input path="sendToEmail" /></td>
					<td><form:errors path="sendToEmail" /></td>
				</tr>
				<tr>
					<td><form:label path="subject">Subject</form:label></td>
					<td><form:input path="subject" /></td>
					<td><form:errors path="subject" /></td>
				</tr>
				<tr>
					<td><form:label path="content">content</form:label></td>
					<td><form:textarea path="content" /></td>
					<td><form:errors path="content" /></td>
				</tr>

				<tr>
					<td></td>
					<td><form:button value="Send Email">Send Email</form:button></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>