<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Import necessary classes if needed -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Customer</title>
    <link rel="stylesheet" href="AddUpdate.css">
</head>
<body>
    <div class="wrapper">
        <form action="UpdateCustomerServlet" method="post">
            <h1>Update Customer</h1>

            <!-- Add hidden input for customerId -->
            <input type="hidden" name="customerId" value="${customer.customer_id}">

            <div class="input-box">
                <input type="text" placeholder="First Name" name="first_name" value="${customer.first_name}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="Last Name" name="last_name" value="${customer.last_name}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="Street" name="street" value="${customer.street}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="Address" name="address" value="${customer.address}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="City" name="city" value="${customer.city}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="State" name="state" value="${customer.state}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="Email" name="email" value="${customer.email}" required>
            </div>

            <div class="input-box">
                <input type="text" placeholder="Phone" name="phone" value="${customer.phone}" required>
            </div>
            <br> <br>
            <button type="submit" class="btn">Submit</button>
        </form>
    </div>
</body>
</html>
