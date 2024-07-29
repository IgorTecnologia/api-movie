package com.devsuperior.movie.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movie.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIT {
	
	private String username;
	private String email;
	private String password;
	private Long ExistingId;
	private Long nonExistingId;
	private String name;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@BeforeEach
	void setUp() throws Exception{
		
		List<String> list = new ArrayList<>();
		list.add(username);
		list.add(email);
		list.add(password);
		
		username = "Igor";
		email = "igor@gmail.com.br";
		password = "1234567";
		ExistingId = 1L;
		nonExistingId = 3L;
		name = "Igor";
	}
	
	@Test
	public void getUsersShouldReturnSelfWhenCallFindAll() throws Exception {
		
		ResultActions result = mockMvc.perform(get("/users/profile")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0]").exists());
		result.andExpect(jsonPath("$[0].id").isNotEmpty());
		result.andExpect(jsonPath("$[0].name").isNotEmpty());
		result.andExpect(jsonPath("$[0].email").isNotEmpty());
				
	}
	
	@Test
	public void getUsersByIdShouldReturnSelfWhenExistingId() throws Exception{
		
		ResultActions result = mockMvc.perform(get("/users/{id}", ExistingId)
				.accept(MediaType.APPLICATION_JSON));
		
			result.andExpect(jsonPath("$.id").isNotEmpty());
			result.andExpect(jsonPath("$.name").isNotEmpty());
			result.andExpect(jsonPath("$.email").isNotEmpty());
	}
	
	@Test
	public void getUserByNameShouldReturnSelfWhenNameExisting() throws Exception{
		
		ResultActions result = mockMvc.perform(get("/users/name/{name}", name)
				.accept(MediaType.APPLICATION_JSON));
				
		result.andExpect(jsonPath("$[0]").exists());
		result.andExpect(jsonPath("$[0].id").isNotEmpty());
		result.andExpect(jsonPath("$[0].name").isNotEmpty());
		result.andExpect(jsonPath("$[0].email").isNotEmpty());
		result.andExpect(jsonPath("$[0].password").isNotEmpty());
	}
	
	@Test
	public void insertShouldInsertResource() throws Exception{
		
		UserDTO dto = new UserDTO(3L, name, email, password);
		
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
	public void updateShouldUpdateResource() throws Exception{
		
		UserDTO dto = new UserDTO(null, name, email, password);
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(put("/users/{id}", ExistingId)
				.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.name").isNotEmpty());
		result.andExpect(jsonPath("$.email").isNotEmpty());
		result.andExpect(jsonPath("$.password").isNotEmpty());
	}
	
	@Test
	public void deleteByIdShouldDeleteResourceWhenIdExisting() throws Exception{
		
		ResultActions result = mockMvc.perform(delete("/users/{id}", ExistingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(jsonPath("$.id").doesNotExist());
		result.andExpect(jsonPath("$.name").doesNotExist());
		result.andExpect(jsonPath("$.email").doesNotExist());
		result.andExpect(jsonPath("$.passwrod").doesNotExist());
	}
}
