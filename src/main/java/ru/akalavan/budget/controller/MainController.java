package ru.akalavan.budget.controller;

import org.springframework.web.bind.annotation.*;
import ru.akalavan.budget.exceptions.NotFoundException;

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
public class MainController {

    private int counter = 4;

    private List<Map<String, String>> items = new ArrayList<>() {{
        add(new HashMap<>() {{
            put("id", "1");
            put("Expense", "curd");
        }});
        add(new HashMap<>() {{
            put("id", "2");
            put("Expense", "sour cream");
        }});
        add(new HashMap<>() {{
            put("id", "3");
            put("Income", "salary");
        }});
    }};

    private Map<String, String> getItem(@PathVariable String id) {
        return items.stream()
                .filter(item -> item.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/")
    public List<Map<String, String>> list() {
        return items;
    }

    @GetMapping("{id}")
    public Map<String, String> show(@PathVariable String id) {
        return getItem(id);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> item) {
        item.put("id", String.valueOf(counter++));

        items.add(item);

        return item;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> item) {
        Map<String, String> itemFromDB = getItem(id);

        itemFromDB.putAll(item);
        itemFromDB.put("id", id);

        return itemFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> item = getItem(id);

        items.remove(item);
    }
}
