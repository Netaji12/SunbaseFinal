<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.customer.model.customerInfo_model, com.customer.daoImpl.customer_info_Impl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer List</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- Your other stylesheets -->
<link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
	<div class="container">
		<h1>Customer List</h1>

		<div class="button-container">
			<button onclick="location.href='AddCustomer.jsp'">Add
				Customer</button>

			<!-- Search dropdown button -->
			<form action="index.jsp" method="get" style="display: inline-block;">
				<div class="dropdown">
					<button class="dropbtn">Search by</button>
					<div class="dropdown-content">
						<label for="filterBy">Filter by:</label> <select id="filterBy"
							name="filterBy">
							<option value="first_name">First Name</option>
							<option value="city">City</option>
							<option value="email">Email</option>
							<option value="phone">Phone</option>
						</select> <input type="text" name="filterValue" placeholder="Enter value">
						<button type="submit">Filter</button>
					</div>
				</div>
			</form>
			<!-- Separate button for displaying all customers -->
			<form action="index.jsp" method="get" style="display: inline-block;">
				<button type="submit" name="showAllCustomers">All Customers</button>
			</form>
		</div>

		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Address</th>
						<th>City</th>
						<th>Street</th>
						<th>State</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<!-- Fetching data from the servlet -->
					<%
					// Fetching customer list from the DAO
					customer_info_Impl customerDAO = new customer_info_Impl();

					// Check if filtering is requested
					String filterBy = request.getParameter("filterBy");
					String filterValue = request.getParameter("filterValue");

					// Pagination parameters
					int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
					int filterPage = (request.getParameter("filterPage") != null)
							? Integer.parseInt(request.getParameter("filterPage"))
							: 1;
					int recordsPerPage = 10;
					int offset = (currentPage - 1) * recordsPerPage;

					List<customerInfo_model> customerList;
					if (filterBy != null && filterValue != null && !filterValue.isEmpty()) {
						customerList = customerDAO.getFilteredCustomers(filterBy, filterValue, (filterPage - 1) * recordsPerPage,
						recordsPerPage);
					} else {
						customerList = customerDAO.getListOfCustomersPaginated(offset, recordsPerPage);
					}

					if (customerList != null && !customerList.isEmpty()) {
						for (customerInfo_model customer : customerList) {
					%>
					<tr>
						<td><%=customer.getFirst_name()%></td>
						<td><%=customer.getLast_name()%></td>
						<td><%=customer.getAddress()%></td>
						<td><%=customer.getCity()%></td>
						<td><%=customer.getStreet()%></td>
						<td><%=customer.getState()%></td>
						<td><%=customer.getEmail()%></td>
						<td><%=customer.getPhone()%></td>
						<td>
							<form action="ShowUpdateFormServlet" method="get"
								style="display: inline-block;">
								<input type="hidden" name="customerId"
									value="<%=customer.getCustomer_id()%>">
								<button class="edit-button" type="submit">
									<i class="fas fa-pencil-alt"></i>
								</button>
							</form>
							<form action="DeleteCustomerServlet" method="post"
								onsubmit="return confirmDelete();"
								style="display: inline-block;">
								<input type="hidden" name="customerId"
									value="<%=customer.getCustomer_id()%>">
								<button class="delete-button" type="submit">
									<i class="fas fa-trash-alt"></i>
								</button>
							</form>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="8">No customer data available</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<!-- Pagination links -->
			<div class="pagination">
				<%-- Assuming totalRecords is the total number of records in the database --%>
				<%
				int totalRecords = customerDAO.getNumberOfCustomers();
				%>
				<%
				int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
				%>

				<%-- Previous Page Link --%>
				<%
				if (currentPage > 1) {
				%>
				<a
					href="index.jsp?page=<%=currentPage - 1%>&filterPage=<%=filterPage%>">&laquo;
					Previous</a>
				<%
				}
				%>

				<%-- Page Numbers --%>
				<%
				for (int i = 1; i <= totalPages; i++) {
				%>
				<a href="index.jsp?page=<%=i%>&filterPage=<%=filterPage%>"><%=i%></a>
				<%
				}
				%>

				<%-- Next Page Link --%>
				<%
				if (currentPage < totalPages) {
				%>
				<a
					href="index.jsp?page=<%=currentPage + 1%>&filterPage=<%=filterPage%>">Next
					&raquo;</a>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<script>
		function confirmDelete() {
			if (confirm("Are you sure you want to delete this customer?")) {
				return true; // Proceed with deletion
			} else {
				return false; // Cancel deletion
			}
		}

		function showDeleteSuccess() {
			alert("Customer deleted successfully.");
		}
	</script>
</body>
</html>
