package com.example.registry.model;

import java.util.Date;

public record TokenDTO(String token, Date expiration) {
}
