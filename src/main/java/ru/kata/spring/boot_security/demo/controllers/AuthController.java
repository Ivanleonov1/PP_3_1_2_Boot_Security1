package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Collections;

@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public AuthController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        return "/auth/login";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(UserEntity formUser, Model model) {
        String err = "Пароли не совпадают";
        UserEntity user = new UserEntity();
        user.setUsername(formUser.getUsername());
        user.setNickname(formUser.getUsername().toUpperCase());
        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        user.setRoles(Collections.singleton(roleService.createRole("ROLE_USER")));

        if (formUser.getPassword().equals(formUser.getConfirm())) {
            if (userService.loadUserByUsername(user.getUsername()) == null) {
                userService.createOrUpdate(user);
                System.out.println(user);
                return "redirect:/login";
            }
            err = "Логин занят";
        }
        model.addAttribute("error", err);
        return "auth/register";
    }


    @GetMapping(params = "/login/logout")
    public String logout1() {
        return "redirect:/logout";
    }

    @GetMapping(params = "/register/logout")
    public String logout2() {
        return "redirect:/logout";
    }
}
