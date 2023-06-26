<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="/mvc/resources/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script type="text/javascript" src="/mvc/resources/js/script.js"></script>
<script type="text/javascript" src="/mvc/resources/js/Bill.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#category')
								.change(
										function() {
											var selectedValue = $(this).val();
											$
													.ajax({
														type : 'GET',
														url : '${pageContext.request.contextPath}/loadProductsByCategory/'
																+ selectedValue,
														success : function(
																response) {
															var result = JSON
																	.parse(response);
															var s = '';
															for (var i = 0; i < result.length; i++) {
																s += '<option value=' + result[i].id + '">'
																		+ result[i].name
																		+ '</option>';
															}
															$('#product').html(
																	s);
															$(
																	'#productQuantity')
																	.val(1);
														},
														error : function(xhr,
																status, error) {
															console.log(error);
														}
													});
										});
					});
</script>
</head>
<body>
	<div class="heading">
		<ul>
			<li><a href="/mvc/">Home</a></li>
			<li><a href="/mvc/invoices">Invoices</a></li>
			<li><a href="/mvc/inventory">Inventory</a></li>
			<li><a href="/mvc/reports">Reports</a></li>
		</ul>

	</div>
	<div class="billing_table">
		<div class="bill">
			<h1>Invoice</h1>

			<form:form method="POST" class="bill-form">
				<div class="form">
					<div class="form-group">
						<label for="categorys">Category:</label> <select class="form-select"
							name="category" id="category" required>
							<option selected="selected" disabled="disabled" value="">--Select--</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="product">Product:</label> <select name="product"
							id="product">
							<option selected="selected" disabled="disabled" value="">--Select--</option>
						</select>
					</div>
					<div class="form-group">
						<label>Quantity: </label> <input class="form-input quantity"
							type="number" min="1" max="50" step="1" placeholder="1"
							id="productQuantity">
					</div>

					<button class="form-btn" onclick="fetchProductbyId()">Add</button>

				</div>


				<table class="table table-striped table-light table-hover" id="bill">
					<thead>
						<tr>
							<th scope="col">Item</th>
							<th scope="col">Quantity</th>
							<th scope="col">Price</th>
							<th scope="col">Total Price</th>

						</tr>
					</thead>
					<tbody id="billingTableBody"></tbody>
					<tfoot>
						<tr>
							<td colspan="3" class="total-label">Total</td>
							<td id="billTotal" colspan="3" class="total-amount"><span>&#8377;</span>0</td>
						</tr>
					</tfoot>
				</table>

				<button type="submit" class="generate btn btn-dark"
					onclick="generateBill()" id="generate">Generate Bill</button>

			</form:form>
		</div>
	</div>
</body>
</html>
