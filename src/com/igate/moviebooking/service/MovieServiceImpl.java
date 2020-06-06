package com.igate.moviebooking.service;

import java.util.ArrayList;

import com.igate.moviebooking.dao.IMovieDAO;
import com.igate.moviebooking.dao.MovieDAOImpl;
import com.igate.moviebooking.dto.AdminLogin;
import com.igate.moviebooking.dto.Booking;
import com.igate.moviebooking.dto.MovieBooking;
import com.igate.moviebooking.dto.Movie_Assignment;
import com.igate.moviebooking.dto.Movie_Details;
import com.igate.moviebooking.dto.Theater;
import com.igate.moviebooking.dto.Theatre_Details;
import com.igate.moviebooking.dto.UserDetails;
import com.igate.moviebooking.dto.UserLogin;
import com.igate.moviebooking.exception.MovieException;
/*
 *  MovieServiceImpl class dispatching calls to the Database layer (DAOImpl)
 */
public class MovieServiceImpl implements IMovieService {
	IMovieDAO movieDAO;

	public MovieServiceImpl() {
		movieDAO = new MovieDAOImpl();
	}
	
	@Override
	public boolean validateUserLogin(UserDetails userDetails) throws MovieException {
		boolean status = movieDAO.validateUserLogin(userDetails);
		return status;
	}

	@Override
	public boolean validateAdminLogin(AdminLogin adminlogin) throws MovieException {
		boolean status = movieDAO.validateAdminLogin(adminlogin);
		return status;
	}

	@Override
	public int addMovie(Movie_Details movie_Details, int mId) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.addMovie(movie_Details, mId);
	}
	
	@Override
	public int addTheatre (Theatre_Details theatreDetails,int tId) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.addTheatre(theatreDetails, tId);
	}
	
	
	@Override
	public ArrayList<String> getMovieNames() throws MovieException {
		return movieDAO.getMovieNames();
	}

	@Override
	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException {
		return movieDAO.searchMovie(movieName);
	}
	
	public int makeBooking(MovieBooking moviebooking)
			throws MovieException {
		return movieDAO.makeBooking(moviebooking);
	}

	@Override
	public ArrayList<String> getMovieList() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getMovieList();
	}
	@Override
	public ArrayList<String> getTheatreList() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getTheatreList();
}

	@Override
	public int assignedDate(Movie_Assignment movieAssignment) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.assignedDate(movieAssignment);
	}

	@Override
	public ArrayList<Movie_Details> searchMovieDetails(String movieName) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.searchMovieDetails(movieName);
	}

	@Override
	public String deleteMovie(Movie_Details movie_Details, String movieName) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.deleteMovie(movie_Details, movieName);
	}

	@Override
	public ArrayList<String> getTheatreNames() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getTheatreNames();
	}

	@Override
	public ArrayList<Theatre_Details> searchTheatreDetails(String theatreName) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.searchTheatreDetails(theatreName);
	}

	@Override
	public ArrayList<Theatre_Details> getAllTheatreNames() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getAllTheatreNames();
	}

	@Override
	public int validateTheatreName(String tname) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.validateTheatreName(tname);
	}
	
	public boolean updateTheatre(Theatre_Details  theatreDetails) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.updateTheatre(theatreDetails);
	}

	@Override
	public ArrayList<Movie_Assignment> searchMovieAssignmentDetails(String theatreName, String movieName)
			throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.searchMovieAssignmentDetails(theatreName, movieName);
	}

	@Override
	public ArrayList<String> getAssignedTheatreNames() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getAssignedTheatreNames();
	}

	@Override
	public ArrayList<String> getAssignedtMovieNames() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getAssignedtMovieNames();
	}

	@Override
	public ArrayList<Movie_Assignment> getAllAssignmentDetails() throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getAllAssignmentDetails();
	}

	@Override
	public boolean addUserDetails(UserDetails userDetails) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.addUserDetails(userDetails);
	}

	@Override
	public boolean changePassword(String s1, String s2, String s3) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.changePassword(s1, s2, s3);
	}

	@Override
	public ArrayList<MovieBooking> getTheatreForBooking(String moviename, String assignedDate) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getTheatreForBooking(moviename,assignedDate);
	}

	@Override
	public ArrayList<MovieBooking> getBookingDetails(int bookingId) throws MovieException {
		// TODO Auto-generated method stub
		return movieDAO.getBookingDetails(bookingId);
	}
	}
	
