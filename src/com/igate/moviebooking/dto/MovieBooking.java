package com.igate.moviebooking.dto;

import java.sql.Date;

public class MovieBooking {
	private int bookingId;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	private String theaterName;
	private String theaterAddress;
	private String movieName;
	private int noOfTickets;
	private int totalNoOfTickets;
	private double ticketCost;
	private float totalPrice;
	private Date bookingDate;
	private String assignedDate;
	private String customerName;
	
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public int getTotalNoOfTickets() {
		return totalNoOfTickets;
	}
	public void setTotalNoOfTickets(int toalNoOfTickets) {
		this.totalNoOfTickets = toalNoOfTickets;
	}
	public double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTheaterAddress() {
		return theaterAddress;
	}
	public void setTheaterAddress(String theaterAddress) {
		this.theaterAddress = theaterAddress;
	}
	@Override
	public String toString() {
		return "MovieBooking [theaterName=" + theaterName + ", movieName=" + movieName + ", noOfTickets=" + noOfTickets
				+ ", toalNoOfTickets=" + totalNoOfTickets + ", ticketCost=" + ticketCost + ", totalPrice=" + totalPrice
				+ ", bookingDate=" + bookingDate + ", assignedDate=" + assignedDate + "]";
	}

	

}
