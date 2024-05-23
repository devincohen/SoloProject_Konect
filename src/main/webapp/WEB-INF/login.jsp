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
    <title>Login</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body class="background">
   <h1 style="text-align: right">Login page</h1>
   <div class="column padded right_align rounded">
   		<form:form action="/login" method="post" modelAttribute="newLogin">
   			<div>
   				<form:label path="email" class="form-label">Email: </form:label>
   				<form:input path="email" class="form-control"></form:input>
   			</div>
   			<div>
   				<form:label path="password" class="form-label">Password: </form:label>
   				<form:input path="password" class="form-control" type="password"></form:input>
   			</div>
   			<input type="submit" value="Login" class="btn btn-success btn-lg btn-block">
   			<c:forEach var="error" items="${loginErrors}">
				<p class="error">${error.defaultMessage}</p>
			</c:forEach>
   		
   		</form:form>
	   		<div>
	   			<a href="/register">Need to make an account? Register here!</a>
	   		</div>
   </div>
   <h1 class="banner"> Welcome to <span style="color: white">Konect</span></h1>
   
</body>
</html>