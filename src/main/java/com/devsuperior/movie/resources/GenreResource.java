package com.devsuperior.movie.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movie.dto.GenreDTO;
import com.devsuperior.movie.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

	@Autowired
	private GenreService service;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll(){
		
		List<GenreDTO> dto = service.findAll();
		
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> findById(@PathVariable Long id){
	
		GenreDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<List<GenreDTO>> queryMethod(@PathVariable String name){
		
		List<GenreDTO> dto = service.queryMethod(name);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity <GenreDTO> insert(@RequestBody GenreDTO dto){
		
		dto = service.insert(dto);
		
		return ResponseEntity.ok().body(dto);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO dto){
		
		dto = service.update(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
