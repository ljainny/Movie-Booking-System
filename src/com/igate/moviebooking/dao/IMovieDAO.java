package com.igate.moviebooking.dao;

import java.util.ArrayList;

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
 * DAO Interface , declaring method signatures to be implemented in DAO Impl class
 */
public interface IMovieDAO {
	
public boolean validateUserLogin(UserDetails userDetails) throws MovieException;
	
	public boolean validateAdminLogin(AdminLogin adminlogin) throws MovieException;

	public int addMovie(Movie_Details movie_Details, int mId) throws MovieException;
	public String deleteMovie(Movie_Details movie_Details, String movieName) throws MovieException;
	public int assignedDate(Movie_Assignment movieAssignment) throws MovieException;
	public int addTheatre(Theatre_Details theatreDetails,int tId) throws MovieException;
	public ArrayList<String> getTheatreNames() throws MovieException;
	public ArrayList<String> getAssignedTheatreNames() throws MovieException;
	public ArrayList<String> getAssignedtMovieNames() throws MovieException;
	public ArrayList<Movie_Assignment> getAllAssignmentDetails() throws MovieException;


	public ArrayList<Theatre_Details> getAllTheatreNames() throws MovieException;

	public ArrayList<String> getMovieList() throws MovieException;
	public ArrayList<Movie_Details> searchMovieDetails(String movieName)
			throws MovieException;
	public ArrayList<Theatre_Details> searchTheatreDetails(String theatreName)
			throws MovieException;
	public ArrayList<Movie_Assignment> searchMovieAssignmentDetails(String theatreName, String movieName)
			throws MovieException;
	public int validateTheatreName(String tname) throws MovieException;
	public boolean updateTheatre(Theatre_Details  theatreDetails) throws MovieException;
	public ArrayList<String> getTheatreList() throws MovieException;
	public ArrayList<String> getMovieNames() throws MovieException;
	
	public boolean addUserDetails(UserDetails userDetails) throws MovieException;
	public boolean changePassword(String s1 ,String s2,String s3) throws MovieException;
	public ArrayList<MovieBooking> getTheatreForBooking(String moviename, String assignedDate) throws MovieException;
	public int makeBooking(MovieBooking moviebooking)
			throws MovieException;
	public ArrayList<MovieBooking> getBookingDetails(int bookingId) throws MovieException;

	
	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException;
	/*public int makeBooking(Booking booking, int theaterId)
			throws MovieException;*/
}
