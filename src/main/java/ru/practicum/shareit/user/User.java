package ru.practicum.shareit.user;

/**
 * TODO Sprint add-controllers.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;

    public User(User other) {
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
    }
}


