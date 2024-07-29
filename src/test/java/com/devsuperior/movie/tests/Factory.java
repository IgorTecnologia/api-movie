package com.devsuperior.movie.tests;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movie.dto.MovieDTO;
import com.devsuperior.movie.entities.Genre;
import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.entities.Review;
import com.devsuperior.movie.entities.User;
import com.devsuperior.movie.repositories.GenreRepository;

public class Factory {

	@Autowired
	private static GenreRepository genreRepository;
	
	private static Long id = 1L;
	
	public static Movie createdMovie() {
		
		Genre genre = genreRepository.getOne(id);
		
		Movie movie = new Movie(null, "Sempre ao seu lado", "Cão amigo", 2000, "www.img.com", "Um simbolo de amizade", genre);
		//movie.setGenre(createdGenre());
		movie.getReviews().add(null);
		return movie;
	}
	
	public static MovieDTO createdMovieDTO() {
		
		Movie movie = createdMovie();
		//movie.getReviews().add(createdReview());
		//movie.setGenre(createdGenre());
		return new MovieDTO(movie, movie.getGenre());
	}
	
	public static Genre createdGenre() {
		
		return new Genre(null, "Romance");
		
	}
	
	public static Review createdReview() {
		
		User user = new User(null, "Mario", "mario@gmail.com", "1234567");
		
		Movie movie = createdMovie();
		
		return new Review(null, "Filme legal!",movie, user);
	}
}
