<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Invoice</title>
<link rel="stylesheet" type="text/css"
	href="/mvc/resources/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript" src="/mvc/resources/js/script.js"></script>
</head>
<body>


	<div class="billing_table">
		<div class="bill">
			<h1>Invoice</h1>
			<p><b>Bill No:</b> ${bill.billingid}</p>
			<p><b>Date:</b> ${bill.billingDate}</p>


			<table class="table table-striped table-light table-hover" id="bill">
				<thead>
					<tr>
						<th scope="col">Item</th>
						<th scope="col">Quantity</th>
						<th scope="col">Price</th>
						<th scope="col">Total Price</th>

					</tr>
				</thead>
				<c:forEach var="billItem" items="${bill.billTableItems}">
					
				<tr>
					<td>${billItem.productName}</td>
					<td>${billItem.quantity}</td>
					<td><span>&#8377;</span>${billItem.price}</td>
					<td><span>&#8377;</span>${billItem.total}</td>
				</tr>
				</c:forEach>
				<tfoot>
					<tr>
						<td colspan="3" class="total-label">Total</td>
						<td id="billTotal" colspan="3" class="total-amount"><span>&#8377;</span>${bill.totalBillAmount}</td>
					</tr>
				</tfoot>
			</table>


		</div>
	</div>
</body>
</html>
