package com.devsuperior.movie.entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenreTests {

	@Test
	public void genreShouldHaveCorrectStructure() {
	
		Genre entity = new Genre();
		
		entity.setId(1L);
		entity.setName("Ação");
		
		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getName());
		Assertions.assertEquals(0, entity.getMovie().size());
	}
}
