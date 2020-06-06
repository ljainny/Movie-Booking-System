package com.igate.moviebooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.igate.moviebooking.util.DBUtil;

/*
 * DAO class for database interaction
 */
public class MovieDAOImpl implements IMovieDAO {
	Connection connection = null;
	Statement statement = null;
	ResultSet rsSet = null;
	PreparedStatement preparedStatement = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.igate.moviebooking.dao.IMovieDAO#getMovieNames() Method to fetch
	 * the movie names from database returning an arraylist of type String with
	 * movie names
	 */
	
	@Override
	public boolean validateUserLogin(UserDetails userDetails) throws MovieException {
		boolean status = false;
		System.out.println(userDetails.getuName());
		System.out.println(userDetails.getuPassword());

		connection = DBUtil.obtainConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from UserDetails where uName=? and uPassword=?");
			preparedStatement.setString(1, userDetails.getuName());
			preparedStatement.setString(2, userDetails.getuPassword());
			ResultSet rsSet = preparedStatement.executeQuery();  
			status = rsSet.next();
			System.out.println("status" + status);
		} catch (SQLException e) {
			System.out.println("Exception occured" + e);
		}
		
		return status;
	}

	@Override
	public boolean validateAdminLogin(AdminLogin adminlogin) throws MovieException {
		boolean status = false;
		System.out.println(adminlogin.getaName());
		System.out.println(adminlogin.getaPassword());

		connection = DBUtil.obtainConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from AdminLogin where aName=? and aPassword=?");
			preparedStatement.setString(1, adminlogin.getaName());
			preparedStatement.setString(2, adminlogin.getaPassword());
			ResultSet rsSet = preparedStatement.executeQuery();  
			status = rsSet.next();
			System.out.println("status" + status);
		} catch (SQLException e) {
			System.out.println("Exception occured" + e);
		}
		
		return status;
	}
	
	@Override
	public int addMovie(Movie_Details movie_Details, int mId) throws MovieException {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.obtainConnection();
		String sequenceSql = "SELECT mid_seq.nextval FROM DUAL";
		String insertSql = "INSERT INTO MovieDetails VALUES(?,?,?,?,?,?)";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				mId = rsSet.getInt(1);
			}
			PreparedStatement pstmt = connection.prepareStatement(insertSql);
			pstmt.setString(1, String.valueOf(mId));
			pstmt.setString(2, movie_Details.getTitle());
			pstmt.setString(3, movie_Details.getDirector());
			pstmt.setString(4, movie_Details.getCast());
			pstmt.setString(5, movie_Details.getDescription());
			pstmt.setString(6, movie_Details.getStatus());
			int insertRows = pstmt.executeUpdate();
			if(insertRows !=0){
				System.out.println("Inserted movie");
			}
			else{
				System.out.println("Unable to insert");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return mId;
	}
	
	@Override
	public int addTheatre(Theatre_Details theatreDetails, int tId) throws MovieException {
		Connection connection = DBUtil.obtainConnection();
		String sequenceSql = "SELECT tid_seq.nextval FROM DUAL";
		String insertSql = "INSERT INTO TheatreDetails VALUES(?,?,?,?,?,?)";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				tId = rsSet.getInt(1);
			}
			PreparedStatement pstmt = connection.prepareStatement(insertSql);
			pstmt.setString(1, String.valueOf(tId));
			pstmt.setString(2, theatreDetails.getTheatreName());
			pstmt.setString(3, theatreDetails.getTheatreAddress());
			pstmt.setInt(4, theatreDetails.getTheatresize());
			pstmt.setInt(5, theatreDetails.getTicketCost());
			pstmt.setString(6, theatreDetails.getTheatreManager());
			int insertRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return tId ;
	}
	public ArrayList<String> getMovieNames() throws MovieException {
		ArrayList<String> movieNames = new ArrayList<String>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT title FROM moviedetails ";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				movieNames.add(rsSet.getString("title"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return movieNames;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.igate.moviebooking.dao.IMovieDAO#searchMovie(java.lang.String)
	 * Method to fetch the theater details playing a particular type of movie,
	 * returning an arraylist of type theaters with relevant theater details,
	 * ticket price and number of tickets available
	 */
	@Override
	public ArrayList<Theater> searchMovie(String movieName)
			throws MovieException {
		ArrayList<Theater> theaterList = new ArrayList<Theater>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT theaterId,theaterName,AvailableTickets,moviePlayTime,price "
				+ "FROM movies m,theaters t WHERE m.movieId=t.movieId AND movie_Name=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, movieName);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				Theater theater = new Theater();
				theater.setTheaterId(rsSet.getInt("THEATERID"));
				theater.setTheaterName(rsSet.getString("THEATERNAME"));
				theater.setAvailableTickets(rsSet.getInt("AVAILABLETICKETS"));
				theater.setMoviePlayTime(rsSet.getTimestamp("MOVIEPLAYTIME"));
				theater.setTicketPrice(rsSet.getDouble("PRICE"));

				theaterList.add(theater);
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}

		return theaterList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.igate.moviebooking.dao.IMovieDAO#makeBooking(com.igate.moviebooking
	 * .dto.Booking, int) 
	 * Method to confirm movie booking. Inserting in ticket details in Booking table and 
	 * updating the number of tickets in the theater table. Returing int as Booking id
	 */
	/*public int makeBooking(Booking booking, int theaterId)
			throws MovieException {
		int bookingId = 0;
		Connection connection = DBUtil.obtainConnection();
		String sequenceSql = "SELECT bookSeq.nextval FROM DUAL";
		String insertSql = "INSERT INTO moviebooking VALUES(?,?,?,?,?,?)";
		String updateSql = "UPDATE theaters SET availableTickets=availableTickets-? WHERE theaterId=?";
		PreparedStatement pstmt = null;
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				bookingId = rsSet.getInt(1);
			}

			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, bookingId);
			preparedStatement.setString(2, booking.getTheaterName());
			preparedStatement.setInt(3, booking.getNoOfTickets());
			preparedStatement.setFloat(4, booking.getTotalPrice());
			preparedStatement.setTimestamp(5, booking.getMoviePlayTime());
			preparedStatement.setDate(6, booking.getBookingDate());

			int insertRows = preparedStatement.executeUpdate();

			if (insertRows != 0) {
				pstmt = connection.prepareStatement(updateSql);
				pstmt.setInt(1, booking.getNoOfTickets());
				pstmt.setInt(2, theaterId);

				int updateRows = pstmt.executeUpdate();

				if (updateRows != 0) {
					return bookingId;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return bookingId;
	}*/
	
	public int makeBooking(MovieBooking moviebooking)
			throws MovieException {
		int bookingId = 0;
		Connection connection = DBUtil.obtainConnection();
		int availablTickets = moviebooking.getTotalNoOfTickets();
		String theatrename= moviebooking.getTheaterName();
		String sequenceSql = "SELECT bookingid_seq.nextval FROM DUAL";
		String insertSql = "INSERT INTO moviebooking VALUES(?,?,?,?,?,?,?,?,?)";
		//String updateSql = "UPDATE theatredetails SET tsize='"+availablTickets+"' WHERE tname='"+theatrename+"'");
		//PreparedStatement pstmt = null;
		try {
			statement = connection.createStatement();
			int updateRows = statement.executeUpdate("UPDATE theatredetails SET tsize='"+availablTickets+"' WHERE tname='"+theatrename+"'");
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				bookingId = rsSet.getInt(1);
			}

			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, bookingId);
			preparedStatement.setString(2, moviebooking.getTheaterName());
			preparedStatement.setString(3, moviebooking.getMovieName());
			preparedStatement.setInt(4, moviebooking.getNoOfTickets());
			preparedStatement.setDouble(5, moviebooking.getTicketCost());
			preparedStatement.setDouble(6, moviebooking.getTotalPrice());
			preparedStatement.setDate(7, moviebooking.getBookingDate());
			preparedStatement.setString(8, moviebooking.getAssignedDate());
			preparedStatement.setString(9, moviebooking.getCustomerName());



			int insertRows = preparedStatement.executeUpdate();

			if (insertRows != 0) {
				/*pstmt = connection.prepareStatement(updateSql);
				pstmt.setInt(1, booking.getNoOfTickets());
				pstmt.setInt(2, theaterId);*/

				//int updateRows = pstmt.executeUpdate();

				if (updateRows != 0) {
					return bookingId;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return bookingId;
	}

	@Override
	public ArrayList<String> getMovieList() throws MovieException {
		
		ArrayList<String> moviedetailsList = new ArrayList<>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT TITLE FROM MOVIEDETAILS WHERE ACTIVE='y'";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				moviedetailsList.add(rsSet.getString("TITLE"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return moviedetailsList;
	}
	
	@Override
	public ArrayList<String> getTheatreList() throws MovieException {
		
		ArrayList<String> theatredetailsList = new ArrayList<>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT TNAME FROM THEATREDETAILS";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				theatredetailsList.add(rsSet.getString("TNAME"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction1123:::"
					+ e.getMessage());
		}
		return theatredetailsList;
	}

	@Override
	public int assignedDate(Movie_Assignment movieAssignment) throws MovieException {
		int assignedid = 0;
		Connection connection = DBUtil.obtainConnection();
		String sequenceSql = "SELECT aid_seq.nextval FROM DUAL";
		String insertSql = "INSERT INTO MOVIEASSIGNMENT VALUES(?,?,?,?)";
		//String updateSql = "UPDATE theaters SET availableTickets=availableTickets-? WHERE theaterId=?";
		PreparedStatement pstmt = null;
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				assignedid = rsSet.getInt(1);
			}
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, assignedid);
			preparedStatement.setString(2, movieAssignment.getMoviename());
			preparedStatement.setString(3, movieAssignment.getTheatrename());
			preparedStatement.setString(4, movieAssignment.getAssignedDate());
			int insertRows = preparedStatement.executeUpdate();
		}
			catch (SQLException e) {
				throw new MovieException("Error while database interaction:::"
						+ e.getMessage());
			}
		return assignedid;
	
}

	@Override
	public ArrayList<Movie_Details> searchMovieDetails(String movieName) throws MovieException {
		ArrayList<Movie_Details> movieDetailsList = new ArrayList<Movie_Details>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT MID,TITLE,DIRECTOR,CAST,DESCRIPTION,ACTIVE "
				+ "FROM moviedetails WHERE TITLE=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, movieName);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				Movie_Details movie_details = new Movie_Details();
				movie_details.setMovieId(rsSet.getInt("MID"));
				movie_details.setTitle(rsSet.getString("TITLE"));
				movie_details.setDirector(rsSet.getString("DIRECTOR"));
				movie_details.setCast(rsSet.getString("CAST"));
				movie_details.setDescription(rsSet.getString("DESCRIPTION"));
				movie_details.setStatus(rsSet.getString("ACTIVE"));
				movieDetailsList.add(movie_details);
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return movieDetailsList;
	}

	@Override
	public String deleteMovie(Movie_Details movie_Details, String movieName) throws MovieException {
		Connection connection = DBUtil.obtainConnection();
		int status1=0;
		String sql = "delete from moviedetails where title=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, movieName);
			status1=preparedStatement.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return "success";
	}

	@Override
	public ArrayList<String> getTheatreNames() throws MovieException {
		ArrayList<String> theatreNames = new ArrayList<String>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT tname FROM theatredetails";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				theatreNames.add(rsSet.getString("tname"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return theatreNames;
	}

	@Override
	public ArrayList<Theatre_Details> searchTheatreDetails(String theatreName) throws MovieException {
		ArrayList<Theatre_Details> theatreDetailsList = new ArrayList<Theatre_Details>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT TID,TNAME,TADDRESS,TSIZE,TCOST,TMANAGER "
				+ "FROM theatredetails WHERE TNAME=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, theatreName);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				
				Theatre_Details theatre_details = new Theatre_Details();
				theatre_details.setTheatreId(rsSet.getInt("TID"));
				theatre_details.setTheatreName(rsSet.getString("TNAME"));
				theatre_details.setTheatreAddress(rsSet.getString("TADDRESS"));
				theatre_details.setTheatresize(rsSet.getInt("TSIZE"));
				theatre_details.setTicketCost(rsSet.getInt("TCOST"));
				theatre_details.setTheatreManager(rsSet.getString("TMANAGER"));
				theatreDetailsList.add(theatre_details);
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return theatreDetailsList;
	}

	@Override
	public ArrayList<Theatre_Details> getAllTheatreNames() throws MovieException {
		// TODO Auto-generated method stub
		 ArrayList<Theatre_Details> theatre_details = new ArrayList<Theatre_Details>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT * FROM theatredetails";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				Theatre_Details theatre_detail = new Theatre_Details();
				theatre_detail.setTheatreId(rsSet.getInt("TID"));
				theatre_detail.setTheatreName(rsSet.getString("TNAME"));
				theatre_detail.setTheatreAddress(rsSet.getString("TADDRESS"));
				theatre_detail.setTheatresize(rsSet.getInt("TSIZE"));
				theatre_detail.setTicketCost(rsSet.getInt("TCOST"));
				theatre_detail.setTheatreManager(rsSet.getString("TMANAGER"));
				theatre_details.add(theatre_detail);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theatre_details;
	}

	@Override
	public int validateTheatreName(String tname) throws MovieException {
		int theatre_id=0;
		Connection connection = DBUtil.obtainConnection();
		//String sql = "SELECT TID FROM theatredetails WHERE TNAME='?'";
		try {
			preparedStatement = connection.prepareStatement("SELECT TID FROM theatredetails WHERE TNAME =" + "'" + tname + "'");
			//preparedStatement.setString(1, tname);
			rsSet = preparedStatement.executeQuery();
			System.out.println("rsSetrsSet"+rsSet);
			if (rsSet.next() == false) {
			      System.out.println("ResultSet in empty in Java");
			    }
			else{
				while (rsSet.next()) {
					
					Theatre_Details theatre_details = new Theatre_Details();
					theatre_details.setTheatreId(rsSet.getInt("TID"));
					/*theatre_details.setTheatreName(rsSet.getString("TNAME"));
					theatre_details.setTheatreAddress(rsSet.getString("TADDRESS"));
					theatre_details.setTheatresize(rsSet.getInt("TSIZE"));
					theatre_details.setTicketCost(rsSet.getInt("TCOST"));
					theatre_details.setTheatreManager(rsSet.getString("TMANAGER"));*/
					theatre_id = theatre_details.getTheatreId();
					System.out.println(theatre_id);
				}
			}
			
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return theatre_id;
	}

	@Override
	public boolean updateTheatre( Theatre_Details theatreDetails) throws MovieException {
		int theatre_id=0;
		Connection connection = DBUtil.obtainConnection();
		//String sql = "SELECT TID FROM theatredetails WHERE TNAME='?'";
		try {
			preparedStatement = connection.prepareStatement("UPDATE theatredetails SET TADDRESS = ?, TSIZE = ?, TCOST = ?, TMANAGER = ? WHERE TNAME = ?");
			preparedStatement.setString(1, theatreDetails.getTheatreAddress());
			preparedStatement.setInt(2, theatreDetails.getTheatresize());
			preparedStatement.setInt(3, theatreDetails.getTicketCost());
			preparedStatement.setString(4, theatreDetails.getTheatreManager());
			preparedStatement.setString(5, theatreDetails.getTheatreName());
			rsSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return true;
	}

	@Override
	public ArrayList<Movie_Assignment> searchMovieAssignmentDetails(String theatreName, String movieName)
			throws MovieException {
		ArrayList<Movie_Assignment> movieassignmentDetailsList = new ArrayList<Movie_Assignment>();
		Connection connection = DBUtil.obtainConnection();
		//String sql = "SELECT AID,MOVIENAME,THEATRENAME,ASSIGNEDATE FROM theatredetails WHERE THEATRENAME=" + "'" + theatreName + "'" +"AND MOVIENAME=" + "'" + movieName + "'"";
		System.out.println("theatreNamemovieName "+movieName);
		try {
			preparedStatement = connection.prepareStatement("SELECT AID,MOVIENAME,THEATRENAME,ASSIGNEDATE FROM movieassignment WHERE THEATRENAME=" + "'" + theatreName + "'" +"AND MOVIENAME=" + "'" + movieName + "'");
			//preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, theatreName);
			//preparedStatement.setString(2, movieName);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				
				Movie_Assignment movie_Assignment = new Movie_Assignment();
				movie_Assignment.setAssignmentId(rsSet.getInt("AID"));
				movie_Assignment.setMoviename(rsSet.getString("MOVIENAME"));
				movie_Assignment.setTheatrename(rsSet.getString("THEATRENAME"));
				movie_Assignment.setAssignedDate(rsSet.getString("ASSIGNEDATE"));
				movieassignmentDetailsList.add(movie_Assignment);
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return movieassignmentDetailsList;
	}

	@Override
	public ArrayList<String> getAssignedTheatreNames() throws MovieException {
		ArrayList<String> assignedTheatreNames = new ArrayList<String>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT DISTINCT theatrename FROM movieassignment";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				assignedTheatreNames.add(rsSet.getString("theatrename"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return assignedTheatreNames;
	}
	

	@Override
	public ArrayList<String> getAssignedtMovieNames() throws MovieException {
		ArrayList<String> assignedMovieNames = new ArrayList<String>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT DISTINCT moviename FROM movieassignment";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				assignedMovieNames.add(rsSet.getString("moviename"));
			}
		} catch (SQLException e) {
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return assignedMovieNames;
	}

	@Override
	public ArrayList<Movie_Assignment> getAllAssignmentDetails() throws MovieException {
		ArrayList<Movie_Assignment> assignment_details = new ArrayList<Movie_Assignment>();
		Connection connection = DBUtil.obtainConnection();
		String sql = "SELECT * FROM movieassignment";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				Movie_Assignment movie_Assignment = new Movie_Assignment();
				movie_Assignment.setAssignmentId(rsSet.getInt("AID"));
				movie_Assignment.setMoviename(rsSet.getString("MOVIENAME"));
				movie_Assignment.setTheatrename(rsSet.getString("THEATRENAME"));
				movie_Assignment.setAssignedDate(rsSet.getString("ASSIGNEDATE"));
				assignment_details.add(movie_Assignment);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignment_details;
	}

	@Override
	public boolean addUserDetails(UserDetails userDetails) throws MovieException {
		Connection connection = DBUtil.obtainConnection();
		int uId=0;
		String sequenceSql = "SELECT uid_seq.nextval FROM DUAL";
		String insertSql = "INSERT INTO UserDetails VALUES(?,?,?,?,?,?,?,?)";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sequenceSql);
			if (rsSet.next()) {
				uId = rsSet.getInt(1);
			}
			PreparedStatement pstmt = connection.prepareStatement(insertSql);
			pstmt.setString(1, String.valueOf(uId));
			pstmt.setString(2, userDetails.getuName());
			pstmt.setString(3, userDetails.getuPassword());
			pstmt.setString(4, userDetails.getGender());
			pstmt.setString(5, userDetails.getPhone());
			pstmt.setString(6, userDetails.getEmailid());
			pstmt.setString(7, userDetails.getAddress());
			pstmt.setInt(8, userDetails.getAge());

			int insertRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MovieException("Error while database interaction:::"
					+ e.getMessage());
		}
		return true;
	}

	@Override
	public boolean changePassword(String s1, String s2, String s3) throws MovieException {
		Connection connection = DBUtil.obtainConnection();
		System.out.println(s1+s2+s3);
		boolean status =false;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM UserDetails WHERE UNAME=" + "'" + s1 + "'" +"AND UPASSWORD=" + "'" + s2 + "'");
			rsSet = preparedStatement.executeQuery();
			String id = null;
			String pass = null;
			if (!rsSet.next() ) {
			    System.out.println("no data");
			    status=false;
			}
			else{
				do {
					id=rsSet.getString(2);
					pass=rsSet.getString(3);
					} while(rsSet.next());
				System.out.println("idididid"+id+ " "+pass+"status"+status);
			
					if(pass.equals(s2)){
						Statement st1=connection.createStatement();
						int i=st1.executeUpdate("update UserDetails set UPASSWORD='"+s3+"' where UNAME='"+id+"'");
						System.out.println(i);
						status=true;
					}
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("return status"+status);
		return status;
	}

	@Override
	public ArrayList<MovieBooking> getTheatreForBooking(String moviename, String assignedDate) throws MovieException {
		ArrayList<MovieBooking> getTheatreForBooking = new ArrayList<MovieBooking>();
		Connection connection = DBUtil.obtainConnection();
		try {
			preparedStatement = connection.prepareStatement("SELECT * from theatredetails WHERE tname IN(SELECT theatrename FROM movieassignment WHERE moviename=" + "'" + moviename + "'"  +"AND assignedate=" + "'" + assignedDate + "')");
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				MovieBooking mb = new MovieBooking();
				//mb.setMovieName(rsSet.getString("MOVIENAME"));
				mb.setTheaterName(rsSet.getString("TNAME"));
				mb.setTheaterAddress(rsSet.getString("TADDRESS"));
				//mb.setAssignedDate(rsSet.getString("ASSIGNEDATE"));
				mb.setTotalNoOfTickets(rsSet.getInt("TSIZE"));
				mb.setTicketCost(rsSet.getDouble("TCOST"));
				getTheatreForBooking.add(mb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getTheatreForBooking;
	}

	@Override
	public ArrayList<MovieBooking> getBookingDetails(int bookingId) throws MovieException {
		ArrayList<MovieBooking> getBookingDetails = new ArrayList<MovieBooking>();
		Connection connection = DBUtil.obtainConnection();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM moviebooking WHERE bid=" + "'" + bookingId + "'");
			rsSet = preparedStatement.executeQuery();
			while (rsSet.next()) {
				MovieBooking moviebooking = new MovieBooking();
				moviebooking.setBookingId(rsSet.getInt("BID"));
				moviebooking.setMovieName(rsSet.getString("MOVIENAME"));
				moviebooking.setTheaterName(rsSet.getString("THEATERNAME"));
				moviebooking.setNoOfTickets(rsSet.getInt("NOOFTICKETS"));
				moviebooking.setTicketCost(rsSet.getDouble("TICKETCOST"));
				moviebooking.setTotalPrice(rsSet.getFloat("TOTALPRICE"));
				moviebooking.setBookingDate(rsSet.getDate("BOOKINGDATE"));
				moviebooking.setAssignedDate(rsSet.getString("ASSIGNEDDATE"));
				getBookingDetails.add(moviebooking);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getBookingDetails;
	}

	
}
