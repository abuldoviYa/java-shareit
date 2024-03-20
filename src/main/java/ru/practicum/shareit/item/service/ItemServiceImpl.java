package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.error.ForbiddenException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto addItem(ItemDto itemDto, Long ownerId) {
        itemDto.setOwnerId(ownerId);
        Item item = ItemMapper.toItem(itemDto);
        itemRepository.addItem(item);

        return ItemMapper.toItemDto(item);
    }

    public ItemDto updateItem(Long itemId, ItemDto itemDto, Long ownerId) {
        Item existingItem = itemRepository.getItemById(itemId);

        if (existingItem == null) {
            return null;
        }

        if (!existingItem.getOwnerId().equals(ownerId)) {
            throw new ForbiddenException("Forbidden, as owner is different");
        }
        if (itemDto.getName() != null && !itemDto.getName().isBlank()) {
            existingItem.setName(itemDto.getName());
        }
        if (itemDto.getDescription() != null && !itemDto.getDescription().isBlank()) {
            existingItem.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            existingItem.setAvailable(itemDto.getAvailable());
        }

        itemRepository.updateItem(existingItem);

        return ItemMapper.toItemDto(existingItem);
    }

    public ItemDto getItemById(Long itemId) {
        Item item = itemRepository.getItemById(itemId);

        if (item == null) {
            return null;
        }

        return ItemMapper.toItemDto(item);
    }

    public List<ItemDto> getAllItemsByOwnerId(Long ownerId) {
        List<Item> items = itemRepository.getAllItemsByOwnerId(ownerId);
        List<ItemDto> itemDtos = items.stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
        return itemDtos;
    }

    public List<ItemDto> searchItems(String searchText) {
        List<Item> items = itemRepository.searchItems(searchText);
        List<ItemDto> itemDtos = items.stream()
                .filter(Item::getAvailable)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
        return itemDtos;
    }
}

