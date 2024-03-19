package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private final Long id;
    @NotBlank
    private final String name;
    @Email
    @NotBlank
    private final String email;
}
