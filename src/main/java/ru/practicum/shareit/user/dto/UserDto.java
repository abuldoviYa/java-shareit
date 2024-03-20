package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private final Long id;
    @Size(min = 2)
    @NotBlank
    private final String name;
    @Email
    @Size(min = 5)
    @NotBlank
    private final String email;
}
