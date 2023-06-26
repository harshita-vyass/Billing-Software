<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Inventory</title>
<link rel="stylesheet" type="text/css"
	href="/mvc/resources/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript" src="/mvc/resources/js/script.js"></script>
</head>
<body>
	<div class=heading>
		<ul>
			<li><a href="/mvc/">Home</a></li>
			<li><a href="/mvc/invoices">Invoices</a></li>
			<li><a href="/mvc/inventory">Inventory</a></li>
			<li><a href="/mvc/reports">Reports</a></li>
		</ul>

	</div>
	<div class="billing_table">
		<div class="inventory">
			<h1>Inventory</h1>
			<table
				class="table-inventory table table-striped table-hover table-light">
				<thead>
					<tr>
						<th scope="col">Product Id</th>
						<th scope="col">Item Name</th>
						<th scope="col">Price</th>
						<th scope="col">Stock</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${listProduct}">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td><span>&#8377;</span>${product.price}</td>
							<td>${product.quantity}</td>
							<td><input placeholder="1" type="number" min="1" max="50" step="1" />
							<button class="view-bill" onclick="addStock(this)">Add</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
