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
</style>
</head>
<body>
	<button type="button" class="add btn btn-light">
		<a style="text-decoration: none" href="add">New Product</a>
	</button>
	<h1>Product List</h1>

	<div>
		<table class="table table-striped"">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Code</th>
					<th scope="col">Description</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Currency</th>
					<th scope="col">Stock/Inventory</th>
					<th scope="col">Location</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productList}" var="product">
					<tr>
						<th scope="row">${product.productName }</th>
						<td>${product.productCode }</td>
						<td>${product.productDescription }</td>
						<td>${product.categoryName }</td>
						<td>${product.price }</td>
						<td>${product.currency}</td>
						<td>${product.inventory }</td>
						<td>${product.location }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class=pagination>
		<button type="button" class=" previous btn btn-light">
			<a style="text-decoration: none" href="https://www.w3schools.com">Previous</a>
		</button>
		<button type="button" class=" next btn btn-light">
			<a style="text-decoration: none" href="https://www.w3schools.com">Next</a>
		</button>
	</div>


</body>
</html>