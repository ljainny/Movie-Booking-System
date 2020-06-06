package com.igate.moviebooking.dto;

public class Movie_Details {
	private int movieId;
	private String title;
	private String director;
	private String cast;
	private String description;
	//private Timestamp releasedDate;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*public Timestamp getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(Timestamp releasedDate) {
		this.releasedDate = releasedDate;
	}*/
	public String getTitle() {
		return title;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMovieId() {
		return movieId;
	}
}
