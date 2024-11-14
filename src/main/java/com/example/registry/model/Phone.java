package com.example.registry.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String number;

    @Column
    private String citycode;

    @Column
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Phone(String number, String citycode, String countrycode, Person person) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
        this.person = person;
    }
}
