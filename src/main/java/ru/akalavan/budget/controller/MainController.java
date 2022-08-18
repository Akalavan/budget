package ru.akalavan.budget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.akalavan.budget.repository.ItemRepository;

/**
 * Class MainController
 *
 * @author Вагин Михаил
 * date 09.08.2022
 */
@Controller
public class MainController {

    private final ItemRepository itemRepository;

    @Value("${spring.profile.active}")
    private String profile;

    @Autowired
    public MainController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping()
    public String main(Model model) {
        model.addAttribute("isDevMode", "dev".equals(profile));
        System.out.println(profile);
        return "index";
    }

    @GetMapping("v1/item")
    public String item(Model model) {

        model.addAttribute("frontendData", itemRepository.findAll());
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "itemV";
    }
}
