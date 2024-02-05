<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--%@ page import="java.io.*,java.util.*,java.sql.*,javax.servlet.*" %-->
<!--%@ page import="javax.servlet.http.*,javax.servlet.annotation.*" %-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
<link rel="stylesheet" href="AddUpdate.css">
</head>
<body>
	<div class="wrapper">
		<form action="AddCustomerServlet" method="post">
			<h1>Add Customer</h1>

			<div class="input-box">
				<input type="text" placeholder="First Name" name="first_name"
					required>
			</div>

			<div class="input-box">
				<input type="text" placeholder="Last Name" name="last_name" required>
			</div>
				
				<div class="input-box">
				<input type="text" placeholder="Street" name="street" required>
			</div>
			<div class="input-box">
				<input type="text" placeholder="Address" name="address" required>
			</div>

			<div class="input-box">
				<input type="text" placeholder="City" name="city" required>
			</div>

			<div class="input-box">
				<input type="text" placeholder="State" name="state" required>
			</div>

			<div class="input-box">
				<input type="text" placeholder="Email" name="email" required>
			</div>

			<div class="input-box">
				<input type="text" placeholder="Phone" name="phone" required>
			</div>
			<br> <br>
			<button type="submit" class="btn">Submit</button>
		</form>
	</div>
</body>
</html>
