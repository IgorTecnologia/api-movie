package com.devsuperior.movie.tests;

import com.devsuperior.movie.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movie.dto.MovieDTO;
import com.devsuperior.movie.entities.Genre;
import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.entities.Review;
import com.devsuperior.movie.repositories.GenreRepository;

import java.util.UUID;

public class Factory {

	@Autowired
	private static GenreRepository genreRepository;

	public static Movie createdMovie() {

		UUID id = UUID.randomUUID();

		Movie entity = new Movie(id, "Sempre ao seu lado", "Cão amigo", 2000, "www.img.com", "Um simbolo de amizade");

		return entity;
	}
	
	public static MovieDTO createdMovieDTO() {

		UUID id = UUID.randomUUID();

		MovieDTO dto = new MovieDTO(id, "Marley & Eu", "Amigo de ouro", 2000, "www.image.com", "Um cão amigo");

		return dto;
	}
	
	public static Genre createdGenre() {

		UUID id = UUID.randomUUID();

		return new Genre(id, "Romance");
		
	}
	
	public static Review createdReview() {

		UUID id = UUID.randomUUID();

		Review entity = new Review(id, "Filme legal.");
		
		return entity;
	}

	public static UserDTO createdUserDto(){

		UUID id = UUID.randomUUID();

		UserDTO dto = new UserDTO(id, "Vitória", "vitória@gmail.com", "1234567");

		return dto;
	}

	public static UserDTO createdUserDtoToUpdate(){

		UUID id = UUID.randomUUID();

		UserDTO dto = new UserDTO(id, "Tomás", "tomas@gmail.com", "1234567");

		return dto;
	}
}
