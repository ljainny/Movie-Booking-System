package com.igate.moviebooking.controller;

/*
 * MovieController servlet will dispatch control to different pages based upon the action parameter 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igate.moviebooking.dto.AdminLogin;
import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.Movie_Assignment;
import com.igate.moviebooking.dto.Movie_Details;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.dto.Theatre_Details;
import com.igate.moviebooking.dto.UserLogin;
import com.igate.moviebooking.exception.MovieException;
import com.igate.moviebooking.service.IMovieService;
import com.igate.moviebooking.service.MovieServiceImpl;

/**
 * Servlet implementation class MovieController
 */
@WebServlet("/MovieController")
public class MovieController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher view = null;
		IMovieService service = new MovieServiceImpl();
		// Initial setting the session parameter to false
		HttpSession session = request.getSession(false);
		
		if (action != null && action.equalsIgnoreCase("adminLogin")) {
			try {
				//ArrayList<String> movieNames = service.getMovieNames();
				//request.setAttribute("movieNames", movieNames);
				view = getServletContext().getRequestDispatcher(
						"/pages/adminLogin.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		
		// action for Admin Validation
				if (action != null && action.equalsIgnoreCase("Submit")) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						String id = request.getParameter("aName");
						String pswd = request.getParameter("aPassword");
						AdminLogin adminlogin = new AdminLogin();
						adminlogin.setaName(id);
						adminlogin.setaPassword(pswd);
						boolean loginflag = service.validateAdminLogin(adminlogin);
						System.out.println("loginflag "+ loginflag);
						if(loginflag == true){
							view = getServletContext().getRequestDispatcher(
									"/pages/adminLsuccess.jsp");
							view.forward(request, response);
						}
						else{
							PrintWriter out = response.getWriter();
							out.println("<h2>Invalid Id or Password</h2>");
							view = getServletContext().getRequestDispatcher(
									"/pages/loginError.jsp");
							view.forward(request, response);
							//view.include(request, response);
						}
						
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
		//action to add movie details by admin
				if (action != null && action.equalsIgnoreCase("Add")) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						String title = request.getParameter("title");
						String director = request.getParameter("director");
						String cast = request.getParameter("cast");
						String description = request.getParameter("description");
						String status = request.getParameter("status");
							Movie_Details moviedetails = new Movie_Details();
							moviedetails.setTitle(title);
							moviedetails.setDirector(director);
							moviedetails.setCast(cast);
							moviedetails.setDescription(description);
							moviedetails.setStatus(status);
							System.out.println("moviedetails" + title);
							int mId =0;
							int movieId = service.addMovie(moviedetails, mId);
							request.setAttribute("movieId", movieId);
							if( movieId !=0){
								view = getServletContext().getRequestDispatcher(
										"/pages/addMoviesuccess.jsp");
								view.forward(request, response);
							}
						
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
		
				if (action != null && action.equalsIgnoreCase("Add Theatre")) {
					try {
						
						String tname = request.getParameter("tname");
						String taddress = request.getParameter("taddress");
						int tsize = Integer.parseInt(request
								.getParameter("tsize"));
						int tcost = Integer.parseInt(request
								.getParameter("tcost"));
						String tmanager = request.getParameter("tmanager");
						Theatre_Details  theatreDetails = new Theatre_Details();
						theatreDetails.setTheatreName(tname);
						theatreDetails.setTheatreAddress(taddress);
						theatreDetails.setTheatresize(tsize);
						theatreDetails.setTicketCost(tcost);
						theatreDetails.setTheatreManager(tmanager);
						System.out.println("tmanager" + tname);
						int tId =0;
						int theatreId = service.addTheatre(theatreDetails, tId);
						System.out.println("theeeeeeeeeeeeeaaaa id" + theatreId);
						request.setAttribute("theatreId", theatreId);
						if( theatreId !=0){
							view = getServletContext().getRequestDispatcher(
									"/pages/addTheatresuccess.jsp");
							view.forward(request, response);
						}
						
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				
				if (action != null && "addMovieViewPage".equals(action)) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						
						 view = request.getRequestDispatcher("/pages/addMovie.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && "addTheatreViewPage".equals(action)) {
					try {
			
						view = request.getRequestDispatcher("/pages/addTheatre.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && "viewTheatreViewPage".equals(action)) {
					try {
						ArrayList<String> theatreNames = service.getTheatreNames();
						request.setAttribute("theatreNames", theatreNames);
						view = request.getRequestDispatcher("/pages/viewTheatre.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				//action to view assignment page
				if (action != null && "viewassignmentPage".equals(action)) {
					try {
						ArrayList<String> theatreNames = service.getAssignedTheatreNames();
						ArrayList<String> movieNames = service.getAssignedtMovieNames();
						request.setAttribute("theatreNames", theatreNames);
						request.setAttribute("movieNames", movieNames);
						view = request.getRequestDispatcher("/pages/viewAssignmentPage.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				if (action != null && "assignTheatreViewPage".equals(action)) {
					try {
						ArrayList<String> moviedetailsList = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						//ArrayList<String> moviedetailsList = service.getMovieList();
						ArrayList<String> theatredetailsList = service.getTheatreList();
						request.setAttribute("moviedetailsList", moviedetailsList);
						request.setAttribute("theatredetailsList", theatredetailsList);
						 view = request.getRequestDispatcher("/pages/assignTheatreViewPage.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && "updateTheatreViewPage".equals(action)) {
					try {
						ArrayList<String> theatredetailsList = service.getTheatreList();
						request.setAttribute("theatredetailsList", theatredetailsList);
						view = request.getRequestDispatcher("/pages/updateTheatreViewPage.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && action.equalsIgnoreCase("Update Theatre")) {
					try {
						boolean status;
						String tname = request.getParameter("theatreName");
						String taddress = request.getParameter("taddress");
						int tsize = Integer.parseInt(request
								.getParameter("tsize"));
						int tcost = Integer.parseInt(request
								.getParameter("tcost"));
						String tmanager = request.getParameter("tmanager");
						System.out.println("theatre exist");
						Theatre_Details  theatreDetails = new Theatre_Details();
						theatreDetails.setTheatreName(tname);
						theatreDetails.setTheatreAddress(taddress);
						theatreDetails.setTheatresize(tsize);
						theatreDetails.setTicketCost(tcost);
						theatreDetails.setTheatreManager(tmanager);
						status= service.updateTheatre(theatreDetails);
						if(status==true){
							view = request.getRequestDispatcher("/pages/updateTheatreSuccess.jsp");
							view.forward(request, response);
						}
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
		// action to assign theatre and movie with assigneddate
				if (action != null && action.equalsIgnoreCase("Assign")) {
					String movieName = request.getParameter("movieName");
					String theatreName = request.getParameter("theatreName");
					String assignedDate = request.getParameter("assignedDate");
					Movie_Assignment ma = new Movie_Assignment();
					ma.setMoviename(movieName);
					ma.setTheatrename(theatreName);
					ma.setAssignedDate(assignedDate);
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					session.setAttribute("movieName", movieName);
					session.setAttribute("theatreName", theatreName);
					try {
						int assignedId = service.assignedDate(ma);
						session.setAttribute("assignedId", assignedId);
						view = request.getRequestDispatcher("/pages/assignTheatreSuccess.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//viewMovieViewPage
				
				//action to view movie details
				if (action != null && action.equalsIgnoreCase("viewMovieViewPage")) {
					try {
						ArrayList<String> movieNames = service.getMovieNames();
						request.setAttribute("movieNames", movieNames);
						view = getServletContext().getRequestDispatcher(
								"/pages/movieListPage.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
		
		// action to fetch movie names available
		if (action != null && action.equalsIgnoreCase("fetchName")) {
			try {
				ArrayList<String> movieNames = service.getMovieNames();
				request.setAttribute("movieNames", movieNames);
				view = getServletContext().getRequestDispatcher(
						"/pages/movieList.jsp");
				view.forward(request, response);
			} catch (MovieException e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}

		// action to search MovieDetails for a given movie as input
				if (action != null && action.equalsIgnoreCase("Search")) {
					String movieName = request.getParameter("movieName");
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					session.setAttribute("movieName", movieName);
					try {
						ArrayList<Movie_Details> movieDetailsList = service.searchMovieDetails(movieName);
						session.setAttribute("movieDetailsList", movieDetailsList);
						view = getServletContext().getRequestDispatcher(
								"/pages/showMovieDetails.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				// action to search TheatreDetails for a given theatre as input
				if (action != null && action.equalsIgnoreCase("Search Theatre")) {
					String theatreName = request.getParameter("theatreName");
		
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					session.setAttribute("theatreName", theatreName);
					try {
						ArrayList<Theatre_Details> theatreDetailsList = service.searchTheatreDetails(theatreName);
						session.setAttribute("theatreDetailsList", theatreDetailsList);
						view = getServletContext().getRequestDispatcher(
								"/pages/showTheatreDetails.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				// action to search TheatreName and MovieName assignment for a given input
				if (action != null && action.equalsIgnoreCase("View Assignment")) {
					String theatreName = request.getParameter("theatreName");
					String movieName = request.getParameter("movieName");

					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					session.setAttribute("theatreName", theatreName);
					session.setAttribute("movieName", movieName);

					try {
						ArrayList<Movie_Assignment> movieAssignmentDetailsList = service.searchMovieAssignmentDetails(theatreName, movieName);
						session.setAttribute("movieAssignmentDetailsList", movieAssignmentDetailsList);
						view = getServletContext().getRequestDispatcher(
								"/pages/showAssignmentDetails.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				//action to view all theatre details
				if (action != null && action.equalsIgnoreCase("View All Theatre")) {
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					//session.setAttribute("theatreName", theatreName);
					try {
						ArrayList<Theatre_Details> allTheatreDetailsList = service.getAllTheatreNames();
						session.setAttribute("allTheatreDetailsList", allTheatreDetailsList);
						view = getServletContext().getRequestDispatcher(
								"/pages/showAllTheatreDetails.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				//action to view all assignment details
				if (action != null && action.equalsIgnoreCase("View All")) {
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					//session.setAttribute("theatreName", theatreName);
					try {
						ArrayList<Movie_Assignment> allAssignmentDetailsList = service.getAllAssignmentDetails();
						session.setAttribute("allAssignmentDetailsList", allAssignmentDetailsList);
						view = getServletContext().getRequestDispatcher(
								"/pages/showAllAssignmentDetails.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				
				// action to delete movie from list
				if (action != null && action.equalsIgnoreCase("deleteMovie")) {
					String movieName = request.getParameter("movieName");
					System.out.println("Delete" + movieName);
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					session.setAttribute("movieName", movieName);
					try {Movie_Details movie_Details = new Movie_Details();
						String deleteStatus = service.deleteMovie(movie_Details, movieName);
						ArrayList<Movie_Details> movieDetailsList = service.searchMovieDetails(movieName);
						session.setAttribute("movieDetailsList", movieDetailsList);
						view = getServletContext().getRequestDispatcher(
								"/pages/showMovieDetails.jsp");
						view.forward(request, response);
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
		
		// action to search theaters which play selected movie
		/*if (action != null && action.equalsIgnoreCase("Search")) {
			//String movieName = request.getParameter("movieName");
			// if an existing session is not there, then creating a new one.
			if (session == null) {
				session = request.getSession(true);
			}
			//session.setAttribute("movieName", movieName);
			try {
				ArrayList<Movie_Details> theaterList = service.searchMovie(movieName);
				session.setAttribute("theaterList", theaterList);
				view = getServletContext().getRequestDispatcher(
						"/pages/showTheaters.jsp");
				view.forward(request, response);
			} catch (MovieException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		*/
		
		// action to allow the user to avail booking for a particular movie 
		/*if (action != null && action.equalsIgnoreCase("Book")) {
			if (session != null) {
				int theaterId = Integer.parseInt(request
						.getParameter("theaterId"));
				ArrayList<Theater> theaterList = 
						(ArrayList<Theater>) session
						.getAttribute("theaterList");
				for (Theater theaterDetails : theaterList) {
					if (theaterId == theaterDetails.getTheaterId()) {
						session.setAttribute("theaterDetails", theaterDetails);
					}
				}
				view = getServletContext().getRequestDispatcher(
						"/pages/bookNow.jsp");
				view.forward(request, response);
			} else {
				// dispatching control to error page if session is null
				request.setAttribute("errMsg", "Invalid Request");
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}*/
		// action to confirm movie booking
		/*if (action != null && action.equalsIgnoreCase("ConfirmBook")) {
			if (session != null) {
				int quantity = Integer.parseInt(request
						.getParameter("quantity"));
				Theater theater = (Theater) session
						.getAttribute("theaterDetails");
				Booking booking = new Booking();
				booking.setTheaterName(theater.getTheaterName());
				booking.setNoOfTickets(quantity);
				float cost = (float) (theater.getTicketPrice() * quantity);
				float taxFees = (float) (cost * 10 / 100);
				float finalPrice = cost + taxFees;
				booking.setTotalPrice(finalPrice);
				booking.setMoviePlayTime(theater.getMoviePlayTime());
				LocalDate date = LocalDate.now();
				//booking.setBookingDate(new java.sql.Date(date));
				session.setAttribute("bookingDetails", booking);
				try {
					int bookingId = service.makeBooking(booking,
							theater.getTheaterId());
					if (bookingId != 0) {
						session.setAttribute("bookingId", bookingId);
						view = getServletContext().getRequestDispatcher(
								"/pages/success.jsp");
						view.forward(request, response);
					} else {
						request.setAttribute("errMsg",
								"Movie Booking not done.");
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				} catch (MovieException e) {
					request.setAttribute("errMsg", e.getMessage());
					view = getServletContext().getRequestDispatcher(
							"/pages/error.jsp");
					view.forward(request, response);
				}
			} else {
				request.setAttribute("errMsg", "Invalid Request");
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}*/
		// action to logout and invalidate the session
		if (action != null && action.equalsIgnoreCase("Logout")) {
			if (session != null) {
				session.invalidate();
				view = getServletContext().getRequestDispatcher("/index.jsp");
				view.forward(request, response);
			} else {
				request.setAttribute("errMsg", "Invalid Request");
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
	}

}
