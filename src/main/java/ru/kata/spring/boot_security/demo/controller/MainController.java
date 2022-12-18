package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getPageAdminAndUsers(Model model, Principal principal) {
        model.addAttribute("authUser", userService.getUserByName(principal.getName()));
        model.addAttribute("listUser", userService.getAllUsers());
        model.addAttribute("create", new User());
        return "admin";
    }

    @PostMapping("/save")
    public String getSaveUserForm(@ModelAttribute("create") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String getUpdateUserForm(@ModelAttribute("user") User user) {
        userService.updateUserById(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String getDeleteUserForm(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
