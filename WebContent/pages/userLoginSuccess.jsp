<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome ${id}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/usermain.css" />
</head>
<body>
	<!-- Header -->
			<header id="header" class="alt">
				<div class="logo"><a href="index.html">Hello, <span>${id}</span></a></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">		
				<ul class="links">
					<li><a href="UserController?action=changePassword">Change Password</a></li>
					<li><a href="UserController?action=bookTicket">Book Ticket</a></li>
					<li><a href="UserController?action=viewTicketPage">View Ticket</a></li>
					<li><a href="UserController?action=userLogin">Logout</a></li>
				</ul>
			</nav>

		<!-- Banner -->
			<section class="banner full">
				<article>
					<img src="images/toptheatre1.jpg" alt="" />
					<div class="inner">
						<header>
							<p>Entertainment for everyone</p>
							<h2>ACE PVR Ltd.</h2>
						</header>
					</div>
				</article>
				<article>
					<img src="images/toptheatrepopcorn.jpg" alt="" />
					<div class="inner">
						<header>
							<p>Bollywood in town</p>
							<h2>ACE PVR Ltd.</h2>
						</header>
					</div>
				</article>
				<article>
					<img src="images/toptheatre2.jpg"  alt="" />
					<div class="inner">
						<header>
							<p>A theatre you will fall in love with</p>
							<h2>ACE PVR Ltd.</h2>
						</header>
					</div>
				</article>
				<article>
					<img src="images/toptheatrepopcornprod.jpg"  alt="" />
					<div class="inner">
						<header>
							<p>Catch films before everyone</p>
							<h2>ACE PVR Ltd.</h2>
						</header>
					</div>
				</article>
				<article>
					<img src="images/toptheatrepopcorn2.jpg"  alt="" />
					<div class="inner">
						<header>
							<p>Where you can enjoy</p>
							<h2>ACE PVR Ltd.</h2>
						</header>
					</div>
				</article>
			</section>

		<!-- One -->
			<section id="one" class="wrapper style2">
				<div class="inner">
					<div class="grid-style">

						<div>
							<div class="box">
								<div class="image fit">
									<img src="images/resizethreeperson.jpg" alt="" />
								</div>
								<div class="content">
									<header class="align-center">
										<p>About Us</p>
										<h2>The Three Musketeers</h2>
									</header>
									<p> What happens when 3 long-time friends go holidaying together in South Africa? The seed of a BigTree is planted. A company is planned, from roots to fruits. Soon after the Eureka moment, C.E.O. Ashish Hemrajani quits his job at JWT, Co-Founder Parikshit Dar takes over Technology, and Co-Founder Rajesh Balpande takes over Finance.</p>
									<footer class="align-center">
										<a href="https://in.bookmyshow.com/aboutus" class="button alt">Learn More</a>
									</footer>
								</div>
							</div>
						</div>

						<div>
							<div class="box">
								<div class="image fit">
									<img src="images/behindthescenes.jpg" alt="" />
								</div>
								<div class="content">
									<header class="align-center">
										<p>How We Work</p>
										<h2>Behind the scenes</h2>
									</header>
									<p> Someone has an idea for a movie.
They create an outline and use it to promote interest in the idea.
A studio or independent investor decides to purchase rights to the film.
People are brought together to make the film (screenwriter, producer, director, cast, crew).
The film is completed and sent to the studio and so on..

</p>
									<footer class="align-center">
										<a href="https://www.filmdaily.tv/film-school/how-film-distribution-works" class="button alt">Learn More</a>
									</footer>
								</div>
							</div>
						</div>

					</div>
				</div>
			</section>

		<!-- Two -->
			


		<!-- Footer -->
			<footer id="footer">
				<div class="container">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
					</ul>
				</div>
				<div class="copyright">
					&copy; Untitled. All rights reserved.
				</div>
			</footer>

		<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<script src="js/main.js"></script>

	</body>
</html>