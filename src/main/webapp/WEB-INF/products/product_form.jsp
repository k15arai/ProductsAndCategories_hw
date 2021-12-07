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
    <title>New Product</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<a href="/categories/new">Register a New Category</a>
		<div id="page_header__message">
			<c:out value="${message}"/>
		</div>
		<h1>New Product</h1>
		
		<form:form class="form" action="/products/new" method="POST" modelAttribute="product">
			<div class="form-group row">
				<form:label path="name">Name:</form:label>
				<form:input type="text" path="name"></form:input>
				<form:errors path="name" class="errors"></form:errors>
			</div>
			<div class="form-group row">
				<form:label path="description">Description:</form:label>
				<form:textarea path="description" rows="4"></form:textarea>
				<form:errors path="description" class="errors"></form:errors>
			</div>
			<div class="form-group row">
				<form:label path="price">Price:</form:label>
				<form:input type="number" step="0.01" path="price"></form:input>
				<form:errors path="price" class="errors"></form:errors>
			</div>
			<input type="submit" class="btn btn-primary m-2" value="Submit" />
		</form:form>
		
		<table class="table">
			<tr class="table-info">
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
		<c:forEach var="product" items="${products}">
			<tr>
				<td><c:out value="${product.name}" /></td>
				<td><c:out value="${product.description}" /></td>
				<td>$<c:out value="${product.price}" /></td>
				<td><a href="/products/<c:out value="${product.id}" />">PRODUCT DETAILS</a> || <a href="/product/delete/<c:out value="${product.id}" />">DELETE</a></td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>
