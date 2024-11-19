package com.example.registry.controller;

import com.example.registry.config.SecurityConfig;
import com.example.registry.model.Person;
import com.example.registry.model.Phone;
import com.example.registry.repo.PersonRepository;
import com.example.registry.repo.PhoneRepository;
import com.example.registry.service.PersonService;
import com.example.registry.service.TokenService;
import com.example.registry.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({PersonController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class, UserDetailsServiceImpl.class})
public class PersonControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonRepository personRepository;

    @MockBean
    PhoneRepository phoneRepository;

    @MockBean
    PersonService personService;

    @Test
    void rootWhenUnAuthenticatedThen401() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isUnauthorized());
    }

    @Test
    void createUser() throws Exception {
        when(personService.createNew(any())).thenReturn(mock(Person.class));
        this.mvc.perform(post("/persons")
                .content("""
                            {
                                "name": "Juan",
                            "email": "juan@me.com",
                            "password": "secreto123",
                            "phones": [ {
                                    "number": "44444444",
                                    "citycode": "11",
                                    "countrycode": "54"
                                    } ]
                            }
                         """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
