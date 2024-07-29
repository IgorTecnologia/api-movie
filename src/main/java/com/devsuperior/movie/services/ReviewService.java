package com.devsuperior.movie.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movie.dto.ReviewDTO;
import com.devsuperior.movie.entities.Movie;
import com.devsuperior.movie.entities.Review;
import com.devsuperior.movie.entities.User;
import com.devsuperior.movie.repositories.MovieRepository;
import com.devsuperior.movie.repositories.ReviewRepository;
import com.devsuperior.movie.repositories.UserRepository;
import com.devsuperior.movie.services.exceptions.DataBaseException;
import com.devsuperior.movie.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;	
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAll() {
		
		List<Review> entity = reviewRepository.findAll();
		
		return entity.stream().map(x -> new ReviewDTO(x, x.getUser(), x.getMovie())).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		
		Optional<Review> obj = reviewRepository.findById(id);
		
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		
		return new ReviewDTO(entity, entity.getUser(), entity.getMovie());
		}
		
	@Transactional(readOnly = true)
	public List<ReviewDTO> queryMethod(String text){
		
		List<Review> entity = reviewRepository.findAllByTextContainingIgnoreCase(text);
		
		return entity.stream().map(x -> new ReviewDTO(x, x.getUser(), x.getMovie())).collect(Collectors.toList());
	}
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		Review entity = new Review();
		copyDtoToEntity(entity, dto);
		reviewRepository.save(entity);
		return new ReviewDTO(entity, entity.getUser(), entity.getMovie());
	}
	
	@Transactional
	public ReviewDTO update(Long id, ReviewDTO dto) {
		
		try {
		Review entity = reviewRepository.getOne(id);
		copyDtoToEntity(entity, dto);
		reviewRepository.save(entity);
		return new ReviewDTO(entity, entity.getUser(), entity.getMovie());
	}catch(EntityNotFoundException e) {
		throw new ResourceNotFoundException("Id not found");
	}
}
	
	public void delete(Long id) {
		
		try {
		reviewRepository.deleteById(id);
	}catch(EntityNotFoundException e) {
		throw new ResourceNotFoundException("Id not found");
	}catch(DataIntegrityViolationException e) {
		throw new DataBaseException("Integrity violation");
	}
}
	
	public void copyDtoToEntity(Review review, ReviewDTO dto) {
		
		User user = userRepository.getOne(dto.getUser().getId());
		
		Movie movie = movieRepository.getOne(dto.getMovie().getId());
		
		review.setId(dto.getId());
		review.setText(dto.getText());
		
		review.setUser(user);
		review.setMovie(movie);
	}
}
