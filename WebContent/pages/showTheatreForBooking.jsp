<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
body, html {
  height: 100%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

* {
  box-sizing: border-box;
}

.bg-image {
  /* The image used */
  background-image: url("images/theatre1.jpg");
  
  /* Add the blur effect */
  filter: blur(0px);
  -webkit-filter: blur(0px);
  
  /* Full height */
  height: 100%; 
  
  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

/* Position text in the middle of the page/image */
.bg-text {
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0, 0.4); /* Black w/opacity/see-through */
  color: white;
  font-weight: bold;
  border: 3px solid #f1f1f1;
  position: absolute;
  top: 48%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  width: 80%;
  padding: 20px;
  text-align: center;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(odd) {
  background-color: #470945;
  }
  
tr:nth-child(even) {
  background-color: #367cc2;
  }
  
  ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
  border-right:1px solid #bbb;
}

li:last-child {
  border-right: none;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}
</style>
<title>Insert title here</title>
</head>
<body>
<ul>
  <li><a href="UserController?action=home&id=${id}">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="UserController?action=contactUs&id=${id}">Contact Us</a></li>
  <li style="float:right"><a class="active" href="UserController?action=about&id=${id}">About</a></li>
</ul>
<div class="bg-image"></div>
<div class="bg-text">
<h2>Movie Name: ${movieName}</h2>
<h2>Show Date:${assignedDate}</h2> 
	<br>
	<form>
		<table>
			<tr>
				<th>Theatre Name</th>
				<th>Theatre Address</th>
				<th>Cost</th>
				<th>Total Tickets Available</th>
				<th></th>
			</tr>
		<c:forEach items="${movieBookingList}" var="theatre">
			<tr>
				<td>${theatre.theaterName}</td>
				<td>${theatre.theaterAddress}</td>
				<td>${theatre.ticketCost}</td>	
				<td>
				<c:choose>
					<c:when test="${theatre.totalNoOfTickets == 0}">
						All bookings full
					</c:when>
					<c:otherwise>
						${theatre.totalNoOfTickets}
					</c:otherwise>
				</c:choose>
				<td>
				<c:choose>
					<c:when test="${theatre.totalNoOfTickets == 0}">
						All bookings full
					</c:when>
					<c:otherwise>
						<a href="UserController?action=ConfirmBooking&movieName=${movieName}&theaterName=${theatre.theaterName}&assignedDate=${assignedDate}&ticketCost=${theatre.ticketCost}&totalNoOfTickets=${theatre.totalNoOfTickets}">Click To Book</a>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</c:forEach>
		</table>
		<a href="MovieController?action=ConfirmBooking&movieName=${theatre.movieName}&theaterName=${theatre.theaterName}&assignedDate=${theatre.assignedDate}&ticketCost=${theatre.ticketCost}&totalNoOfTickets=${theatre.toalNoOfTickets}">Back</a>
	</form>
	</div>
</body>
</html>