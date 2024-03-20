package ru.practicum.shareit.user;

/**
 * TODO Sprint add-controllers.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @Size(min = 2)
    @NotBlank
    private String name;
    @Email
    @Size(min = 5)
    @NotBlank
    private String email;

    public User(User other) {
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
    }
}


