package com.devsuperior.movie.dto;


import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.entities.Review;
import com.devsuperior.movie.entities.User;

public class ReviewDTO {

	private Long id;
	private String text;
	private UserDTO user;
	private MovieDTO movie;

	public ReviewDTO(Long id, String text) {
		this.id = id;
		this.text = text;
	}

	public ReviewDTO(Review entity, User user, Movie movie ) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.user = new UserDTO(user);
		this.movie = new MovieDTO(movie);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	
	
}
