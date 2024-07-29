package com.devsuperior.movie.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movie.dto.UserDTO;
import com.devsuperior.movie.entities.User;
import com.devsuperior.movie.repositories.UserRepository;
import com.devsuperior.movie.services.exceptions.DataBaseException;
import com.devsuperior.movie.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll () {
		
		List<User> entity = repository.findAll();
		
		return entity.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		Optional<User> obj = repository.findById(id);
		
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		
		return new UserDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> queryMethod(String name) {
	
		List<User> entity = repository.findAllByNameContainingIgnoreCase(name);
		
		return entity.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new UserDTO(entity);
		
	}
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		
		try {
		User entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new UserDTO(entity);
		
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		
	}
	
	public void delete(Long id) {
		
		try {
		repository.deleteById(id);
		
	}catch (EmptyResultDataAccessException e) {
		throw new ResourceNotFoundException("Id not found "+ id);
	}
	
	catch (DataIntegrityViolationException e) {
		throw new DataBaseException("Integrity violation");
	}
}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		
	}
}
