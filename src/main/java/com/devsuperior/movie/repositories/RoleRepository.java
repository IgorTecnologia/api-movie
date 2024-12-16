package com.devsuperior.movie.repositories;

import com.devsuperior.movie.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean existsByAuthority(String authority);
}
