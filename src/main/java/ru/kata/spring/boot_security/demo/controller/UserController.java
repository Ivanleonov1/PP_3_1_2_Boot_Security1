package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/user")
public class UserController {


    @GetMapping
    public String getLoginPage() {
        return "/user";
    }

    @PostMapping("/user")
    public String redirectToMainPage() {
        return "redirect:/admin";
    }
}