package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idCounter;

    public InMemoryUserRepository() {
        this.idCounter = new AtomicLong(0);
    }

    @Override
    public User save(User user) {
        Optional<User> userFound = findByEmail(user.getEmail());
        if (userFound.isPresent() && !userFound.get().getId().equals(user.getId())) {
            throw new RuntimeException("Email already exists");
        }
        if (user.getId() == null) {
            user.setId(idCounter.incrementAndGet());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = users.get(id);
        if (user != null) {
            return Optional.of(new User(user));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }
}

