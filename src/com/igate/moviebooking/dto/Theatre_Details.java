package com.igate.moviebooking.dto;

public class Theatre_Details {
	private int theatreId;
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	private String theatreName;
	private String theatreAddress;
	private int theatresize;
	private int ticketCost;
	private String theatreManager;
	
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTheatreAddress() {
		return theatreAddress;
	}
	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}
	public int getTheatresize() {
		return theatresize;
	}
	public void setTheatresize(int theatresize) {
		this.theatresize = theatresize;
	}
	public int getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(int ticketCost) {
		this.ticketCost = ticketCost;
	}
	public String getTheatreManager() {
		return theatreManager;
	}
	public void setTheatreManager(String theatreManager) {
		this.theatreManager = theatreManager;
	}
	public int getTheatreId() {
		return theatreId;
	}
}
