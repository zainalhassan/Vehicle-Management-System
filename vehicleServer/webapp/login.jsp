<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Vehicle Database - Login</title>
</head>
<body>

<div class="container">
	<form action = "./login" method = "POST">
		<label> Username: </label>
		<input type = "text" name = "usernameInput">
		<br>
		<label> Password: </label>
		<input type = "text" name = "passwordInput">
		<button name = "loginButton" type = "submit"> Login </button>
		<a type = "button" href = "./home"> go back </a>
	</form>
</div>


</body>
</html>