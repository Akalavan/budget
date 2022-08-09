package ru.akalavan.budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class MainController
 *
 * @author Вагин Михаил
 * date 09.08.2022
 */
@Controller
public class MainController {

    @GetMapping("v1/item")
    public String item() {
        return "itemV";
    }
}
