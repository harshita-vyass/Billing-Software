<!DOCTYPE html>
<html>
<head>
<title>Page Not Found</title>
<link rel="stylesheet" type="text/css"
	href="/mvc/resources/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
}

h1 {
	font-size: 24px;
	margin-top: 40px;
}

p {
	font-size: 18px;
	margin-top: 20px;
}
</style>
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
	<div class="error-page">
		<h1>Sorry for the inconvenience!</h1>
		<p>The requested page could not be displayed at the moment</p>
		<a href="/mvc/">
			<button class="view-bill">Return to Home</button>
		</a>
	</div>
</body>
</html>
