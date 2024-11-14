package com.example.registry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @NotBlank(message = "The name must not be null and must contain at least one non-whitespace character")
    @Size(min = 3, max = 70, message = "The name content must be at most 70 characters, and has at least 3 characters")
    private String name;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "The email must be valid")
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private List<PhoneDTO> phones;

    private Date createdDate;

    private Date lastModified;

    private Date last_login;

    private String token;

    private Boolean isactive;
}
