package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.repository.UserRepository;

import javax.validation.ValidationException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.practicum.shareit.user.mapper.UserMapper.toUser;
import static ru.practicum.shareit.user.mapper.UserMapper.toUserDto;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        return toUserDto(userRepository.save(toUser(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with ID #" + userId + " does not exist."));
        if (userDto.getName() != null) user.setName(userDto.getName());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());

        return toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto get(Long userId) {
        if (userId == null) throw new ValidationException("User ID cannot be null.");
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with ID #" + userId + " does not exist."));
        return toUserDto(user);
    }

    @Override
    public void delete(Long userId) {
        if (userId == null) throw new ValidationException("User ID cannot be null.");
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(toList());
    }
}
