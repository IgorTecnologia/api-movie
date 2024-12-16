package com.devsuperior.movie.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.devsuperior.movie.repositories.MovieRepository;
import com.devsuperior.movie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movie.tests.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.devsuperior.movie.dto.MovieDTO;
import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.repositories.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieResourceIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MovieRepository repository;

	@Autowired
	private GenreRepository genreRepository;

	@Test
	public void findAllPagedShouldReturnAllMoviesPaged() throws Exception{
		
		ResultActions result = mockMvc.perform(get("/movies")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].title").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].subTitle").isNotEmpty());
	}
	
	@Test
	public void findByIdShouldReturnMovieByIdWhenIdExists() throws Exception{

		Optional<Movie> obj = repository.findAll().stream().findFirst();
		UUID id = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + obj.get().getId())).getId();

		ResultActions result = mockMvc.perform(get("/movies/{id}", id)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.title").isNotEmpty());
		result.andExpect(jsonPath("$.subTitle").isNotEmpty());
	}
	
	@Test
	public void queryMethodShouldReturnAllMoviesFilteredByTitle() throws Exception{

		String title = "O Garfield";

		ResultActions result = mockMvc.perform(get("/movies/title/{title}", title)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].title").value(title));
		result.andExpect(jsonPath("$.content[0].subTitle").exists());
		result.andExpect(jsonPath("$.content[0].yearOfRelease").exists());
		result.andExpect(jsonPath("$.content[0].synopsis").exists());

	}
	
	@Test
	public void insertShouldSaveObjectWhenCorrectStructure() throws Exception{
		
		MovieDTO dto = Factory.createdMovieDTO();
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(post("/movies")
				.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.title").isNotEmpty());	
		result.andExpect(jsonPath("$.subTitle").isNotEmpty());		
		result.andExpect(jsonPath("$.yearOfRelease").isNotEmpty());
		result.andExpect(jsonPath("$.synopsis").isNotEmpty());
	}
}
