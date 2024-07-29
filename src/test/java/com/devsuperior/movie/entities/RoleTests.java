package com.devsuperior.movie.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoleTests {

	@Test
	public void RoleShouldHaveCorrectStructure() {

		Role entity = new Role();

		Long id = 1L;

		entity.setId(id);
		entity.setAuthority("Admin");

		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getAuthority());
		Assertions.assertEquals("Admin", entity.getAuthority());

	}
}
