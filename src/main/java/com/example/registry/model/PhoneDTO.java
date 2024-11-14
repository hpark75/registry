package com.example.registry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    @NotBlank(message = "The number must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 10, message = "The number content must be at most 10 characters, and has at least 1 characters")
    private String number;

    @NotBlank(message = "The city code must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 6, message = "The city code content must be at most 6 characters, and has at least 1 characters")
    private String citycode;

    @NotBlank(message = "The country code must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 4, message = "The country code content must be at most 4 characters, and has at least 1 characters")
    private String countrycode;
}
