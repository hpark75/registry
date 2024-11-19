package com.example.registry;

import com.example.registry.controller.AuthController;
import com.example.registry.controller.PersonController;
import com.example.registry.service.PersonService;
import com.example.registry.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
class RegistryApplicationTests {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PersonService personService;

	@Autowired
	TokenService tokenService;

	@Autowired
	PersonController personController;

	@Autowired
	AuthController authController;

	@Test
	void contextLoads() {
		assertThat(userDetailsService).isNotNull();
		assertThat(personService).isNotNull();
		assertThat(tokenService).isNotNull();
		assertThat(personController).isNotNull();
		assertThat(authController).isNotNull();
	}

}
