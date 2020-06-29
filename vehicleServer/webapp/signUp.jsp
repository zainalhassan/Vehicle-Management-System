<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>add User</title>
</head>
<body>
<form action = "./signup" method = "POST">
	<label> First Name </label> <input type = "text" name = "insertFirstname">
	<br>
	<label> Surname </label> <input type = "text" name = "insertSurname">
	<br>
	<label> Username </label> <input type = "text" name = "insertUsername">
	<br>
	<label> Password </label> <input type = "text" name = "insertPassword">
	<br>
	<label> User Type </label> <input type = "text" name = "insertUser_type">
	<br>
	<button type = "submit" > Sign up </button>
</form>

</body> 
</body>
</html>