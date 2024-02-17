<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add product Here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

<style>
.card-div {
	text-align: center;
}

.card {
	margin: 25px auto;
}

h1 {
	text-align: center
}

.mb-3 {
	margin-bottom: 10px;
}
</style>

</head>
<body>
	<h1>Add Product</h1>
	<div class="card-div">
		<div class="card" style="width: 25rem;">
			<div class="card-body">
				<form action="save" method="post">
					<div class="mb-3">
						<label>Enter Product Code</label> <input type="text"
							name="productCode" class="form-control">
					</div>
					<div class="mb-3">
						<label>Enter Product Description</label> <input type="text"
							name="productDescription" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Product Name</label> <input type="text"
							name="productName" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Category Name</label> <input type="text"
							name="categoryName" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Category Code</label> <input type="text"
							name="categoryCode" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Product price</label> <input type="text"
							name="productPrice" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Currency</label> <input type="text" name="currency"
							class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Location</label> <input type="text" name="location"
							class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Inventory Available</label> <input type="text"
							name="invaentoryAvailable" class="form-control">
					</div>

					<button class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>