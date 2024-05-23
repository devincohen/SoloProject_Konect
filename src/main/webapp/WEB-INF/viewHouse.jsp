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
	   	</div>
   </div>
   
<!-- body -->

<!-- left column -->
	<div>
	
	</div>
<!-- right column -->
	<div>
		<div>
			<table class="table table-striped">
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
		<div class="centered padded column" >
			<h3>Add a house member:</h3>
			<form:form action="/connection/${household.id}/add" method="post" modelAttribute="newMember">
				<form:input type="hidden" path="memberCreator" value="${user.id}" />
				<form:input type="hidden" path="household" value="${household.id }" />
				<div>
	   				<form:label path="fname" class="form-label">First Name: </form:label>
	   				<form:errors path="fname" class="error"></form:errors>
	   				<form:input path="fname" class="form-control"></form:input>
	   			</div>
	   			<div>
	   				<form:label path="lname" class="form-label">Last Name: </form:label>
	   				<form:errors path="lname" class="error"></form:errors>
	   				<form:input path="lname" class="form-control"></form:input>
	   			</div>
	   			<div>
	   				<form:label path="birthday" class="form-label">Birthday: </form:label>
	   				<form:errors path="birthday" class="error"></form:errors>
	   				<form:input type="date" path="birthday" class="form-control"></form:input>
	   			</div>
   				<input type="submit" value="Submit" class="btn btn-success"> 		
			</form:form>
			<a href="/dashboard">Home</a>
		</div>
		
		<div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Birthday</th>
						<th>Age</th>
						<th>Options </th>
					</tr>
				
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${members.size()==0}">
							<tr>
								<td>No House members yet</td>
							</tr>
						</c:when>
						<c:when test="${members.size()>0}">
							<c:forEach var="member" items="${members}">
								<tr>
									<td>${member.fname } ${member.lname }</td>
									<td>${member.birthday.month+1}/${member.birthday.date}/${member.birthday.year + 1900}</td>
									<td> ${Math.floor((today.getTime()-member.birthday.getTime())/1000/60/60/24/365)} </td>
									<td>
										<a href="/connection/${household.id}/edit/${member.id}">Edit</a>
										<form:form action="/connection/${household.id}/delete/${member.id}" method="delete">
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
	</div>
</body>
</html>