
var billingList = [];

function isNaturalNumber(n) {
	n = n.toString();
	var n1 = Math.abs(n);
	var n2 = parseInt(n, 10);
	return !isNaN(n1) && n2 === n1 && n1.toString() === n && n2 != 0;
}

function fetchProductbyId() {
	let xhr = new XMLHttpRequest();
	var category = document.getElementById('category').value;
	var product = document.getElementById('product').value;
	var quantity = document.getElementById('productQuantity').value;
	if (category == "" || product == "" || quantity == "") {
		alert("All fields not filled!!");
		return;
	}
	if (!isNaturalNumber(quantity)) {
		alert("Quantity must be a valid!");
		return;
	}
	
	var url = 'http://localhost:8080/mvc/getProductById/' + parseInt(product);
	xhr.open("GET", url, true);

	// function execute after request is successful
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			const result = JSON.parse(this.responseText);
			populateBillingTable(result);
		}
	}
	// Sending our request
	xhr.send();
}


function checkAvailability(selectedProduct, quantity) {
	var isDuplicate = false;
	var isOutOfStock = false;
	billingList.forEach(product => {
		if (product.item.id == selectedProduct.id) {
			isDuplicate = true;
			if ((product.quantity + quantity) > selectedProduct.quantity) {
				isOutOfStock = true;
			} else {
				product.quantity += quantity;
			}
		} else {
			if (quantity > selectedProduct.quantity) {
				isOutOfStock = true;
			}
		}
	});
	if (quantity > selectedProduct.quantity) {
		isOutOfStock = true;
	}
	return { isDuplicate: isDuplicate, isOutOfStock: isOutOfStock };
}

// Function to populate the billing table
function populateBillingTable(selectedProduct) {
	var quantity = parseInt(document.getElementById('productQuantity').value);
	const flags = checkAvailability(selectedProduct, quantity);

	if (flags.isOutOfStock) {
		alert("Requested stock not available for the product " + selectedProduct.name + "!");
		return;
	}
	if (!flags.isDuplicate) {
		billingList.push({ item: selectedProduct, quantity: quantity });
	}
	// console.log(billingList);
	var tableBody = document.getElementById('billingTableBody');
	var totalBill = document.getElementById('billTotal');

	// Clear existing rows
	tableBody.innerHTML = '';
	var totalBillAmount = 0;

	// Iterate through the billing list and create table rows
	for (var i = 0; i < billingList.length; i++) {
		var row = document.createElement('tr');

		var itemCell = document.createElement('td');
		itemCell.textContent = billingList[i].item.name;
		row.appendChild(itemCell);

		var quantityCell = document.createElement('td');
		quantityCell.textContent = billingList[i].quantity;
		row.appendChild(quantityCell);

		var priceCell = document.createElement('td');
		priceCell.textContent = "₹" + billingList[i].item.price.toFixed(2);
		row.appendChild(priceCell);

		var totalCell = document.createElement('td');
		const cost = billingList[i].quantity * billingList[i].item.price.toFixed(2);
		totalCell.textContent = "₹" + cost;
		row.appendChild(totalCell);

		totalBillAmount += cost;
		tableBody.appendChild(row);
	}

	totalBill.innerHTML = "₹" + totalBillAmount.toFixed(2);
	document.getElementById('productQuantity').value = 1;
}

function generateBill() {
	if (billingList.length == 0) {
		alert("Please add items in cart to proceed.");
		return;
	}
	let xhr = new XMLHttpRequest();
	// Making our connection 
	var url = 'http://localhost:8080/mvc/generateBill';
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8');

	// function execute after request is successful
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			const result = this.responseText;
			// console.log(result);
			alert("Bill no. " + result + " has been generated!");
			location.reload(true);
		} else if (this.readyState == 4 && (this.status == 404 || this.status == 500)) {
			console.error("Error Encountered!!");
		}
	}
	// Sending our request
	xhr.send(JSON.stringify(billingList));
}

function viewBill(button) {
	var row = button.parentNode.parentNode; // Get the parent row of the button
	var data = row.cells[0].innerText; // Get data from first cell
	// console.log(data);
	window.open('http://localhost:8080/mvc/invoices/' + data, '_blank');
}

function addStock(button) {
	var td = button.parentNode;
	var row = td.parentNode;
	if (td.firstElementChild.value == '') {
		alert("Please add stock value!!");
		return;
	}
	if (!isNaturalNumber(td.firstElementChild.value)) {
		alert("Stock value must be valid!");
		return;
	}
	const stock = parseInt(row.cells[3].innerText) + parseInt(td.firstElementChild.value);
	let xhr = new XMLHttpRequest();
	// Making our connection 
	var url = 'http://localhost:8080/mvc/inventory/update?product_id= ' + row.cells[0].innerText + '&quantity=' + stock;
	xhr.open("PUT", url, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8');

	// function execute after request is successful
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			row.cells[3].innerText = stock;
			td.firstElementChild.value = '';
		} else if (this.readyState == 4 && (this.status == 404 || this.status == 500)) {
			console.error("Error Encountered!!");
		}
	}
	// Sending our request
	xhr.send();

}

