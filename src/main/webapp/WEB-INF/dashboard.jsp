<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
<!-- navbar -->
   <div class="navbar padded">
	   	<div>
	   		<h1>Dashboard</h1>
	   		<h3>Welcome, ${user.fname}</h3>
	   	</div>
	   	<div>
	   		<h3>Konect Outreach Center</h3>
	   		<h5>1234 Main St, Houston, TX 77047</h5>
	   		<a href="/logout" class="btn btn-outline-danger">LOG OUT</a>	
	   		
	   	</div>
   </div>
   
<!-- body -->

<!-- left column -->
	<div>
	
	</div>
<!-- right column -->
	<div>
		<a href="/new-connection" class="btn btn-success">Add Connection</a>
		<!-- list of households -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Household Name</th>
					<th>Address</th>
					<th>Options</th>
				</tr>
			
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${households.size()==0}">
						<tr>
							<td>No connections yet</td>
						</tr>
					</c:when>
					<c:when test="${households.size()>0}">
						<c:forEach var="household" items="${households}">
							<tr>
								<td>${household.name}</td>
								<td>${household.streetAddress} ${household.streetAddress2}, ${household.city}, ${household.state} ${household.zip} </td>
								<td class="horizontal-buttons">
									<a href="/connection/${household.id}" class="btn btn-outline-info">View</a>		
									<a href="/connection/${household.id}/edit" class="btn btn-outline-warning">Edit</a>
									<form:form action="/connection/${household.id}/delete" method="post">
									    <input type="hidden" name="_method" value="delete">
									    <input type="submit" value="Delete" class="btn btn-danger" >
									</form:form>
								</td>
							
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		
		</table>
	</div>
</body>
</html>