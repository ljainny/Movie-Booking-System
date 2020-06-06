package com.igate.moviebooking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.MovieBooking;
import com.igate.moviebooking.dto.Movie_Assignment;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.dto.UserDetails;
import com.igate.moviebooking.dto.UserLogin;
import com.igate.moviebooking.exception.MovieException;
import com.igate.moviebooking.service.IMovieService;
import com.igate.moviebooking.service.MovieServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		RequestDispatcher view = null;
		IMovieService service = new MovieServiceImpl();
		// Initial setting the session parameter to false
		HttpSession session = request.getSession(false);
		//session.setAttribute("id", arg1);
		
		//action for User Login page
		if (action != null && action.equalsIgnoreCase("userLogin")) {
			try {
				//ArrayList<String> movieNames = service.getMovieNames();
				//request.setAttribute("movieNames", movieNames);
				view = getServletContext().getRequestDispatcher(
						"/pages/newUserLogin.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		
		// action for User Validation
		if (action != null && action.equalsIgnoreCase("Submit")) {
			try {
				String id = request.getParameter("uName");
				String pswd = request.getParameter("uPassword");
				System.out.println(id +"id"+pswd+"pswd");
				UserDetails userDetails = new UserDetails();
				userDetails.setuName(id);
				userDetails.setuPassword(pswd);
				boolean loginflag = service.validateUserLogin(userDetails);
				System.out.println("loginflag "+ loginflag);
				request.setAttribute("id", id);
				session.setAttribute("id", id);
				if(loginflag == true){
					view = getServletContext().getRequestDispatcher(
							"/pages/userLoginSuccess.jsp");
					/*view = getServletContext().getRequestDispatcher(
							"/pages/UserLogin.html");*/
					view.forward(request, response);
				}
				else{
					//out.println("Invalid Id or Password");
					view = getServletContext().getRequestDispatcher(
							"/pages/newUserLogin.jsp");
					response.setContentType("text/html");
			        PrintWriter out = response.getWriter();
			        out.print("<h2>Invalid Password</h2>");
					view.include(request, response);
				}
				
			} catch (Exception e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		
		//action for User registration Page
		if (action != null && action.equalsIgnoreCase("userRegistration")) {
			try {
				view = getServletContext().getRequestDispatcher(
						"/pages/newUserRegistration.jsp");
				view.forward(request, response);
			} catch (Exception e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		
		//action to insert user deatils into database
		if (action != null && action.equalsIgnoreCase("Sign Up")) {
			try {
				String name = request.getParameter("uname");
				String pswd = request.getParameter("upwd"); 
				String gender = request.getParameter("gender");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				int age = Integer.parseInt(request.getParameter("age"));
				
				UserDetails userDetails = new UserDetails();
				userDetails.setuName(name);
				userDetails.setuPassword(pswd);
				userDetails.setGender(gender);
				userDetails.setPhone(phone);
				userDetails.setEmailid(email);
				userDetails.setAddress(address);
				userDetails.setAge(age);
				
				boolean staus = service.addUserDetails(userDetails);
				view = getServletContext().getRequestDispatcher(
						"/pages/newUserLogin.jsp");
				response.setContentType("text/html");
		        PrintWriter out = response.getWriter();
		        out.print("<h2>Log In to Book</h2>");
				view.include(request, response);
				
			} catch (Exception e) {
				// dispatching control to error page
				request.setAttribute("errMsg", e.getMessage());
				view = getServletContext().getRequestDispatcher(
						"/pages/error.jsp");
				view.forward(request, response);
			}
		}
		
		//action to view change password page
				if (action != null && action.equalsIgnoreCase("changePassword")) {
					try {
						//String id =request.getParameter("id");
						String id2 =(String) session.getAttribute("id");
						//System.out.println("luckyid" +id);
						//String id1 = id.replace("/", "");
						//request.setAttribute("id",id1);
						//System.out.println("luckyid2" +id1);
						System.out.println("sessionid"+id2);

						view = getServletContext().getRequestDispatcher(
								"/pages/viewChangePassword.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				//action to view change password page
				if (action != null && action.equalsIgnoreCase("Change")) {
					try {
						String id = request.getParameter("id");
						String oldPwd = request.getParameter("oldPwd");
						String newPwd = request.getParameter("newPwd"); 
						String confirmPwd = request.getParameter("confirmPwd");
						System.out.println("oldPwd"+oldPwd+newPwd+confirmPwd);
						request.setAttribute("id", id);
						if(newPwd.equals(confirmPwd)){
							boolean status = service.changePassword(id, oldPwd, newPwd);
							System.out.println("statusstatus"+status);
							if(status == true){
								response.setContentType("text/html");
						        PrintWriter out = response.getWriter();
						        out.print("<<marquee>>Password changed successfuly</<marquee>>");
								view = getServletContext().getRequestDispatcher(
										"/pages/userLoginSuccess.jsp");
								view.forward(request, response);
							}
							else{
								
								response.setContentType("text/html");
						        PrintWriter out = response.getWriter();
						        out.print("<h2>New Password and Confirm Password didn't match OR Incorrect password</h2>");
								view = getServletContext().getRequestDispatcher(
										"/pages/viewChangePassword.jsp");
								view.forward(request, response);
							}
						}
						else{
							
							response.setContentType("text/html");
					        PrintWriter out = response.getWriter();
					        out.print("<h2>New Password and Confirm Password didn't match OR Incorrect password</h2>");
							view = getServletContext().getRequestDispatcher(
									"/pages/viewChangePassword.jsp");
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
		
				//action for User registration Page
				if (action != null && action.equalsIgnoreCase("bookTicket")) {
					try {
						ArrayList<String> theatreNames = service.getAssignedTheatreNames();
						ArrayList<String> movieNames = service.getAssignedtMovieNames();
						request.setAttribute("theatreNames", theatreNames);
						request.setAttribute("movieNames", movieNames);
						view = getServletContext().getRequestDispatcher(
								"/pages/bookTicket.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				
				
				// action to search theaters which play selected movie
				if (action != null && action.equalsIgnoreCase("SearchTheatreForMovie")) {
					String movieName = request.getParameter("movieName");
					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					session.setAttribute("movieName", movieName);
					try {
						ArrayList<Theater> theaterList = service.searchMovie(movieName);
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
				
				
				if (action != null && action.equalsIgnoreCase("Book")) {
					String movieName = request.getParameter("movieName");
					String assignedDate = request.getParameter("assignedDate");
					//System.out.println("theatreName+++"+theatreName.isEmpty()+movieName.isEmpty()+assignedDate.isEmpty());

					// if an existing session is not there, then creating a new one.
					if (session == null) {
						session = request.getSession(true);
					}
					//session.setAttribute("theatreName", theatreName);
					session.setAttribute("assignedDate", assignedDate);
					session.setAttribute("movieName", movieName);

					try {
						ArrayList<MovieBooking> movieBookingList = service.getTheatreForBooking(movieName,assignedDate);
						if(movieBookingList.isEmpty()){
							System.out.println("Theatres not available");
							view = getServletContext().getRequestDispatcher(
									"/pages/showTheatreForBookingFailed.jsp");
							view.forward(request, response);
						}
						else{
							session.setAttribute("movieBookingList", movieBookingList);
							view = getServletContext().getRequestDispatcher(
									"/pages/showTheatreForBooking.jsp");
							view.forward(request, response);
						}
						
					} catch (MovieException e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && action.equalsIgnoreCase("ConfirmBooking")) {
					String theatreName = request.getParameter("theaterName");
					String movieName = request.getParameter("movieName");
					String assignedDate = request.getParameter("assignedDate");
					String ticketCost = request.getParameter("ticketCost");
					String totalNoOfTickets = request.getParameter("totalNoOfTickets");
System.out.println("theatreName////"+theatreName+movieName+assignedDate+ticketCost+totalNoOfTickets);

					// if an existing session is not there, then creating a new one.
					try {
						if (session == null) {
							session = request.getSession(true);
						}
						session.setAttribute("theatreName", theatreName);
						session.setAttribute("movieName", movieName);
						session.setAttribute("assignedDate", assignedDate);
						session.setAttribute("ticketCost", ticketCost);
						session.setAttribute("totalNoOfTickets", totalNoOfTickets);
						//ArrayList<MovieBooking> movieBookingList = service.getTheatreForBooking(movieName,assignedDate);
						//session.setAttribute("movieBookingList", movieBookingList);
						view = getServletContext().getRequestDispatcher(
								"/pages/confirmBooking.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
		
				//action for final booking
				if (action != null && action.equalsIgnoreCase("FinalBooking")) {
					if (session != null) {
						String customerName = (String) session.getAttribute("id");
						int quantity = Integer.parseInt(request
								.getParameter("quantity"));
						String movieName = request.getParameter("movieName");
						String theatreName = request.getParameter("theatreName");
						String assignedDate = request.getParameter("assignedDate");
						Double ticketCost = Double.parseDouble(request.getParameter("ticketCost"));
						int totalNoOfTickets = (Integer.parseInt((request.getParameter("totalNoOfTickets"))) - quantity);
						System.out.println("quantity" +quantity+movieName+theatreName+assignedDate+ticketCost+totalNoOfTickets);

						/*Theater theater = (Theater) session
								.getAttribute("theaterDetails");*/
						MovieBooking moviebooking = new MovieBooking();
						moviebooking.setTheaterName(theatreName);
						moviebooking.setMovieName(movieName);	
						moviebooking.setAssignedDate(assignedDate);
						moviebooking.setTicketCost(ticketCost);
						moviebooking.setTotalNoOfTickets(totalNoOfTickets);
						moviebooking.setNoOfTickets(quantity);
						moviebooking.setCustomerName(customerName);
						
						float cost = (float) (ticketCost * quantity);
						float taxFees = (float) (cost * 10 / 100);
						float finalPrice = cost + taxFees;
						moviebooking.setTotalPrice(finalPrice);
						Date date1 = Date.valueOf(LocalDate.now());
						moviebooking.setBookingDate(date1);
						
						try {
							int bookingid = service.makeBooking(moviebooking);
							System.out.println("bookingidbookingid "+bookingid);
							if (bookingid != 0) {
								session.setAttribute("bookingId", bookingid);
								session.setAttribute("moviebooking", moviebooking);
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						request.setAttribute("errMsg", "Invalid Request");
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
		
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
		
				
		
		
				//action to view Search Booking Page
				if (action != null && action.equalsIgnoreCase("viewTicketPage")) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						view = getServletContext().getRequestDispatcher(
								"/pages/searchbooking.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				//action to view Search Booking Page
				if (action != null && action.equalsIgnoreCase("Search Booking")) {
					try {
						int bookingId= Integer.parseInt(request
								.getParameter("bookingId"));
						ArrayList<MovieBooking> moviebookingList = service.getBookingDetails(bookingId);
						if(moviebookingList.isEmpty()){
							view = getServletContext().getRequestDispatcher(
									"/pages/showBookingDetailsFailed.jsp");
							view.forward(request, response);
						}
						else{
							request.setAttribute("moviebookingList", moviebookingList);
							view = getServletContext().getRequestDispatcher(
									"/pages/showBookingDetails.jsp");
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
		
				if (action != null && action.equalsIgnoreCase("home")) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						view = getServletContext().getRequestDispatcher(
								"/pages/userLoginSuccess.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && action.equalsIgnoreCase("contactUs")) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						view = getServletContext().getRequestDispatcher(
								"/pages/userLoginSuccess.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
				
				if (action != null && action.equalsIgnoreCase("about")) {
					try {
						//ArrayList<String> movieNames = service.getMovieNames();
						//request.setAttribute("movieNames", movieNames);
						view = getServletContext().getRequestDispatcher(
								"/pages/userLoginSuccess.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						// dispatching control to error page
						request.setAttribute("errMsg", e.getMessage());
						view = getServletContext().getRequestDispatcher(
								"/pages/error.jsp");
						view.forward(request, response);
					}
				}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
