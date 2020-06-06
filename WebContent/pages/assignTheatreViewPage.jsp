<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  background-image: url("images/theatre7.jpg");
  
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
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  width: 80%;
  padding: 20px;
  text-align: center;
}
</style>
<title>Assign theatre</title>
</head>
<body>
<div class="bg-image"></div>
<div class="bg-text">
<form action="MovieController" method="post">

Select Movie Name: <select name="movieName" required>
			<option value="">Pls Select Movie Name</option>	
			<c:forEach items="${moviedetailsList}" var="moviedetailsList">
				<option value="${moviedetailsList}">${moviedetailsList}</option>
			</c:forEach>
		</select> <BR>
		
Select Theatre Name: <select name="theatreName" required>
			<option value="">Pls Select Theatre Name</option>	
			<c:forEach items="${theatredetailsList}" var="theatredetailsList">
				<option value="${theatredetailsList}">${theatredetailsList}</option>
			</c:forEach>
		</select> <BR>
		Enter Date(dd/mm/yyyy) :<input type="text" name="assignedDate" value="01/05/2020" placeholder="(dd/mm/yyyy)"/>
		<input type="submit" value="Assign" name="action">
	</form>
	</div>
</body>
</html>