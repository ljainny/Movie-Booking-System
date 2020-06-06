<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
<form method="post" action="UserController" >
		Name: <input type="text" name="uname" placeholder="Name..." ><BR/>
		Password: <input type="text" name="upwd" placeholder="*************"><BR/>
		Gender: <input type="text" name="gender" placeholder="Gender(M/F)"><BR/>
		Phone: <input type="text" name="phone" placeholder="Phone No"><BR/>
		Email <input type="text" name="email"  placeholder="Email address..."><BR/>
		Address <input type="text" name="address" placeholder="Address"><BR/>
		Age: <input type="text" name="age" placeholder="Age "><BR/>
		<input type="submit" value="Sign Up" name="action" /> 
</form>
</body>
</html>