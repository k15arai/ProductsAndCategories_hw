<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Category</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<a href="/products/new">Register a New Product</a>
		<div id="page_header__message">
			<c:out value="${message}"/>
		</div>
		<h1>New Category</h1>
		
		<form:form class="form" action="/categories/new" method="POST" modelAttribute="category">
			<div class="form-group row">
				<form:label path="name">Name:</form:label>
				<form:input type="text" path="name"></form:input>
				<form:errors path="name" class="errors"></form:errors>
			</div>
			<input type="submit" class="btn btn-primary m-2" value="Submit" />
		</form:form>
		
		<table class="table">
			<tr class="table-info">
				<th>Name</th>
				<th>Actions</th>
			</tr>
		<c:forEach var="category" items="${categories}">
			<tr>
				<td><c:out value="${category.name}" /></td>
				<td><a href="/categories/<c:out value="${category.id}" />">CATEGORY DETAILS</a> || <a href="/category/delete/<c:out value="${category.id}" />">DELETE</a></td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>
