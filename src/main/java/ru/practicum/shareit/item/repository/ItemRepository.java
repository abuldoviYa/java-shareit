package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemRepository {
    void addItem(Item item);

    void updateItem(Item item);

    Item getItemById(Long itemId);

    List<Item> getAllItemsByOwnerId(Long ownerId);

    List<Item> searchItems(String searchText);
}
