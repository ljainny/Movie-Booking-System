package com.igate.moviebooking.dto;

import java.util.Date;

public class Movie_Assignment {
	
	private int assignmentId;
	private String assignedDate;
	private String moviename;
	private String theatrename;
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getTheatrename() {
		return theatrename;
	}
	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	@Override
	public String toString() {
		return "Movie_Assignment [assignmentId=" + assignmentId + ", assignedDate=" + assignedDate + "]";
	}
	
	
	
}
