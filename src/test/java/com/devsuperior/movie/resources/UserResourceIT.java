package com.devsuperior.movie.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devsuperior.movie.entities.User;
import com.devsuperior.movie.repositories.UserRepository;
import com.devsuperior.movie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movie.tests.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.devsuperior.movie.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository repository;
	
	@Test
	public void findAllPagedShouldReturnAllUserPaged() throws Exception {
		
		ResultActions result = mockMvc.perform(get("/users/profile")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].name").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].email").isNotEmpty());

	}
	
	@Test
	public void findByIdShouldReturnUserByIdWhenIdExists() throws Exception{

		Optional<User> obj = repository.findAll().stream().findFirst();
		UUID id = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + obj.get().getId())).getId();

		ResultActions result = mockMvc.perform(get("/users/{id}", id)
				.accept(MediaType.APPLICATION_JSON));
		
			result.andExpect(jsonPath("$.id").isNotEmpty());
			result.andExpect(jsonPath("$.name").isNotEmpty());
			result.andExpect(jsonPath("$.email").isNotEmpty());
	}
	
	@Test
	public void queryMethodShouldReturnAllUserFilteredByName() throws Exception{

		String name = "Nanci";

		ResultActions result = mockMvc.perform(get("/users/name/{name}", name)
				.accept(MediaType.APPLICATION_JSON));
				
		result.andExpect(jsonPath("$[0]").exists());
		result.andExpect(jsonPath("$[0].id").isNotEmpty());
		result.andExpect(jsonPath("$[0].name").isNotEmpty());
		result.andExpect(jsonPath("$[0].email").isNotEmpty());
		result.andExpect(jsonPath("$[0].password").isNotEmpty());
	}
	
	@Test
	public void insertShouldSaveObjectWhenCorrectStructure() throws Exception{
		
		UserDTO dto = Factory.createdUserDto();
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(post("/users")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.name").isNotEmpty());
		result.andExpect(jsonPath("$.email").isNotEmpty());
		result.andExpect(jsonPath("$.password").isNotEmpty());
	}
	
	@Test
	public void updateShouldSaveObjectWhenIdExists() throws Exception{

		Optional<User> obj = repository.findAll().stream().findFirst();
		UUID id = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + obj.get().getId())).getId();

		UserDTO dto = Factory.createdUserDtoToUpdate();

		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(put("/users/{id}", id)
				.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.name").isNotEmpty());
		result.andExpect(jsonPath("$.email").isNotEmpty());
		result.andExpect(jsonPath("$.password").isNotEmpty());
	}
	
	@Test
	public void deleteByIdShouldDeleteUserByIdWhenIdExists() throws Exception{

		Optional<User> obj = repository.findAll().stream().findFirst();
		UUID id = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + obj.get().getId())).getId();

		ResultActions result = mockMvc.perform(delete("/users/{id}", id)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
}
