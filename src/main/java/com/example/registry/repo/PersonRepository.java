package com.example.registry.repo;

import com.example.registry.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
}
