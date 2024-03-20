package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryItemRepository implements ItemRepository {

    private final Map<Long, Item> items;
    private final AtomicLong idCounter;
    private final UserRepository userRepository;

    public InMemoryItemRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.idCounter = new AtomicLong(0);
        this.items = new HashMap<>();
    }

    @Override
    public void addItem(Item item) {

        if (userRepository.findById(item.getOwnerId()).isEmpty()) {
            throw new NotFoundException("No user with this id");
        }
        long newId = idCounter.incrementAndGet();

        item.setId(newId);
        items.put(item.getId(), item);
    }

    @Override
    public void updateItem(Item item) {
        items.put(item.getId(), item);
    }

    @Override
    public Item getItemById(Long itemId) {
        return items.get(itemId);
    }

    @Override
    public List<Item> getAllItemsByOwnerId(Long ownerId) {
        List<Item> ownerItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getOwnerId().equals(ownerId)) {
                ownerItems.add(item);
            }
        }
        return ownerItems;
    }

    @Override
    public List<Item> searchItems(String searchText) {
        if (searchText.isBlank()) {
            return new ArrayList<>();
        }
        List<Item> foundItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getName().toLowerCase().contains(searchText.toLowerCase()) || item.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
}

