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
   <div class="navbar">
	   	<div>
	   		<h1>Dashboard</h1>
	   		<h3>Welcome, ${user.fname}</h3>
	   	</div>
	   	<div>
	   		<h3>Konect Outreach Center</h3>
	   		<h5>1234 Main St, Houston, TX 77047</h5>
	   	</div>
   </div>
   
<!-- body -->

<!-- left column -->
	<div>
	
	</div>
<!-- right column -->
	<div>
		<div>
			<table>
				<thead>
				
				</thead>
				<tbody>
					<tr>
						<th>Household Name: </th>
						<td>${household.name}</td>
					</tr>
					<tr>
						<th>Address: </th>
						<td>${household.streetAddress} ${household.streetAddress2}, ${household.city}, ${household.state} ${household.zip}</td>
					</tr>
					<tr>
						<th>Notes: </th>
						<td>${household.notes}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="column padded">
			<h3>Edit a house member:</h3>
			<form:form action="/connection/${household.id}/edit/${member.id}" method="put" modelAttribute="member">
				<input type="hidden" value="_method" value="put"/>
				<form:input type="hidden" path="memberCreator" value="${user.id }" />
				<form:input type="hidden" path="household" value="${household.id }" />
				<div>
	   				<form:label path="fname" class="form-label">First Name: </form:label>
	   				<form:errors path="fname"></form:errors>
	   				<form:input path="fname" class="form-control" value="${member.fname}"></form:input>
	   			</div>
	   			<div>
	   				<form:label path="lname" class="form-label" >Last Name: </form:label>
	   				<form:errors path="lname"></form:errors>
	   				<form:input path="lname" class="form-control" value="${ member.lname}"></form:input>
	   			</div>
	   			<div>
	   				<form:label path="birthday" class="form-label" >Birthday: </form:label>
	   				<form:errors path="birthday"></form:errors>
	   				<form:input type="date" class="form-control" value="${member.birthday}" path="birthday"></form:input>
	   			</div>
   				<input type="submit" value="Submit" class="btn btn-success"> 		
			</form:form>
			<a href="/dashboard">Home</a>
		</div>
		
		
	</div>
</body>
</html>