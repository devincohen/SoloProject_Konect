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
	<div class="column padded">
		<form:form action="/connection/${id}/edit" method="put" modelAttribute="household">
			<input type="hidden" value="_method" value="put"/>
			<form:input type="hidden" path="houseCreator" value="${user.id}"/>
   			<div>
   				<form:label path="name" class="form-label">Household Name: </form:label>
   				<form:errors path="name" class="error"></form:errors>
   				<form:input path="name" class="form-control" value="${ household.name}"></form:input>
   			</div>
   			<div>
   				<form:label path="streetAddress" class="form-label">Street Address: </form:label>
   				<form:errors path="streetAddress" class="error"></form:errors>
   				<form:input path="streetAddress" class="form-control" value="${ household.streetAddress}"></form:input>
   			</div>
   			<div>
   				<form:label path="streetAddress2" class="form-label">Address Line 2: </form:label>
   				<form:errors path="streetAddress2" class="error"></form:errors>
   				<form:input path="streetAddress2" class="form-control" value="${ household.streetAddress2}"></form:input>
   			</div>
   			<div>
   				<form:label path="city" class="form-label">City: </form:label>
   				<form:errors path="city" class="error"></form:errors>
   				<form:input path="city" class="form-control" value="${ household.city}"></form:input>
   			</div>
   			<div>
   				<form:label path="state" class="form-label">State: </form:label>
   				<form:errors path="state" class="error"></form:errors>
   				<form:input path="state" class="form-control" value="${ household.state}"></form:input>
   			</div>
   			<div>
   				<form:label path="zip" class="form-label">Zip: </form:label>
   				<form:errors path="zip" class="error"></form:errors>
   				<form:input path="zip" class="form-control" value="${ household.zip}"></form:input>
   			</div>
   			<div>
   				<form:label path="notes" class="form-label">Notes: </form:label>
   				<form:errors path="notes" class="error"></form:errors>
   				<form:textarea path="notes" class="form-control" value="${ household.notes}"></form:textarea>
   			</div>
   			<input type="submit" value="Submit" class="btn btn-success"> 		
   		</form:form>
	</div>
</body>
</html>