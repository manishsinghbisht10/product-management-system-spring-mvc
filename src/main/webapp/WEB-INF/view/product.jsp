<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products list</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/* body {
	background-color: #F0F8FF;
} */
.table {
	margin: 25px auto;
	width: 80%;
	border: 1px solid black;
}

h1 {
	text-align: center;
	margin: 25px auto;
}

.pagination {
	display: flex;
	flex-direction: row;
	gap: 10px;
	justify-content: center;
}

.add {
	margin: 10px 0;
}

.fas:hover {
	cursor: pointer;
}

.delete {
	margin: 0;
}
</style>
</head>
<body>
	<button type="button" class="add btn btn-light">
		<a style="text-decoration: none" href="add"><strong>New
				Product</strong></a>
	</button>
	<button type="button" class="add btn btn-light">
		<a style="text-decoration: none" href="home"><strong>Refresh</strong></a>
	</button>
	<h1>Product List</h1>

	<div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Product Name <i id="downArrow1"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Product Code <i id="downArrow2"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Product Description <i id="downArrow3"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Category Name<i id="downArrow4"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Price <i id="downArrow5"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Currency <i id="downArrow6"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Stock/Inventory <i id="downArrow7"
						class="fas fa-chevron-down"></i></th>
					<th scope="col">Location <i id="downArrow8"
						class="fas fa-chevron-down"></i></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productList}" var="product">
					<tr>
						<td>${product.productName }</td>
						<td>${product.productCode }</td>
						<td>${product.productDescription }</td>
						<td>${product.categoryName }</td>
						<td>${product.price }</td>
						<td>${product.currency}</td>
						<td>${product.inventory }</td>
						<td>${product.location }</td>
						<td><button type="button" class="delete btn btn-light">
								<a style="text-decoration: none"
									href="/product/delete?productId=${product.productId}"><strong>Delete</strong>
								</a>
							</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class=pagination>
		<button type="button" class=" previous btn btn-light previous">
			<strong>Previous</strong>
		</button>
		<button type="button" class=" next btn btn-light next">
			<strong>Next</strong>
		</button>
	</div>

	<script>
		let offset = ${	offset};
		let limit = ${limit};
		let count = ${count};
		let sortBy = $
		{
			'sortBy'
		};
		console.log('Offset:', offset);
		console.log('Limit:', limit);
		console.log('Count:', count);
		document
		.getElementById('downArrow1')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=product_name&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow2')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=product_code&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow3')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=product_description&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow4')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=category_name&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow5')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=product_price&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow6')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=currency&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow7')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=inventory_available&limit=${limit}&offset=${offset}";
				});

		document
		.getElementById('downArrow8')
		.addEventListener(
				'click',
				function() {
					window.location.href = "/product/productSort?sortBy=location&limit=${limit}&offset=${offset}";
				});

		document
		.querySelector('.previous')
		.addEventListener(
				'click',
				function() {
					if ( ${offset + 4} > 4) {
						window.location.href = "/product/productSort?sortBy=${sortBy}&limit=${limit}&offset=${offset-4}";
					}
				});

		document
		.querySelector('.next')
		.addEventListener(
				'click',
				function() {
					if (${offset + 4} < ${count}) {
						console.log('hello');
						console.log(count);
						window.location.href = "/product/productSort?sortBy=${sortBy}&limit=${limit}&offset=${offset+4}";
					}
				});
	</script>
</body>
</html>