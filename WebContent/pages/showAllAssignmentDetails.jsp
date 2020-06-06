<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
  background-image: url("images/theatre6.jpg");
  
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
  top: 30%;
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
</style>
</head>
<body>
<div class="bg-image"></div>
<div class="bg-text">
	<form>
		<table>
			<tr>
				<th>Assignment Id</th>
				<th>Movie Name</th>
				<th>Theatre Name</th>
				<th>Assigned Date</th>
			</tr>
		<c:forEach items="${allAssignmentDetailsList}" var="allassignment">
			<tr>
				<td>${allassignment.assignmentId}</td>
				<td>${allassignment.moviename}</td>
				<td>${allassignment.theatrename}</td>
				<td>${allassignment.assignedDate}</td>
			</tr>
		</c:forEach>
		</table>
		<a href="MovieController?action=viewassignmentPage">Back</a>
	</form>
	</div>
</body>
</html>