package com.devsuperior.movie.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.devsuperior.movie.entities.Genre;
import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.entities.Review;

public class MovieDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	
	private GenreDTO genre;
	private List<ReviewDTO> reviews = new ArrayList<>();
	
	@Column(columnDefinition = "TEXT")
	private String synopsis;
	
	public MovieDTO() {
	}

	public MovieDTO(Long id) {
		this.id = id;
	}

	
	public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis, Long genreId) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
		
	}

	public MovieDTO(Movie entity) {
		
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		synopsis = entity.getSynopsis();
		
		
	}
	
	public MovieDTO(Movie entity, Genre genre) {
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		synopsis = entity.getSynopsis();
		this.genre = new GenreDTO(genre);
		
	}



	public MovieDTO(Movie entity, Genre genre, List<Review> reviews) {

	id = entity.getId();
	title = entity.getTitle();
	subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		synopsis = entity.getSynopsis();

		this.genre = new GenreDTO(genre);
		reviews.forEach(x -> this.reviews.add(new ReviewDTO(x, x.getUser(), x.getMovie())));
	//	entity.getReviews().forEach(rev -> this.reviews.add(new ReviewDTO(rev)));

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	public List<ReviewDTO> getReviews() {
		return reviews;
	}

	
	
	
}
