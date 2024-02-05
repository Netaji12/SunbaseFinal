<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--%@ page import="java.io.*,java.util.*,java.sql.*,javax.servlet.*"%-->
<!--%@ page import="javax.servlet.http.*,javax.servlet.annotation.*"%-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogIn</title>
<link rel="stylesheet" href="login.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
</head>
<body>

	<div class="wrapper">
		<form action="LoginServlet" method="post">
			<h1>Login</h1>
			<div class="input-box">
				<input type="text" placeholder="Login ID" name="login_id" required>
				<i class='bx bxs-user'></i>
			</div>

			<div class="input-box">
				<input type="text" placeholder="Password" name="Password" required>
				<i class='bx bxs-lock-alt'></i>
			</div>
			<button type="submit" class="btn">Submit</button>
		</form>
	</div>
</body>
</html>