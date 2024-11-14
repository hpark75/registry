package com.example.registry.service;

import com.example.registry.model.Person;
import com.example.registry.model.PersonDTO;
import com.example.registry.model.Phone;
import com.example.registry.model.PhoneDTO;
import com.example.registry.repo.PersonRepository;
import com.example.registry.repo.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final PhoneRepository phoneRepository;

    public PersonService(PersonRepository personRepository, PhoneRepository phoneRepository) {
        this.personRepository = personRepository;
        this.phoneRepository = phoneRepository;
    }

    public Person createNew(Person person) {
        if (personRepository.findByEmail(person.getEmail().toLowerCase()).isPresent()) {
            throw new RuntimeException("The email %s already exists!".formatted(person.getEmail()));
        }
        Optional<Person> result = personRepository.findByName(person.getName());
        if (result.isEmpty()) {
            return personRepository.save(person);
        } else {
            Person p = result.get();
            return personRepository.save(update(person, p.getId(), p.getCreatedDate()));
        }
    }

    public void update(String name, String token, Date expiration) {
        personRepository.findByName(name).ifPresent(p -> personRepository.save(update(p, p.getId(), p.getCreatedDate(), token, expiration)));
    }

    private Person update(Person person, UUID id, Date createdDate) {
        return new Person(id, person.getName(), person.getEmail(), person.getPassword(), person.getPhones(), createdDate);
    }

    private Person update(Person person, UUID id, Date createdDate, String token, Date expiration) {
        return new Person(id, person.getName(), person.getEmail(), person.getPassword(), person.getPhones(), createdDate, token, expiration);
    }

    public Phone createNewPhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
