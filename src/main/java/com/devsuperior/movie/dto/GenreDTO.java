package com.devsuperior.movie.dto;

import java.io.Serializable;

import com.devsuperior.movie.entities.Genre;
import com.devsuperior.movie.entities.Movie;

public class GenreDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	
	public GenreDTO() {
	}

	public GenreDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public GenreDTO(Genre entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public GenreDTO(Genre entity, Movie movie) {
		id = entity.getId();
		name = entity.getName();
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
