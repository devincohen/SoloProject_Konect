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
    <title>Register</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body class="background">
   <h1 style="text-align: right">Register page</h1>
   <div class="column padded right_align rounded">
   		<form:form action="/register" method="post" modelAttribute="newUser">
   			<div>
   				<form:label path="fname" class="form-label">First Name: </form:label>
   				<form:errors path="fname" class="error"></form:errors>
   				<form:input path="fname" class="form-control"></form:input>
   			</div>
   			<div>
   				<form:label path="lname" class="form-label">Last name: </form:label>
   				<form:errors path="lname" class="error"></form:errors>
   				<form:input path="lname" class="form-control"></form:input>
   			</div>
   			<div>
   				<form:label path="email" class="form-label">Email: </form:label>
   				<form:errors path="email" class="error"></form:errors>
   				<form:input path="email" class="form-control"></form:input>
   			</div>
   			<div>
   				<form:label path="password" class="form-label">Password: </form:label>
   				<form:errors path="password" class="error"></form:errors>
   				<form:input path="password" class="form-control" type="password"></form:input>
   			</div>
   			<div>
   				<form:label path="confirm" class="form-label">Confirm: </form:label>
   				<form:errors path="confirm" class="error"></form:errors>
   				<form:input path="confirm" class="form-control" type="password"></form:input>
   			</div>
   			<input type="submit" value="Submit" class="btn btn-success btn-block">
   			
   		
   		</form:form>
   		
   		
   		<div>
   			<a href="/login">Already a user? Login here!</a>
   		</div>
   </div>
      <h1 class="banner"> Welcome to <span style="color: white">Konect</span></h1>
   
</body>
</html>