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
  background-image: url("images/spotlight1.jpg");
  
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
<title>Update Theatre</title>
</head>
<body>
<div class="bg-image"></div>

<div class="bg-text">
<form method="post" action="MovieController" >
		Select Theatre Name: <select name="theatreName" required>
			<option value="">Pls Select Theatre Name</option>	
			<c:forEach items="${theatredetailsList}" var="theatredetailsList">
				<option value="${theatredetailsList}">${theatredetailsList}</option>
			</c:forEach>
		</select> <BR>
		Theatre Address: <input type="text" name="taddress" placeholder="Enter Theatre address" required><BR/>
		Theatre Size: <input type="number" name="tsize" pattern="[0-9]+" min="50" placeholder="Enter Theatre Size(min 50)" required><BR/>
		Ticket Cost: <input type="number" name="tcost" pattern="[0-9]+" min="70" placeholder="Enter Cost per Ticket(min 70rs.)" required><BR/>
		Theatre Manager: <input type="text" name="tmanager" placeholder="Enter Manager name"  pattern="[a-zA-Z]*" oninvalid="setCustomValidity('Please enter alphabets only. ')" required><BR/>
		<input type="submit" value="Update Theatre" name="action" /> 
		<input type="reset">
</form>
</div>
</body>
</html>