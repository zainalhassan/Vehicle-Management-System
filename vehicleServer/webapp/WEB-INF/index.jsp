<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Vehicle Database - Index</title>
</head>
<body>
<div class="container">
<table class = "table table-striped">



<th>Vehicle ID</th>
	<th>Make</th>
	<th>Model</th>
	<th>Year</th>
	<th>Price</th>
	<th>License Number</th>
	<th>Colour</th>
	<th>Number of Doors</th>
	<th>Transmission</th>
	<th>Mileage</th>
	<th>Fuel Type</th>
	<th>Engine Size</th>
	<th>Body Style</th>
	<th>Condition</th>
	<th>Notes</th>
	<th> Delete </th>

<c:forEach items="${allVehicles}" var="c">
<tr>
	<th>${c.getVehicle_id()}</th>
	<td>${c.getMake()}</td>
	<td>${c.getModel()}</td>
	<td>${c.getYear()}</td>
	<td>${c.getPrice()}</td>
	<td>${c.getLicense_number()}</td>
	<td>${c.getColour()}</td>
	<td>${c.getNumber_doors()}</td>
	<td>${c.getTransmission()}</td>
	<td>${c.getMileage()}</td>
	<td>${c.getFuel_type()}</td>
	<td>${c.getEngine_size()}</td>
	<td>${c.getBody_style()}</td>
	<td>${c.getCondition()}</td>
	<td>${c.getNotes()}</td>
	<td> <form action = "./delete" method "GET"> <button name = "deleteButton" type = "submit" value = "${c.getVehicle_id()}"> Delete </button> </form> </td>
</tr>

</c:forEach>

</table>

<form action = "./login" method = "GET"> 
	<button name = "loginButton" type = "submit"> Login </button>
</form>
<form action = "./insert" method = "POST">
	<button name = "insertButton" type = "submit"> Insert New Vehicle </button>
</form>
</div>
</body>
</html>