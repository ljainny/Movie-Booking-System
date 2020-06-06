<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
header {
  background-color: #3333cc;
  padding: 30px;
  text-align: center;
  font-size: 35px;
  color: white;
}

/* Container for flexboxes */
section {
  display: -webkit-flex;
  display: flex;
}

/* Style the navigation menu */
nav {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  background: #ccc;
  padding: 20px;
}

/* Style the list inside the menu */
nav ul {
  list-style-type: none;
  padding: 0;
}

/* Style the content */
article {
  -webkit-flex: 3;
  -ms-flex: 3;
  flex: 3;
  background-color: #f1f1f1;
  padding: 10px;
}

/* Style the footer */
footer {
  background-color: #3333cc;
  padding: 10px;
  text-align: center;
  color: white;
}

/* Responsive layout - makes the menu and the content (inside the section) sit on top of each other instead of next to each other */
@media (max-width: 600px) {
  section {
    -webkit-flex-direction: column;
    flex-direction: column;
  }
}
</style>

<title>Admin Page</title>
</head>
<body>
<header>
  <h2>Welcome Admin</h2>
</header>

<section>
  <nav>
    <ul>
      <li><a href="MovieController?action=addMovieViewPage">Add Movie</a></li>
      <li><a href="MovieController?action=viewMovieViewPage">View Movie</a></li>
      <li><a href="MovieController?action=addTheatreViewPage">Add Theatre</a></li>
	  <li><a href="MovieController?action=viewTheatreViewPage">View Theatre</a></li>
	  <li><a href="MovieController?action=assignTheatreViewPage">Assign Theatre</a></li>
	  <li><a href="MovieController?action=viewassignmentPage">View Assignment</a></li>
	  <li><a href="MovieController?action=updateTheatreViewPage">Update Theatre</a></li>
    </ul>
  </nav>
  
  <article>
    <h1>Movie Facts</h1>
    <li>
	When was the first movie made ?<br>
1888
Roundhay Garden Scene (1888)<br>
The world's earliest surviving motion-picture film, showing actual consecutive action is called Roundhay Garden Scene. It's a short film directed by French inventor Louis Le Prince.</li>
<br>
<li>
What was first Colour movie ?<br>
The 1935 film “Becky Sharp”—based on Thackeray's novel “Vanity Fair” is generally regarded as the first “color movie” meaning the first to use the technology of three-color Technicolor.</li> 
<br>
<li>
How did movies start ?<br>
The movie industry as we know it today originated in the early 19th century through a series of technological developments: the creation of photography, the discovery of the illusion of motion by combining individual still images, and the study of human and animal locomotion. </li>
</article>
</section>

<footer>
  <p>Movie Booking App	</p>
</footer>


</body>
</html>