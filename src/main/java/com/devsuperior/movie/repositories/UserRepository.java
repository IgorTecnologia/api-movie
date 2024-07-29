package com.devsuperior.movie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsuperior.movie.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findAllByNameContainingIgnoreCase(@Param ("name") String name);
}
