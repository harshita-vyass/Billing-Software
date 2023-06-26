<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Invoices</title>
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
			<h1>Invoices</h1>
			<table
				class="table-inventory table table-striped table-hover table-light">
				<thead>
					<tr>
						<th scope="col">Bill Id</th>
						<th scope="col">Created Date</th>
						<th scope="col">Amount</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="invoice" items="${invoiceList}">
						<tr>
							<td>${invoice.billingid}</td>
							<td>${invoice.billingDate}</td>
							<td><span>&#8377;</span>${invoice.totalAmount}</td>
							<td><button class="view-bill" onclick="viewBill(this)"
									id="viewBill">View</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
