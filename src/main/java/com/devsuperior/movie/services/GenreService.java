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

import com.devsuperior.movie.dto.GenreDTO;
import com.devsuperior.movie.entities.Genre;
import com.devsuperior.movie.repositories.GenreRepository;
import com.devsuperior.movie.services.exceptions.DataBaseException;
import com.devsuperior.movie.services.exceptions.ResourceNotFoundException;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;
	
	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		
		List<Genre> entity = repository.findAll();
		
		return entity.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {
		
		Optional<Genre> obj = repository.findById(id);
		Genre entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new GenreDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<GenreDTO> queryMethod(String name) {
	
		List<Genre> entity = repository.findAllByNameContainingIgnoreCase(name);
		
		return entity.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional
	public GenreDTO insert(GenreDTO dto) {
		
		Genre entity = new Genre();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new GenreDTO(entity);
	}
	
	private void copyDtoToEntity(GenreDTO dto, Genre entity) {
		entity.setName(dto.getName());
	}
	
	@Transactional
	public GenreDTO update(Long id, GenreDTO dto) {
		
		try {
			Genre entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			repository.save(entity);
			return new GenreDTO(entity);
		}catch(EntityNotFoundException e) {
		 throw new ResourceNotFoundException("id not found");
		}
	}
	
	public void delete(Long id) {
		
		try {
		repository.deleteById(id);
	}catch(EmptyResultDataAccessException e) {
		throw new ResourceNotFoundException("Id not found");
	}catch(DataIntegrityViolationException e) {
		throw new DataBaseException("Integrity violation");
	}
}
}
