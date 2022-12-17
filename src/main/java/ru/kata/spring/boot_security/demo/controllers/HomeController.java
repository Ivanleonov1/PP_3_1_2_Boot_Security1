package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @PostMapping
    public String onlyGetMapping(Model model) {
        return "home";
    }
}
