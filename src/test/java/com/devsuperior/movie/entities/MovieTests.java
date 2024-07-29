package com.devsuperior.movie.entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieTests {

	@Test
	public void MovieShouldHaveCorrectStructure() {
		
		Movie entity = new Movie();
		
		Genre genre = new Genre(1L, "Comédia");
		
		entity.setId(1L);
		entity.setTitle("Garfild");
		entity.setSubTitle("Esperto para xuxu");
		entity.setYear(2000);
		entity.setSynopsis("Uma gostosa aventura com o Garfild");
		entity.setImgUrl("www.img.com.br");
		entity.setGenre(genre);
		
		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getTitle());
		Assertions.assertNotNull(entity.getSubTitle());
		Assertions.assertNotNull(entity.getYear());
		Assertions.assertNotNull(entity.getSynopsis());
		Assertions.assertNotNull(entity.getImgUrl());
		Assertions.assertNotNull(entity.getGenre());
	}
}
