package com.example.registry.service;

import com.example.registry.repo.PersonRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByName(username)
                .map(p -> User.withUsername(p.getName()).password(p.getPassword()).authorities("ROLE_USER").build())
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user %s".formatted(username)));
    }
}
