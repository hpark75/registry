package com.example.registry.controller;

import com.example.registry.model.*;
import com.example.registry.service.PersonService;
import com.example.registry.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    public PersonController(PersonService personService) {
        this.personService = personService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createPerson(@RequestBody @Valid PersonDTO person) {
        List<Phone> phones1 = new ArrayList<>();
        Person person1 = personService.createNew(new Person(person.getName(), person.getEmail().toLowerCase(),
                passwordEncoder.encode(person.getPassword()), phones1));
        person1.setPhones(person.getPhones().stream().map(p -> newPhone(p, person1)).collect(Collectors.toList()));
        return convert(person1);
    }

    private Phone newPhone(@Valid PhoneDTO phone, Person person) {
        Phone phone1 = new Phone(phone.getNumber(), phone.getCitycode(), phone.getCountrycode(), person);
        return personService.createNewPhone(phone1);
    }

    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {
        return personService.getAll().stream()
                .map(p -> convert(p))
                .collect(Collectors.toList());
    }

    private PersonDTO convert(Person person) {
        return new PersonDTO(person.getName(), person.getEmail(), person.getPassword(), convert(person.getPhones()),
                person.getCreatedDate(), person.getLastModified(), person.getLastModified(),
                person.getToken() == null ? "" : person.getToken(),
                person.getExpiration() == null ?
                false : Date.from(Instant.now()).compareTo(person.getExpiration()) < 0);
    }

    private List<PhoneDTO> convert(List<Phone> phones) {
        return phones.stream()
                .map(p -> new PhoneDTO(p.getNumber(), p.getCitycode(), p.getCountrycode()))
                .collect(Collectors.toList());
    }


}
