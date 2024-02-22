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
	display: inline-block;
	margin-left: 37%;
	margin-bottom: 0;
	margin-top: 0;
}

.mb-3 {
	margin-bottom: 10px;
}

.home {
	display: inline-block;
	margin-top: 0;
	margin-left: 1%;
}
</style>

</head>
<body>
	<button type="button" class="home add btn btn-light">
		<a style="text-decoration: none" href="home"><strong>Home</strong></a>
	</button>
	<h1>Add Product</h1>
	<div class="card-div">
		<div class="card" style="width: 25rem;">
			<div class="card-body">


				<%-- Check for success message --%>
				<c:if test="${not empty successMessage}">
					<div class="alert alert-success">${successMessage}</div>
				</c:if>

				<%-- Check for error message --%>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>


				<form action="save" method="post"">
					<div class="mb-3">
						<label>Enter Product Code</label> <input type="text"
							name="productCode" id="productCode" class="form-control" required>
					</div>
					<div class="mb-3">
						<label>Enter Product Description</label> <input type="text"
							name="productDescription" id="productDescription"
							class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Product Name</label> <input type="text"
							name="productName" id="productName" class="form-control" required>
					</div>

					<div class="mb-3">
						<label>Enter Category Name</label> <input type="text"
							name="categoryName" id="categoryName" class="form-control"
							required>
					</div>

					<div class="mb-3">
						<label>Enter Product price</label> <input type="number"
							name="productPrice" id="productPrice" class="form-control"
							required>
					</div>

					<div class="mb-3">
						<label>Enter Currency</label> <input type="text" name="currency"
							id="currency" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Location</label> <input type="text" name="location"
							id="location" class="form-control">
					</div>

					<div class="mb-3">
						<label>Enter Inventory Available</label> <input type="number"
							name="invaentoryAvailable" id="invaentoryAvailable"
							class="form-control">
					</div>

					<button class="btn btn-primary">
						<strong>Submit</strong>
					</button>
				</form>
			</div>
		</div>
	</div>

	<script>
		function validateForm() {
			let fieldValue = document.getElementById('fieldId').value.trim();
			if (fieldValue === '') {
				alert('Field cannot be empty!');
				return false;
			}
			// You can add more validation rules here if needed
			return true;
		}
	</script>
</body>
</html>