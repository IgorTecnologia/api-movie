package com.devsuperior.movie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsuperior.movie.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	public List<Genre>findAllByNameContainingIgnoreCase(@Param("name") String name);
}
