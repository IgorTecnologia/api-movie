package com.devsuperior.movie.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movie.dto.MovieDTO;
import com.devsuperior.movie.entities.Genre;
import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.repositories.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovieResourceIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private GenreRepository genreRepository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long genreId;
	private String title;
	
	@BeforeEach
	public void setup() throws Exception{
		
		existingId = 1L;
		nonExistingId = 4L;
		title = "O";
		genreId = 1L;
	}
	
	@Test
	public void getMoviesShouldReturnAllWhenCallFindAll() throws Exception{
		
		ResultActions result = mockMvc.perform(get("/movies")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].title").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].subTitle").isNotEmpty());
	}
	
	@Test
	public void getMoviesByIdShouldReturnSelfWhenIdExisting() throws Exception{
		
		ResultActions result = mockMvc.perform(get("/movies/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.title").isNotEmpty());
		result.andExpect(jsonPath("$.subTitle").isNotEmpty());
	}
	
	@Test
	public void getMoviesByTitleShouldReturnResource() throws Exception{
		
		ResultActions result = mockMvc.perform(get("/movies/title/{title}", title)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$[0]").exists());
		result.andExpect(jsonPath("$[0].id").isNotEmpty());
		result.andExpect(jsonPath("$[0].title").value("O homem de ferro"));
		result.andExpect(jsonPath("$[1]").exists());
		result.andExpect(jsonPath("$[1].id").isNotEmpty());
		result.andExpect(jsonPath("$[1].title").isNotEmpty());
		
	}
	
	@Test
	public void insertShouldInsertResource() throws Exception{
		
		Genre genre = genreRepository.getOne(genreId);
		
		Movie entity = new Movie(null, "Marley & Eu", "Amigo e cão", 2000, "www.img.com", "Filme para a familia", genre);
		
		MovieDTO dto = new MovieDTO(entity, entity.getGenre(), entity.getReviews());
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(post("/movies")
				.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.title").isNotEmpty());	
		result.andExpect(jsonPath("$.subTitle").isNotEmpty());		
		result.andExpect(jsonPath("$.year").isNotEmpty());		
	}
}
