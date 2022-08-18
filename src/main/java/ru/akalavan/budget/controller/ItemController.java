package ru.akalavan.budget.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import ru.akalavan.budget.exceptions.NotFoundException;
import ru.akalavan.budget.model.Item;
import ru.akalavan.budget.model.Views;
import ru.akalavan.budget.service.ItemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Вагин Михаил
 * date 01.08.2022
 */
@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    @JsonView(Views.IdName.class)
    public List<Item> list() {
        return itemService.list();
    }

//    @GetMapping("{id}")
//    public Item show(@PathVariable Long id) {
//        return itemService.getItem(id);
//    }

    @GetMapping("{id}")
    public Item show(@PathVariable("id") Item item) {
        return item;
    }

    @PostMapping()
    public Item create(@RequestBody Item item) {
        itemService.add(item);
        System.out.println(item);
        return item;
    }

    @PutMapping()
    public Item update(@RequestBody Item item) {
        return itemService.update(item);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
