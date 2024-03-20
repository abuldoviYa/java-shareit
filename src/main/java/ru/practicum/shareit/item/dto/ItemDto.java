package ru.practicum.shareit.item.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDto {
    private Long id;
    @Size(min = 2)
    @NotBlank
    private String name;
    @Size(min = 5)
    @NotBlank
    private String description;
    @NotNull
    private Boolean available;
    private Long ownerId;
}
