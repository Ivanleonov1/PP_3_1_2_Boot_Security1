package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

    @GetMapping()
    public String showProfile(Model model, @AuthenticationPrincipal UserEntity userEntity) {
        model.addAttribute("user", userEntity);
        return "user/profile";
    }

    @GetMapping("/{id}")
    public String showProfileFromAdmin(Model model, @PathVariable Long id) {
        UserEntity user = userService.get(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/profile";
        } else {
            model.addAttribute("messages", Arrays.asList("Нет такого пользователя.", "Вы не угадали ID'шник ))"));
            return "error";
        }
    }
}
