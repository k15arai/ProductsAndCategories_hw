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
    <title>Category Page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container pt-2">
	<a href="/products/new">Register A New Product</a> || 
	<a href="/categories/new">Register A New Category</a>
	<div id="page_header__message">
		<c:out value="${message}"/>
	</div>
	<h1>${category.name}</h1>
	<div class="row">
		<div class="col-sm">
			<h2>Products</h2>
			<ul>
			  	<c:forEach var="includedProduct" items="${includedProducts}">
			  		<li>${includedProduct.name}</li>	
			  	</c:forEach>
	  		</ul>
		</div>
		<div class="col-sm">
			<form:form method="POST" action="/product/add/${category.id}" modelAttribute="product">
				<label for="product_id">Add Product:</label>
				<select name="product_id" id="product_id">
					<c:forEach var="excludedProduct" items="${excludedProducts}">
					<option value="${excludedProduct.id}">${excludedProduct.name}</option>
					</c:forEach>
				</select>
				<button>Add</button>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>
