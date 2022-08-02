package ru.akalavan.budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author Вагин Михаил
 * date 01.08.2022
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
}
