package ru.akalavan.budget.service;

import org.springframework.stereotype.Service;
import ru.akalavan.budget.exceptions.NotFoundException;
import ru.akalavan.budget.model.Item;
import ru.akalavan.budget.repository.ItemRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class ItemService
 *
 * @author Вагин Михаил
 * date 04.08.2022
 */

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public Item getItem(Long id) {
        List<Item> items = list();
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public void add(Item item) {
        item.setCreationDate(LocalDateTime.now());
        itemRepository.save(item);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Item update(Item item) {
        Optional<Item> row = itemRepository.findById(item.getId());

        row.ifPresent(itemUpdate -> {
            if (!item.getName().isEmpty()) {
                itemUpdate.setName(item.getName());
            }
            itemRepository.save(itemUpdate);
        });

        return item;
    }
}
