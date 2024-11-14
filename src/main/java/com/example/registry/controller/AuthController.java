package com.example.registry.controller;

import com.example.registry.model.TokenDTO;
import com.example.registry.service.PersonService;
import com.example.registry.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final PersonService personService;

    public AuthController(TokenService tokenService, PersonService personService) {
        this.tokenService = tokenService;
        this.personService = personService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(Authentication authentication) {
        LOG.info("Token requested for user: '{}'", authentication);
        TokenDTO token = tokenService.generateToken(authentication);
        personService.update(authentication.getName(), token.token(), token.expiration());
        LOG.info("Token granted {}", token.token());
        return token.token();
    }
}
