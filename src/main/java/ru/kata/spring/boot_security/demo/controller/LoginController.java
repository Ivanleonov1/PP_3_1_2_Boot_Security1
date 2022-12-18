package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/admin")
    public String redirectToMainPage() {
        return "redirect:/admin";
    }
}
